package cs5004.animator.view;

import java.awt.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ShapeProperty;
import cs5004.animator.model.TimePeriod;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;

public final class EasyAnimator {
  public static void main(String[] args) throws IOException {
    AnimationModel m = new AnimationModel();
     AnimationReader reader = new AnimationReader();
    AnimationBuilderImpl builder = new AnimationBuilderImpl();
    BufferedReader objReader = new BufferedReader(new FileReader("E:\\IdeaProjects\\Assignments\\Assignment7\\code\\toh-5.txt"));
    IAnimator newM = (IAnimator) reader.parseFile(objReader, builder);
   List nList = newM.getShapeList();
   for(Object n : nList){
     IShape k = (IShape)n;
     System.out.println(k.toString());
   }
   VisualView n = new VisualView(20,170, 360, 360, nList);
  }
}
