import org.junit.Before;

public class TransformationTest {
  private ITransformation m;

  @Before
  public void setup(){
    IShape R = new Rectangle("R", new Point2D(200.0, 200.0),
        new ColorRGB(1.0, 0.0, 0.0), new ShapeProperty(50, 100),
        new TimePeriod(1, 100));
    TimePeriod p = new TimePeriod(30,60);
  }


}
