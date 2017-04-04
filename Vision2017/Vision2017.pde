import ipcapture.*;
import gab.opencv.*;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/* USER OPTIONS AND SETTINGS AREA BEGINS HERE */

// Testing options //
final boolean testModeEnabled = false; //Are we in test mode (no connection, etc)?
final String testImageName = "image.png"; //Image used for tests

// Display options //
final int inputWidth = 120; //Pixels
final int inputHeight = 90;
final int viewWidth = 640;
final int viewHeight = 480;

final boolean displayFilteredImage = false; //Do we display the black-and-white filtered image (as opposed to the original)? Useful for debugging

final boolean displayOutlines = true;
final int targetOutlineThickness = 3;
final color outlineColor = color(255, 0, 0);

final boolean displayBoundingBoxes = true;
final int boundingBoxOutlineThickness = 1;
final color boundingBoxColor = color(0, 0, 255);

final boolean displayTargetCrosshairs = true;
final int crosshairThickness = 1;
final color closestCrosshairColor = color(0, 255, 0);
final color defaultCrosshairColor = color(150, 0, 0);
final int crosshairLength = 100;

final boolean displayScreenCrosshairs = true;
final int screenCrosshairThickness = 1;
final color screenCrosshairColor = color(255, 0, 0);

// Networking constants //
final String robotIP = "roborio-2438-frc.local"; // 169.254.160.17

/* USER OPTIONS AND SETTINGS AREA ENDS HERE */

/* TUNING AREA BEGINS HERE */

// camera1 offset //
final float imageCenterX = 0.5; //What should the program consider the "center" of the frame?
final float imageCenterY = 0.5;

// Image and display parameters //
final float inputAspectRatio = float(inputWidth) / float(inputHeight);
final float viewAspectRatio = float(viewWidth) / float(viewHeight);
final float xDisplayMultiplier = float(viewWidth) / float(inputWidth);
final float yDisplayMultiplier = float(viewHeight) / float(inputHeight);
final float yMysteryMultiplier = yDisplayMultiplier; //Really, really don't know why this is needed, but... for some reason -squaring- the y multiplier makes stuff work...?
final int textSize = 23;
final int framerate = 15;

// Color filtering parameters //
final float hueMin = 130;
final float hueMax = 200;
final float satMin = 20;
final float satMax = 100;
final float valMin = 20;
final float valMax = 100;

// Area filtering parameters //
final int minArea = 200; //Minimum number of pixels a blob should have (bounding box area)

// Recantularity filtering parameters //
final float minRectangularity = 0.0; //TODO: Tune
final float maxRectangularity = 1;

// Aspect ratio filtering parameters //
final float minAspectRatio = 0.1;
final float maxAspectRatio = 3;

// OpenCV parameters //
final int polygonDetectionStrictness = 2; //Higher = simpler polygon

/* TUNING AREA ENDS HERE */

// List of filters //
ArrayList<ContourFilter> filterList;
MinimumAreaFilter minimumAreaFilter = new MinimumAreaFilter(minArea);
RectangularityRangeFilter rectangularityRangeFilter = new RectangularityRangeFilter(minRectangularity, maxRectangularity);
AspectRatioRangeFilter aspectRatioRangeFilter = new AspectRatioRangeFilter(minAspectRatio, maxAspectRatio);

// Physical parameters //
final float targetWidth = 20.0; //Inches
final float targetHeight = 12.0;

// Program is run as a finite state machine //
VisionState state;

// IPCapture //
IPCapture camera1;
IPCapture camera2;
final int mjpgPort1 = 5800;
final int mjpgPort2 = 8080;
final String mjpgURL1 = "http://" + robotIP + ":" + mjpgPort1 + "/?action=stream";
final String mjpgURL2 = "http://" + robotIP + ":" + mjpgPort2 + "/?action=stream";

// Network table //
//NetworkTable networkTable;
final String networkTableName = "SmartDashboard";
final String networkTableXErrorField = "vision-x-error";
final String networkTableYErrorField = "vision-y-error";

// OpenCV //
OpenCV opencv;

// Image info //
PImage frame;
PImage filteredFrame;
ArrayList<Contour> potentialTargets;
ArrayList<Contour> filteredTargets;

void settings() {
  size(1330, viewHeight);
}

void setup() {
  // Initialize state machine //
  state = testModeEnabled ? VisionState.RUNNING : VisionState.CONNECTING;

  // Initialize Processing //
  colorMode(HSB, 360, 100, 100);
  background(0); //Black background
  frameRate(framerate);

  // Initialize OpenCV //
  frame = new PImage();
  opencv = new OpenCV(this, viewWidth, viewHeight);

  // Initialize filter list //
  filterList = new ArrayList<ContourFilter>();
  filterList.add(minimumAreaFilter);
  filterList.add(rectangularityRangeFilter);
  filterList.add(aspectRatioRangeFilter);
}

void draw() {
  switch (state) {
  case CONNECTING:
    try {        
      // Try to connect IPCapture //
      camera1 = new IPCapture(this, mjpgURL1, "", ""); //No username / password
      camera1.start();
      camera2 = new IPCapture(this, mjpgURL2, "", ""); //No username / password
      camera2.start();
      // Try to connect network table //
      //networkTable = NetworkTable.getTable(networkTableName);
    } 
    catch (Exception e) {
      println("Failed to connect to either network table or camera1");
      return;
    }

    // Good to go //
    state = VisionState.RUNNING;
    break;
  case RUNNING:
    // Get camera1 image and test connectivity //
    if (!testModeEnabled) {
      // Make sure the network table is okay //
      /*if (!networkTable.isConnected()) {
        //println("ERROR: Network table disconnected");
        state = VisionState.CONNECTING;
        return;
      }*/

      // Make sure the camera1 has new data for us //
      if (!camera1.isAvailable()) return;

      // Load a new image //
      camera1.read();
      camera2.read();
      frame = camera1;
    } else {
      // Load up sample image //
      frame = loadImage(testImageName);
      //frame.resize(viewWidth, viewHeight);
    }

    // Filter image by color to search for green //
    filteredFrame = filterImageHSBRange(frame, hueMin, hueMax, satMin, satMax, valMin, valMax);

    // Display base image //
    image(displayFilteredImage ? filteredFrame : frame, 0, 0, viewWidth, viewHeight);
    image(displayFilteredImage ? filteredFrame : camera2, viewWidth + 50, 0, viewWidth, viewHeight);

    // Load filtered image into OpenCV //
    //filteredFrame.resize(viewWidth, viewHeight);
    opencv.loadImage(filteredFrame);

    // Find blobs / contours //
    potentialTargets = opencv.findContours(true, true); //Find contours, counting holes and order by size
    //println(mouseX + " " + mouseY);
    
    // Apply filters //
    filteredTargets = new ArrayList<Contour>();
    boolean passedFilters = true;
    for (Contour contour : potentialTargets) {
      for (ContourFilter filter : filterList) {
        if (!filter.doesContourPass(contour)) {
          /*System.out.println(contour.getBoundingBox().getX() + " " + contour.getBoundingBox().getY());
          System.out.println(float(contour.getBoundingBox().width) / float(contour.getBoundingBox().height));
          System.out.println(filter);*/ 
          //delay(1000);
          contour.draw();
          passedFilters = false;
          break;
        }
      }

      if (passedFilters) filteredTargets.add(contour);
    }
    
    // Find closest target to center of the screen based on X position //
    Contour closestTarget = null;
    float distanceToClosestTarget = -1; 
    float distanceToTarget;
    
    for (Contour contour : filteredTargets) {
      distanceToTarget = abs((imageCenterX * inputWidth) - ((float) (contour.getBoundingBox().getCenterX())));
      if (distanceToClosestTarget == -1 || distanceToTarget < distanceToClosestTarget) {
        distanceToClosestTarget = distanceToTarget;
        closestTarget = contour;
      }
    }

    // Draw stuff //
    /*for (Contour contour : filteredTargets) {
      contour.setPolygonApproximationFactor(polygonDetectionStrictness);
      ArrayList<PVector> points = contour.getPolygonApproximation().getPoints();

      // Draw outlines //
      if (displayOutlines) {
        noFill();
        stroke(outlineColor);
        strokeWeight(targetOutlineThickness);

        beginShape();
        for (PVector point : points) {
          vertex(point.x * xDisplayMultiplier, point.y * yDisplayMultiplier * yMysteryMultiplier);
        }
        vertex(points.get(0).x * xDisplayMultiplier, points.get(0).y * yDisplayMultiplier * yMysteryMultiplier);
        endShape();
      }

      // Draw bounding boxes //
      if (displayBoundingBoxes) {
        noFill();
        stroke(boundingBoxColor);
        strokeWeight(boundingBoxOutlineThickness);
        rectMode(CORNER);
        rect(contour.getBoundingBox().x * xDisplayMultiplier, contour.getBoundingBox().y * yDisplayMultiplier * yMysteryMultiplier, contour.getBoundingBox().width * xDisplayMultiplier, contour.getBoundingBox().height * yDisplayMultiplier * yMysteryMultiplier);
      }
      
      // Draw crosshairs //
      if (displayTargetCrosshairs && closestTarget != null) {
        noFill();
        strokeWeight(crosshairThickness);
        stroke(contour == closestTarget ? closestCrosshairColor : defaultCrosshairColor);
        line(((float) contour.getBoundingBox().getCenterX()) * xDisplayMultiplier, ((float) (contour.getBoundingBox().getCenterY() * yMysteryMultiplier * yDisplayMultiplier)) + (crosshairLength / 2), ((float) contour.getBoundingBox().getCenterX()) * xDisplayMultiplier, (((float) contour.getBoundingBox().getCenterY() * yMysteryMultiplier * yDisplayMultiplier) - (crosshairLength / 2)));
        line(((float) contour.getBoundingBox().getCenterX() * xDisplayMultiplier) - (crosshairLength / 2), (float) contour.getBoundingBox().getCenterY() * yDisplayMultiplier * yMysteryMultiplier, (((float) contour.getBoundingBox().getCenterX() * xDisplayMultiplier)+ (crosshairLength / 2)), (float) contour.getBoundingBox().getCenterY() * yDisplayMultiplier * yMysteryMultiplier);
      }
    }
    
    // Draw screen crosshairs //
    if (displayScreenCrosshairs) {
      noFill();
      strokeWeight(screenCrosshairThickness);
      stroke(screenCrosshairColor);
      line(0, float(viewHeight) * imageCenterY, width, float(viewHeight) * imageCenterY);
      line(float(viewWidth) * imageCenterX, 0, float(viewWidth) * imageCenterX, height);
    }*/
    
    if (closestTarget == null) return; //Close current frame if no targets are detected
    
    // Calculate error values //
    float normalizedCenterX = (float) closestTarget.getBoundingBox().getCenterX() / float(inputWidth);
    float normalizedCenterY = (float) closestTarget.getBoundingBox().getCenterY() / float(inputHeight);
    double closestXError = (imageCenterX - normalizedCenterX) * (inputHeight) / ((float) closestTarget.getBoundingBox().height); //Normalized to height; positive = aiming too far right
    double closestYError = (imageCenterY - normalizedCenterY) * (inputHeight) / ((float) closestTarget.getBoundingBox().height); //Normalized to height; positive = aiming too far down
      
    // Update network tables //
    /*if (!testModeEnabled) {
        networkTable.putNumber(networkTableXErrorField, closestXError);
        networkTable.putNumber(networkTableYErrorField, closestYError);
    }*/
    
    // TODO: Text display //
    break;
  case ERROR:
    background(255); //White background
    break;
  default:
    println("ERROR: Unrecognized state");
    state = VisionState.ERROR;
    break;
  }
}

// Calculation functions below //
PImage filterImageHSBRange(PImage img, float minHue, float maxHue, float minSat, float maxSat, float minValue, float maxValue) {
  PImage output = createImage(img.width, img.height, HSB);
  output.loadPixels();
  img.loadPixels();
  int imgPixelCount = img.width * img.height;
  color examinedPixel; 
  float hue;
  float sat;
  float val;

  for (int i = 0; i < imgPixelCount; i++) {
    examinedPixel = img.pixels[i];
    hue = hue(examinedPixel);
    sat = saturation(examinedPixel);
    val = brightness(examinedPixel);
    if (withinRange(hue, minHue, maxHue) && withinRange(sat, minSat, maxSat) && withinRange(val, minValue, maxValue)) { //Good pixel
      output.pixels[i] = color(0, 0, 100); //Add white pixel
    } else { //Bad pixel
      output.pixels[i] = color(0, 0, 0); //Add black pixel
    }
  }

  output.updatePixels();
  img.updatePixels();
  return output;
}

boolean withinRange (float number, float min, float max) { //Utility function that checks if a number is within a range
  if (number > max || number < min) return false;
  return true;
}

float getContourArea(Contour contour) { //Use Green's Theorem to compute the area of a contour 
  float area = 0;
  ArrayList<PVector> points = contour.getPoints();
  int numPoints = points.size();

  for (int i = 0; i < numPoints - 1; i++) {
    area += (points.get(i).x * points.get(i + 1).y);
    area -= (points.get(i).y * points.get(i + 1).x);
  }
  area += points.get(numPoints - 1).x * points.get(0).y;  
  area -= points.get(numPoints - 1).y * points.get(0).x;
  return abs(area) / 2;
}