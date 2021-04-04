/**
 * This enum represents the Shape type.
 */
public enum ShapeType {
  RECTANGLE, OVAL;

  @Override
  public String toString() {
    if (this == RECTANGLE) {
      return "rectangle";
    } else {
      return "oval";
    }
  }
}
