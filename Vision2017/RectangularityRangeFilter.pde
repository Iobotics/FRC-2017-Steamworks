public class RectangularityRangeFilter implements ContourFilter {
  private float minimumRectangularity;
  private float maximumRectangularity;
  
  public RectangularityRangeFilter(float minRectangularity, float maxRectangularity) {
    minimumRectangularity = minRectangularity;
    maximumRectangularity = maxRectangularity;  
  }
  
  boolean doesContourPass(Contour contour) {
    float rectangularity = getContourArea(contour) / contour.area();
    return withinRange(rectangularity, minimumRectangularity, maximumRectangularity);
  }
}