package cs5004.animator.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;
import cs5004.animator.model.Move;
import cs5004.animator.model.NoChange;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Scale;
import cs5004.animator.model.ShapeProperty;
import cs5004.animator.model.ShapeType;
import cs5004.animator.model.TimePeriod;

public class SVGView implements IView {
  private IAnimator model;
  private int tempo;
  private boolean loop;
  private int endTime;
  private String outputTemp;

  public SVGView(IAnimator model, int tempo) {
    this.model = model;
    if (tempo > 0) {
      this.tempo = tempo;
    } else {
      throw new IllegalArgumentException("tempo can't be negative");
    }
    this.loop = false;
    this.endTime = 0;
  }

  public void refresh() {
  }

  public String getViewType() {
    return "svg";
  }

  public void loopOrNot(boolean loop, int endTime) {
    this.loop = loop;
    this.endTime = endTime;
  }

  public String getOutPut() {
    return outputTemp;
  }

  public void play() {
    String svgInfo = "<svg width=\"" + model.getWidth() + "\" height=\"" + model.getHeight()
        + "\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n";

    for (IShape s : model.getShapeList()) {
      svgInfo += this.loopInfo(s);
    }

    svgInfo += this.getInitial();
    //svgInfo += this.getSVGShape();

    svgInfo += "</svg>";
    outputTemp = svgInfo;
  }

  private String loopInfo(IShape s) {
    String output = "";
    String type = "";
    switch (s.getShapeType().toString()) {
      case "rectangle":
        type = "rect";
        break;
      case "oval":
        type = "ellipse";
        break;
    }
    if (loop) {
      output += "<" + type + ">\n";
      output += "    <animate id=\"base\" begin=\"0;base.end\" dur=\"" + endTime * 1000 / tempo + "ms\""
          + " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n";
      output += "</" + type + ">\n\n";
    }
    return output;
  }

  private String getInitial() {
    String output = "";
    for (IShape s : model.getShapeList()) {

      if (s.getShapeType() == ShapeType.RECTANGLE) {
        output += "<rect id=\"" + s.getName() + "\" x=\"" + s.getPosition().getX()
            + "\" y=\"" + s.getPosition().getY()
            + "\" width=\"" + s.getShapeProperty().getOne()
            + "\" height=\"" + s.getShapeProperty().getTwo()
            + "\" fill=\"rgb(" + s.getColor().getRed()
            + "," + s.getColor().getGreen()
            + "," + s.getColor().getBlue()
            + ")\" visibility=\"visible\" >\n";
      } else {
        output += "<ellipse id=\"" + s.getName() + "\" cx=\"" + s.getPosition().getX()
            + "\" cy=\"" + s.getPosition().getY()
            + "\" rx=\"" + s.getShapeProperty().getOne()
            + "\" ry=\"" + s.getShapeProperty().getTwo()
            + "\" fill=\"rgb(" + s.getColor().getRed()
            + "," + s.getColor().getGreen()
            + "," + s.getColor().getBlue()
            + ")\" visibility=\"visible\" >\n";
      }




      for (ITransformation t : model.getTransList()) {
        double start = t.getTimePeriod().getStart() * 1000 / (double) tempo;
        double end = t.getTimePeriod().getEnd() * 1000 / (double) tempo;
        double dur = end - start;
        if (t.getTransShape().getName().equals(s.getName())
            && t.getTransShape().getShapeType() == s.getShapeType()) {
          switch (t.getTransType().toString()) {
            case "move":
              if (t.getTransShape().getShapeType() == ShapeType.OVAL) {
                if (t.getToPosition().getX() != t.getPosition().getX()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"cx\""
                      + " from=\"" + t.getPosition().getX() + "\" to=\""
                      + t.getToPosition().getX() + "\" fill=\"freeze\" />\n";
                }
                if (t.getToPosition().getY() != t.getPosition().getY()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"cy\""
                      + " from=\"" + t.getPosition().getY() + "\" to=\""
                      + t.getToPosition().getY() + "\" fill=\"freeze\" />\n";
                }
              } else {
                if (t.getToPosition().getX() != t.getPosition().getX()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"x\""
                      + " from=\"" + t.getPosition().getX() + "\" to=\""
                      + t.getToPosition().getX() + "\" fill=\"freeze\" />\n";
                }
                if (t.getToPosition().getY() != t.getPosition().getY()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"y\""
                      + " from=\"" + t.getPosition().getY() + "\" to=\""
                      + t.getToPosition().getY() + "\" fill=\"freeze\" />\n";
                }
              }
              break;
            case "scale":
              if (t.getTransShape().getShapeType() == ShapeType.OVAL) {
                if (t.getToShapeProperty().getOne() != t.getShapeProperty().getOne()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"rx\""
                      + " from=\"" + t.getShapeProperty().getOne() + "\" to=\""
                      + t.getToShapeProperty().getOne() + "\" fill=\"freeze\" />\n";
                }
                if (t.getToShapeProperty().getTwo() != t.getShapeProperty().getTwo()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"ry\""
                      + " from=\"" + t.getShapeProperty().getTwo() + "\" to=\""
                      + t.getToShapeProperty().getTwo() + "\" fill=\"freeze\" />\n";
                }
              } else {
                if (t.getToShapeProperty().getOne() != t.getShapeProperty().getOne()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"width\""
                      + " from=\"" + t.getShapeProperty().getOne() + "\" to=\""
                      + t.getToShapeProperty().getOne() + "\" fill=\"freeze\" />\n";
                }
                if (t.getToShapeProperty().getTwo() != t.getShapeProperty().getTwo()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"height\""
                      + " from=\"" + t.getShapeProperty().getTwo() + "\" to=\""
                      + t.getToShapeProperty().getTwo() + "\" fill=\"freeze\" />\n";
                }
              }
              break;
            case "change color":
              if (t.getToColor().getRed() != t.getColor().getRed()
                  || t.getToColor().getGreen() != t.getColor().getGreen()
                  || t.getToColor().getBlue() != t.getColor().getBlue()) {
                output += "    <animate attributeType=\"css\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"fill\""
                    + " from=\"rgb(" + t.getColor().getRed() + ","
                    + t.getColor().getGreen() + ","
                    + t.getColor().getBlue() + ")\" to=\"rgb("
                    + t.getToColor().getRed() + ","
                    + t.getToColor().getGreen() + ","
                    + t.getToColor().getBlue() + ")\" fill=\"freeze\" />\n";
              }
              break;
          }
        }
      }

      if (s.getShapeType() == ShapeType.OVAL) {
        output += "</ellipse>\n\n";
      } else {
        output += "</rect>\n\n";
      }

    }return output;
  }


    private String getSVGShape () {
      String output = "";

      for (IShape s : model.getShapeList()) {
/**
 if (s.getShapeType() == ShapeType.RECTANGLE) {
 output += "<rect id=\"" + s.getName() + "\" x=\"" + s.getPosition().getX()
 + "\" y=\"" + s.getPosition().getY()
 + "\" width=\"" + s.getShapeProperty().getOne()
 + "\" height=\"" + s.getShapeProperty().getTwo()
 + "\" fill=\"rgb(" + s.getColor().getRed()
 + "," + s.getColor().getGreen()
 + "," + s.getColor().getBlue()
 + ")\" visibility=\"visible\" >\n";
 } else {
 output += "<ellipse id=\"" + s.getName() + "\" cx=\"" + s.getPosition().getX()
 + "\" cy=\"" + s.getPosition().getY()
 + "\" rx=\"" + s.getShapeProperty().getOne()
 + "\" ry=\"" + s.getShapeProperty().getTwo()
 + "\" fill=\"rgb(" + s.getColor().getRed()
 + "," + s.getColor().getGreen()
 + "," + s.getColor().getBlue()
 + ")\" visibility=\"visible\" >\n";
 }*/
        for (ITransformation t : model.getTransList()) {
          double start = t.getTimePeriod().getStart() * 1000 / (double) tempo;
          double end = t.getTimePeriod().getEnd() * 1000 / (double) tempo;
          double dur = end - start;
          if (t.getTransShape().getName() == s.getName()
              && t.getTransShape().getShapeType() == s.getShapeType()) {
            switch (t.getTransType().toString()) {
              case "move":
                if (t.getTransShape().getShapeType() == ShapeType.OVAL) {
                  if (t.getToPosition().getX() != t.getPosition().getX()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"cx\""
                        + " from=\"" + t.getPosition().getX() + "\" to=\""
                        + t.getToPosition().getX() + "\" fill=\"freeze\" />\n";
                  }
                  if (t.getToPosition().getY() != t.getPosition().getY()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"cy\""
                        + " from=\"" + t.getPosition().getY() + "\" to=\""
                        + t.getToPosition().getY() + "\" fill=\"freeze\" />\n";
                  }
                } else {
                  if (t.getToPosition().getX() != t.getPosition().getX()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"x\""
                        + " from=\"" + t.getPosition().getX() + "\" to=\""
                        + t.getToPosition().getX() + "\" fill=\"freeze\" />\n";
                  }
                  if (t.getToPosition().getY() != t.getPosition().getY()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"y\""
                        + " from=\"" + t.getPosition().getY() + "\" to=\""
                        + t.getToPosition().getY() + "\" fill=\"freeze\" />\n";
                  }
                }
                break;
              case "scale":
                if (t.getTransShape().getShapeType() == ShapeType.OVAL) {
                  if (t.getToShapeProperty().getOne() != t.getShapeProperty().getOne()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"rx\""
                        + " from=\"" + t.getShapeProperty().getOne() + "\" to=\""
                        + t.getToShapeProperty().getOne() + "\" fill=\"freeze\" />\n";
                  }
                  if (t.getToShapeProperty().getTwo() != t.getShapeProperty().getTwo()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"ry\""
                        + " from=\"" + t.getShapeProperty().getTwo() + "\" to=\""
                        + t.getToShapeProperty().getTwo() + "\" fill=\"freeze\" />\n";
                  }
                } else {
                  if (t.getToShapeProperty().getOne() != t.getShapeProperty().getOne()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"width\""
                        + " from=\"" + t.getShapeProperty().getOne() + "\" to=\""
                        + t.getToShapeProperty().getOne() + "\" fill=\"freeze\" />\n";
                  }
                  if (t.getToShapeProperty().getTwo() != t.getShapeProperty().getTwo()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"height\""
                        + " from=\"" + t.getShapeProperty().getTwo() + "\" to=\""
                        + t.getToShapeProperty().getTwo() + "\" fill=\"freeze\" />\n";
                  }
                }
                break;
              case "change color":
                if (t.getToColor().getRed() != t.getColor().getRed()
                    || t.getToColor().getGreen() != t.getColor().getGreen()
                    || t.getToColor().getBlue() != t.getColor().getBlue()) {
                  output += "    <animate attributeType=css\"x\" begin=\"" + start + "ms\" dur=\"" + dur + "ms\" attributeName=\"fill\""
                      + " from=\"rgb(" + t.getColor().getRed() + ","
                      + t.getColor().getGreen() + ","
                      + t.getColor().getBlue() + ")\" to=\"rgb("
                      + t.getToColor().getRed() + ","
                      + t.getToColor().getGreen() + ","
                      + t.getToColor().getBlue() + ")\" fill=\"freeze\" />\n";
                }
                break;
            }
          }
        }

        if (s.getShapeType() == ShapeType.OVAL) {
          output += "</ellipse>\n\n";
        } else {
          output += "</rect>\n\n";
        }
        /**
         if (s.getShapeType() == ShapeType.RECTANGLE) {
         output += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"" +
         "1ms\" attributeName=\"x\" to=\"" + s.getPosition().getX() + "\" fill=\"freeze\" />\n";
         output += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"" +
         "1ms\" attributeName=\"y\" to=\"" + s.getPosition().getY() + "\" fill=\"freeze\" />\n";
         output += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"" +
         "1ms\" attributeName=\"width\" to=\"" + s.getShapeProperty().getOne() + "\" fill=\"freeze\" />\n";
         output += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"" +
         "1ms\" attributeName=\"height\" to=\"" + s.getShapeProperty().getTwo() + "\" fill=\"freeze\" />\n";
         output += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"" +
         "1ms\" attributeName=\"color\" to=\"rgb(" + s.getColor().getRed()
         + "," + s.getColor().getGreen() + "," + s.getColor().getBlue() + ")\" fill=\"freeze\" />\n";
         output += "</rect>\n\n";
         } else {
         output += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"" +
         "1ms\" attributeName=\"cx\" to=\"" + s.getPosition().getX() + "\" fill=\"freeze\" />\n";
         output += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"" +
         "1ms\" attributeName=\"cy\" to=\"" + s.getPosition().getY() + "\" fill=\"freeze\" />\n";
         output += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"" +
         "1ms\" attributeName=\"rx\" to=\"" + s.getShapeProperty().getOne() + "\" fill=\"freeze\" />\n";
         output += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"" +
         "1ms\" attributeName=\"ry\" to=\"" + s.getShapeProperty().getTwo() + "\" fill=\"freeze\" />\n";
         output += "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"" +
         "1ms\" attributeName=\"color\" to=\"rgb(" + s.getColor().getRed()
         + "," + s.getColor().getGreen() + "," + s.getColor().getBlue() + ")\" fill=\"freeze\" />\n";
         output += "</ellipse>\n\n";
         }*/
      }
      return output;
    }


    public static void main (String[]args){
      IAnimator model = new AnimationModel();
      model.setBounds(145, 50, 410, 199);
      Rectangle disk1 = new Rectangle("disk1", new Point2D(190 ,161),
          new Color(113, 87, 151), new ShapeProperty(20, 11));

      Rectangle disk2 = new Rectangle("disk2", new Point2D(183,172),
          new Color(35, 173, 73),new ShapeProperty(32, 11));

      IShape copy1 = disk1.copyShape();
      IShape copy2 = disk2.copyShape();

      model.addShape(disk1);
      model.addShape(disk2);

      ITransformation no1 = new NoChange(copy1,new TimePeriod(1,25));

      ITransformation move1 = new Move(copy1, new TimePeriod(25, 35), new Point2D(190, 50));
      ITransformation no2 = new NoChange(copy1, new TimePeriod(35, 36));
      ITransformation move2 = new Move(copy1, new TimePeriod(36, 46), new Point2D(340, 50 ));



      model.addTransformations(move1);
      model.addTransformations(move2);
      model.addTransformations(no2);
      model.addTransformations(no1);


      SVGView svg = new SVGView(model, 1);
      //svg.loopOrNot(true, 100000000);
      svg.play();
      System.out.println(svg.getOutPut());
    }
  }
