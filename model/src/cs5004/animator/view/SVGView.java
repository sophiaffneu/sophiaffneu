package cs5004.animator.view;

import java.awt.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ShapeProperty;
import cs5004.animator.model.ShapeType;
import cs5004.animator.model.TransType;

public class SVGView {
  private IAnimator model;
  private int tempo;
  private boolean loop;
  private int endTime;

  public SVGView(IAnimator model, int tempo) {
    this.model = model;
    if (tempo > 0) {
      this.tempo = tempo;
    }else{
      throw new IllegalArgumentException("tempo can't be negative");
    }
    this.loop = false;
    this.endTime = 0;
  }

  public void loopOrNot(boolean loop, int endTime){
    this.loop = loop;
    this.endTime = endTime;
  }




  public void getOutPut() {
    String svgInfo = "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n";

    for(IShape s: model.getShapeList()){
      svgInfo += this.getSVGShape(s);
    }
    System.out.println(svgInfo);
  }

  private String getSVGShape(IShape s){
    String output = "";
    if(s.getShapeType()== ShapeType.RECTANGLE){
      if(loop) {
        output += "<rect>\n";
        output += "    <animate id=\"base\" begin=\"0;base.end\" dur=\"" + endTime*1000/tempo + "ms\""
            + " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n";
        output += "<rect>\n\n";
      }
      output += "<rect id=\"" + s.getName() +"\" x=\"" + s.getPosition().getX()
          +"\" y=\"" + s.getPosition().getY()
          +"\" width=\"" + s.getShapeProperty().getOne()
          +"\" height=\"" + s.getShapeProperty().getTwo()
          +"\" fill=\"rgb(" +(int)Math.floor(s.getColor().getRed() * 255)
          + "," + (int)Math.floor(s.getColor().getGreen() * 255)
          + "," + (int)Math.floor(s.getColor().getBlue() * 255)
          + ")\" visibility=\"visible\" >\n";
    }
    return output;
  }

  private String getSVGTrans(IShape s, ITransformation t){
    //double start = s.getPeriod().getStart() * 1000/ (double) tempo;
    //double end = s.getPeriod().getEnd() * 1000/(double) tempo;
    String output = "";

    /**
    if(t.getTransShape().getName()== s.getName()){
      if(t.getTransType()== TransType.MOVE)
      output+= "    <animate attributeType=\"xml\" begin=\"" + start+ "ms\" dur=\"" + end + "ms\""
          + " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n";//not yet
    }*/
    return output;
  }
  public static void main(String[] args) {
    IAnimator model = new AnimationModel();
    Rectangle r = new Rectangle("R", new Point2D(200, 200),
        new Color(1, 0, 0), new ShapeProperty(50, 100));
    Oval c = new Oval("C", new Point2D(500, 100),
        new Color(0, 0, 1), new ShapeProperty(60, 30));

    model.addShape(r);
    model.addShape(c);
    SVGView svg = new SVGView(model,1);
    svg.loopOrNot(false,100);
    svg.getOutPut();
  }
}
