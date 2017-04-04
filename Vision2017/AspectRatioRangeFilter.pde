public class AspectRatioRangeFilter implements ContourFilter {
  private float minimumAspectRatio;
  private float maximumAspectRatio;
  
  public AspectRatioRangeFilter(float minAspectRatio, float maxAspectRatio) {
    minimumAspectRatio = minAspectRatio;
    maximumAspectRatio = maxAspectRatio;
  }
  
  boolean doesContourPass(Contour contour) {
    float aspectRatio = float(contour.getBoundingBox().width) / (float(contour.getBoundingBox().height) * yMysteryMultiplier);
    return withinRange(aspectRatio, minimumAspectRatio, maximumAspectRatio);
  }
}