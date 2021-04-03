import java.util.ArrayList;
import java.util.List;

/**
 * An abstract shape class.
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected Point2D position;
  protected ColorRGB color;
  protected ShapeProperty shapeProperty;
  protected TimePeriod period;
  protected List<ITransformation> transformationList;

  /**
   * Construct the initial AbstractShape.
   *
   * @param name     the name of the shape.
   * @param position the position of the shape.
   * @param color    the color of the shape.
   * @param shapeProperty the shapeProperties of the shape.
   */
  public AbstractShape(String name, Point2D position, ColorRGB color, ShapeProperty shapeProperty, TimePeriod period) {
    this.name = name;
    this.position = position;
    this.color = color;
    this.shapeProperty = shapeProperty;;
    this.period = period;
    this.transformationList = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Point2D getPosition() {
    return position;
  }
  
   @Override
  public void setPosition(Point2D point2D) {this.position = point2D;}


  @Override
  public ColorRGB getColor() {
    return color;
  }
  
  @Override
  public void setColor( ColorRGB color) {
    this.color = color;
  }


@Override
  public ShapeProperty getShapeProperty() {
    return this.shapeProperty;
  }

  @Override
  public void setShapeProperty(ShapeProperty shapeProperty) {
    this.shapeProperty = shapeProperty;
  }


  @Override
  public TimePeriod getPeriod(){ return  period;}

  @Override
  public void addTransformations(ITransformation t) {
    transformationList.add(t);
  }

  @Override
  public void removeTransformations(ITransformation t) {
   transformationList.remove(t);
  }

  @Override
  public List<ITransformation> getTransformationList() {
    return transformationList;
  }

  public void setTransformationList(List<ITransformation> transformationList) {
    this.transformationList = transformationList;
  }
}
