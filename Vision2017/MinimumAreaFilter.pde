public class MinimumAreaFilter implements ContourFilter {
  private int minimumPixels;
  
  public MinimumAreaFilter(int minPixels) {
    minimumPixels = minPixels;
  }
  
  boolean doesContourPass(Contour contour) {
    return contour.area() > minimumPixels;
  }
}