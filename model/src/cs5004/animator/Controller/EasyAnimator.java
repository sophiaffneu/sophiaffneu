package cs5004.animator.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import cs5004.animator.model.IAnimator;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;


public final class EasyAnimator {
  public static void main(String[] args) throws IOException {
    int speed; //ticks per second.
    AnimationReader reader = new AnimationReader();
    AnimationBuilderImpl builder = new AnimationBuilderImpl();
    BufferedReader objReader = new BufferedReader(new FileReader("E:\\IdeaProjects\\Assignments\\Assignment7\\src\\txt\\toh-8.txt"));
    IAnimator newM = (IAnimator) reader.parseFile(objReader, builder);

   /*
    a view factory here
     */

    // visualview as a place holder here for now, it will change to whatever view factory populate later.


    AnimatorController animatorController = new AnimatorController(newM);
    animatorController.go();

  }
}
