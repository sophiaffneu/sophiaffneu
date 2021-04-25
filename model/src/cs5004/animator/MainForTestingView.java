package cs5004.animator;

import cs5004.animator.controller.AnimatorController;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;
import cs5004.animator.view.NewView;
import cs5004.animator.view.VisualView;

import java.io.FileNotFoundException;
import java.io.FileReader;

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

    FileReader objReader =new FileReader("E:\\IdeaProjects\\Assignments\\Assignment7\\src\\txt\\buildings" +
            ".txt");

    int tick = 1;

    model = AnimationReader.parseFile(objReader, builder);
    IView v  = new NewView(200,70, 420,550, 50);
    AnimatorController animatorController = new AnimatorController(v, model, 50);
    animatorController.goToView(v);
  }

}