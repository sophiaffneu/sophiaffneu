package cs5004.animator.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.IAnimator;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGViewNoModel;
import cs5004.animator.view.TextView;
import cs5004.animator.view.TextViewNoModel;


public final class EasyAnimatorOld {
  public static void main(String[] args) throws IOException {
    int speed = 0; //ticks per second.
    String viewType = "";
    AnimationReader reader = new AnimationReader();
    AnimationBuilderImpl builder = new AnimationBuilderImpl();
    BufferedReader objReader = new BufferedReader(new FileReader("E:\\IdeaProjects\\Assignments\\Assignment7\\src\\txt\\smalldemo" +
            ".txt"));
    IAnimator newM = (IAnimator) reader.parseFile(objReader, builder);

   /*
    a view factory here
     */

    // visualview as a place holder here for now, it will change to whatever view factory populate later.
    TextViewNoModel t = new TextViewNoModel(newM.getShapeList(), newM.getTransList());


    SVGViewNoModel s = new SVGViewNoModel(newM.getShapeList(), newM.getTransList(),100 ,newM.getWidth(), newM.getHeight() );

    AnimatorController animatorController = new AnimatorController(t, newM, speed);
    //animatorController.go();

  }
}
