package cs5004.animator.Controller;

import java.awt.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ShapeProperty;
import cs5004.animator.model.TimePeriod;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.VisualView;

public final class EasyAnimator {
  public static void main(String[] args) throws IOException {
    int speed;
    AnimationReader reader = new AnimationReader();
    AnimationBuilderImpl builder = new AnimationBuilderImpl();
    BufferedReader objReader = new BufferedReader(new FileReader("E:\\IdeaProjects\\Assignments\\Assignment7\\code\\buildings.txt"));
    IAnimator newM = (IAnimator) reader.parseFile(objReader, builder);
    List nList = newM.getTransList();

    //code for testing newM.

    for (Object n : nList) {
      ITransformation k = (ITransformation) n;
      System.out.println("easyanimator" + k.toString());
    }

   /*
    a view factory here
     */

    // visualview as a place holder here for now, it will change to whatever view factory populate later.


    AnimatorController animatorController = new AnimatorController(newM);
    animatorController.go();

  }
}
