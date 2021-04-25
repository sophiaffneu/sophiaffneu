package cs5004.animator.view;

import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;
import cs5004.animator.model.ShapeType;

import java.util.List;

/**
 * This is a SVGView class. It represents the svg of the animation.
 */
public class SVGView implements IView {
  private int tempo;
  private boolean loop;
  private int endTime;
  private String outputTemp;

  private List<IShape> shapeList;
  private List<ITransformation> transList;
  private int width;
  private int height;
  private int x;
  private int y;

  /**
   * Construct the SVG view.
   *
   * @param shapeList a list of shapes
   * @param transList a list of transformations
   * @param tempo     tempo
   * @param width     width of the model
   * @param height    height of the model
   * @param x         x of the model
   * @param y         y of the model
   */
  public SVGView(List<IShape> shapeList, List<ITransformation> transList, int tempo,
                 int width, int height, int x, int y) {
    this.shapeList = shapeList;
    this.transList = transList;
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;

    if (tempo > 0) {
      this.tempo = tempo;
    } else {
      throw new IllegalArgumentException("tempo can't be negative");
    }
    this.loop = false;
    this.endTime = 0;
  }

  @Override
  public SwingPanel getPanel() {
    return null;
  }

  @Override
  public void refresh() {
    // interface requirement
  }

  @Override
  public String getViewType() {
    return "svg";
  }

  /**
   * public void loopOrNot(boolean loop, int endTime) { this.loop = loop; this.endTime = endTime; }
   */

  @Override
  public String getOutPut() {
    return outputTemp;
  }

  @Override
  public void play() {
    String svgInfo = "<svg width=\"" + width + "\" height=\"" + height
        + "\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n";

    for (IShape s : shapeList) {
      svgInfo += this.loopInfo(s);
    }

    svgInfo += this.getSVGInfo();

    svgInfo += "</svg>";
    outputTemp = svgInfo;
  }

  private String loopInfo(IShape s) {
    String output = "";
    String type = "";
    if (s.getShapeType().toString().equals("rectangle")) {
      type = "rect";
    } else {
      type = "ellipse";
    }
    if (loop) {
      output += "<" + type + ">\n";
      output += "    <animate id=\"base\" begin=\"0;base.end\" dur=\"" + endTime * 1000 / tempo
          + "ms\"" + " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n";
      output += "</" + type + ">\n\n";
    }
    return output;
  }

  private String getSVGInfo() {
    String output = "";
    for (IShape s : shapeList) {

      if (s.getShapeType() == ShapeType.RECTANGLE) {
        output += "<rect id=\"" + s.getName() + "\" x=\"" + (s.getPosition().getX() - x)
            + "\" y=\"" + (s.getPosition().getY() - y)
            + "\" width=\"" + s.getShapeProperty().getOne()
            + "\" height=\"" + s.getShapeProperty().getTwo()
            + "\" fill=\"rgb(" + s.getColor().getRed()
            + "," + s.getColor().getGreen()
            + "," + s.getColor().getBlue()
            + ")\" visibility=\"visible\" >\n";
      } else {
        output += "<ellipse id=\"" + s.getName() + "\" cx=\"" + (s.getPosition().getX() - x)
            + "\" cy=\"" + (s.getPosition().getY() - x)
            + "\" rx=\"" + s.getShapeProperty().getOne()
            + "\" ry=\"" + s.getShapeProperty().getTwo()
            + "\" fill=\"rgb(" + s.getColor().getRed()
            + "," + s.getColor().getGreen()
            + "," + s.getColor().getBlue()
            + ")\" visibility=\"visible\" >\n";
      }


      for (ITransformation t : transList) {
        double start = t.getTimePeriod().getStart() * 1000 / (double) tempo;
        double end = t.getTimePeriod().getEnd() * 1000 / (double) tempo;
        double dur = end - start;
        if (t.getTransShape().getName().equals(s.getName())
            && t.getTransShape().getShapeType() == s.getShapeType()) {
          switch (t.getTransType().toString()) {
            case "move":
              if (t.getTransShape().getShapeType() == ShapeType.OVAL) {
                if (t.getToPosition().getX() != t.getPosition().getX()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\""
                      + dur + "ms\" attributeName=\"cx\""
                      + " from=\"" + (t.getPosition().getX() - x) + "\" to=\""
                      + (t.getToPosition().getX() - x) + "\" fill=\"freeze\" />\n";
                }
                if (t.getToPosition().getY() != t.getPosition().getY()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\""
                      + dur + "ms\" attributeName=\"cy\""
                      + " from=\"" + (t.getPosition().getY() - y) + "\" to=\""
                      + (t.getToPosition().getY() - y) + "\" fill=\"freeze\" />\n";
                }
              } else {
                if (t.getToPosition().getX() != t.getPosition().getX()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\""
                      + dur + "ms\" attributeName=\"x\""
                      + " from=\"" + (t.getPosition().getX() - x) + "\" to=\""
                      + (t.getToPosition().getX() - x) + "\" fill=\"freeze\" />\n";
                }
                if (t.getToPosition().getY() != t.getPosition().getY()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\""
                      + dur + "ms\" attributeName=\"y\""
                      + " from=\"" + (t.getPosition().getY() - y) + "\" to=\""
                      + (t.getToPosition().getY() - y) + "\" fill=\"freeze\" />\n";
                }
              }
              break;
            case "scale":
              if (t.getTransShape().getShapeType() == ShapeType.OVAL) {
                if (t.getToShapeProperty().getOne() != t.getShapeProperty().getOne()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\""
                      + dur + "ms\" attributeName=\"rx\""
                      + " from=\"" + t.getShapeProperty().getOne() + "\" to=\""
                      + t.getToShapeProperty().getOne() + "\" fill=\"freeze\" />\n";
                }
                if (t.getToShapeProperty().getTwo() != t.getShapeProperty().getTwo()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\""
                      + dur + "ms\" attributeName=\"ry\""
                      + " from=\"" + t.getShapeProperty().getTwo() + "\" to=\""
                      + t.getToShapeProperty().getTwo() + "\" fill=\"freeze\" />\n";
                }
              } else {
                if (t.getToShapeProperty().getOne() != t.getShapeProperty().getOne()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\""
                      + dur + "ms\" attributeName=\"width\""
                      + " from=\"" + t.getShapeProperty().getOne() + "\" to=\""
                      + t.getToShapeProperty().getOne() + "\" fill=\"freeze\" />\n";
                }
                if (t.getToShapeProperty().getTwo() != t.getShapeProperty().getTwo()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\""
                      + dur + "ms\" attributeName=\"height\""
                      + " from=\"" + t.getShapeProperty().getTwo() + "\" to=\""
                      + t.getToShapeProperty().getTwo() + "\" fill=\"freeze\" />\n";
                }
              }
              break;
            case "change color":
              if (t.getToColor().getRed() != t.getColor().getRed()
                  || t.getToColor().getGreen() != t.getColor().getGreen()
                  || t.getToColor().getBlue() != t.getColor().getBlue()) {
                output += "    <animate attributeType=\"css\" begin=\"" + start + "ms\" dur=\""
                    + dur + "ms\" attributeName=\"fill\""
                    + " from=\"rgb(" + t.getColor().getRed() + ","
                    + t.getColor().getGreen() + ","
                    + t.getColor().getBlue() + ")\" to=\"rgb("
                    + t.getToColor().getRed() + ","
                    + t.getToColor().getGreen() + ","
                    + t.getToColor().getBlue() + ")\" fill=\"freeze\" />\n";
              }
              break;
            default:
              break;
          }
        }
      }

      if (s.getShapeType() == ShapeType.OVAL) {
        output += "</ellipse>\n\n";
      } else {
        output += "</rect>\n\n";
      }

    }
    return output;
  }
}