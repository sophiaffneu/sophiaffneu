package cs5004.animator;

import cs5004.animator.controller.AnimatorController;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;
import cs5004.animator.view.ViewFactory;
import cs5004.animator.view.VisualView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * An easy animator.
 */
public class MainForTestingView {
  /**
   * Run the animator.
   *
   * @param args command line
   */
  public static void main(String[] args) throws FileNotFoundException {
    AnimationBuilder builder = new AnimationBuilderImpl();
    AnimationModel model;
    AnimationReader reader = new AnimationReader();

    FileReader objReader =new FileReader("E:\\IdeaProjects\\Assignments\\Assignment7\\src\\txt\\smalldemo" +
            ".txt");

    int tick = 1;

    model = AnimationReader.parseFile(objReader, builder);
    VisualView v  = new VisualView(model.getX(), model.getY(), model.getWidth(), model.getHeight(), 50);
    AnimatorController animatorController = new AnimatorController(v, model, tick);
    animatorController.goToView(v);
  }

}