package cs5004.animator.view;

import java.util.List;

import java.awt.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;
import cs5004.animator.model.Move;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Scale;
import cs5004.animator.model.ShapeProperty;
import cs5004.animator.model.ShapeType;
import cs5004.animator.model.TimePeriod;

  public class SVGViewNoModel implements IView{
    private List<IShape> shapeList;
    private List<ITransformation> transList;
    private int width;
    private int height;
    private int tempo;
    private boolean loop;
    private int endTime;

    public SVGViewNoModel(List<IShape> shapeList, List<ITransformation> transList, int tempo, int width, int height) {
      this.shapeList = shapeList;
      this.transList = transList;
      this.width = width;
      this.height = height;
      if (tempo > 0) {
        this.tempo = tempo;
      } else {
        throw new IllegalArgumentException("tempo can't be negative");
      }
      this.loop = false;
      this.endTime = 0;
    }

    public void loopOrNot(boolean loop, int endTime) {
      this.loop = loop;
      this.endTime = endTime;
    }


    @Override
    public SwingPanel getPanel() {
      return null;
    }

    @Override
    public void refresh() {

    }

    @Override
    public String getViewType() {
      return "SVGview";
    }

    public void getOutPut() {
      String svgInfo = "<svg width=\"" + this.width + "\" height=\"" + this.height
              + "\" version=\"1.1\"\n"
              + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n";

      for (IShape s : shapeList) {
        svgInfo += this.loopInfo(s);
      }

      svgInfo += this.getSVGShape(shapeList);

      svgInfo += "</svg>";
      System.out.println(svgInfo);
    }

    @Override
    public String play() {
      return null;
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


    private String getSVGShape(List<IShape> shapeList) {
      String output = "";
      for (IShape s : shapeList) {
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
        for (ITransformation t : transList) {
          double start = t.getTimePeriod().getStart() * 1000 / (double) tempo;
          double end = t.getTimePeriod().getEnd() * 1000 / (double) tempo;
          if (t.getTransShape().getName() == s.getName()
                  && t.getTransShape().getShapeType() == s.getShapeType()) {
            switch (t.getTransType().toString()) {
              case "move":
                if (t.getTransShape().getShapeType() == ShapeType.OVAL) {
                  if (t.getToPosition().getX() != t.getPosition().getX()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + end + "ms\" attributeName=\"cx\""
                            + " from=\"" + t.getPosition().getX() + "\" to=\""
                            + t.getToPosition().getX() + "\" fill=\"freeze\" />\n";
                  }
                  if (t.getToPosition().getY() != t.getPosition().getY()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + end + "ms\" attributeName=\"cy\""
                            + " from=\"" + t.getPosition().getY() + "\" to=\""
                            + t.getToPosition().getY() + "\" fill=\"freeze\" />\n";
                  }
                } else {
                  if (t.getToPosition().getX() != t.getPosition().getX()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + end + "ms\" attributeName=\"x\""
                            + " from=\"" + t.getPosition().getX() + "\" to=\""
                            + t.getToPosition().getX() + "\" fill=\"freeze\" />\n";
                  }
                  if (t.getToPosition().getY() != t.getPosition().getY()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + end + "ms\" attributeName=\"y\""
                            + " from=\"" + t.getPosition().getY() + "\" to=\""
                            + t.getToPosition().getY() + "\" fill=\"freeze\" />\n";
                  }
                }
                break;
              case "scale":
                if (t.getTransShape().getShapeType() == ShapeType.OVAL) {
                  if (t.getToShapeProperty().getOne() != t.getShapeProperty().getOne()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + end + "ms\" attributeName=\"rx\""
                            + " from=\"" + t.getShapeProperty().getOne() + "\" to=\""
                            + t.getToShapeProperty().getOne() + "\" fill=\"freeze\" />\n";
                  }
                  if (t.getToShapeProperty().getTwo() != t.getShapeProperty().getTwo()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + end + "ms\" attributeName=\"ry\""
                            + " from=\"" + t.getShapeProperty().getTwo() + "\" to=\""
                            + t.getToShapeProperty().getTwo() + "\" fill=\"freeze\" />\n";
                  }
                } else {
                  if (t.getToShapeProperty().getOne() != t.getShapeProperty().getOne()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + end + "ms\" attributeName=\"width\""
                            + " from=\"" + t.getShapeProperty().getOne() + "\" to=\""
                            + t.getToShapeProperty().getOne() + "\" fill=\"freeze\" />\n";
                  }
                  if (t.getToShapeProperty().getTwo() != t.getShapeProperty().getTwo()) {
                    output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + end + "ms\" attributeName=\"height\""
                            + " from=\"" + t.getShapeProperty().getTwo() + "\" to=\""
                            + t.getToShapeProperty().getTwo() + "\" fill=\"freeze\" />\n";
                  }
                }
                break;
              case "change color":
                if (t.getToColor().getRed() != t.getColor().getRed()
                        || t.getToColor().getGreen() != t.getColor().getGreen()
                        || t.getToColor().getBlue() != t.getColor().getBlue()) {
                  output += "    <animate attributeType=\"xml\" begin=\"" + start + "ms\" dur=\"" + end + "ms\" attributeName=\"color\""
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
        }
      }
      return output;
    }

  }

