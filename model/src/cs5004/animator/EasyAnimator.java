package cs5004.animator;

import cs5004.animator.controller.AnimatorController;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;
import cs5004.animator.view.ViewFactory;

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
public class EasyAnimator {
  /**
   * Run the animator.
   *
   * @param args command line
   */
  public static void main(String[] args) {
    AnimationBuilder builder = new AnimationBuilderImpl();
    AnimationModel model;

    Readable in = new StringReader("");
    int tick = 1;
    String viewType = "";
    FileWriter writer = null;
    Appendable out = System.out;

    if (!(Arrays.asList(args).contains("-in")
        && Arrays.asList(args).contains("-view"))) {
      JOptionPane.showMessageDialog(null,
          "input and view needed");
    }

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        try {
          in = new FileReader(args[i + 1]);
        } catch (FileNotFoundException e) {
          JOptionPane.showMessageDialog(null, "file not found");
        } catch (IndexOutOfBoundsException e) {
          JOptionPane.showMessageDialog(null, "file needed");
        }
      }

      if (args[i].equals("-view")) {
        try {
          viewType = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          JOptionPane.showMessageDialog(null, "view type needed");
        }
      }

      if (args[i].equals("-out")) {
        try {
          writer = new FileWriter(args[i + 1]);
        } catch (IOException e) {
          JOptionPane.showMessageDialog(null, "file can't be created");
        } catch (IndexOutOfBoundsException e) {
          JOptionPane.showMessageDialog(null, "output file needed");
        }
      }

      if (args[i].equals("-speed")) {

        try {
          int speed = Integer.parseInt(args[i + 1]);
          if (speed > 0) {
            tick = speed;
          } else {
            JOptionPane.showMessageDialog(null, "invalid speed");
          }
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(null, "invalid speed");
        } catch (IndexOutOfBoundsException e) {
          JOptionPane.showMessageDialog(null, "speed needed");
        }
      }
    }

    model = AnimationReader.parseFile(in, builder);
    IView view = new ViewFactory().generateView(viewType, model, tick);
    AnimatorController animatorController = new AnimatorController(view, model, tick);
    animatorController.goToView(view);

    try {
      if (writer != null) {
        writer.write(view.getOutPut());
        writer.close();
      } else {
        if (!view.getViewType().equals("visual")) {
          out.append(view.getOutPut());
        }
      }

    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "can't write on file");
    }

  }

}