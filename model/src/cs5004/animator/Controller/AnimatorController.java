package cs5004.animator.controller;

import cs5004.animator.model.IAnimator;
import cs5004.animator.model.IShape;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 * This class represents an animation controller.
 */
public class AnimatorController implements ActionListener {

  IAnimator m;
  IView v;
  int elapsedTime = 0;
  int speed;
  Timer timer;
  boolean cycleFlag = false;

  /**
   * Construct the initial controller.
   *
   * @param view  the view
   * @param model the model
   * @param tempo the tempo
   */
  public AnimatorController(IView view, IAnimator model, int tempo) {
    this.v = view;
    this.m = model;
    this.speed = tempo;
  }

  /**
   * Run the view.
   *
   * @param v view
   */
  public void goToView(IView v) {
    if (v.getViewType().equals("text")
        || v.getViewType().equals("svg")) {
      v.play();
    } else if (v.getViewType().equals("visual")) {
      timer = new Timer(1000 / speed, this);
      timer.start();
    } else if (v.getViewType().equals("playback")) {
      v.getStartButton().addActionListener(this);
      v.getResumeButton().addActionListener(this);
      v.getRestartButton().addActionListener(this);
      v.getPauseButton().addActionListener(this);
      v.getIncreaseSpeedB().addActionListener(this);
      v.getDecreaseSpeedB().addActionListener(this);
      v.getCycleButton().addActionListener(this);
      v.getStopCycleButton().addActionListener(this);
      v.getSaveSVGButton().addActionListener(this);
      v.getSaveTextButton().addActionListener(this);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (v.getViewType().equals("playback")) {
      if (e.getSource() == v.getPauseButton()) {
        if (timer != null) {
          timer.stop();
        }
      } else if (e.getSource() == v.getResumeButton()) {
        if (timer != null) {
          timer.start();
        }
      } else if (e.getSource() == v.getStartButton()) {
        if (timer == null) {
          timer = new Timer(1000 / speed, this);
          timer.start();
        }
      } else if (e.getSource() == v.getRestartButton()) {
        if (timer != null) {
          timer.start();
        }
        elapsedTime = 0;
        elapsedTime += 1000 / this.speed;
        List<IShape> shapeAtTick = m.getShapeAtTick(elapsedTime, speed);
        v.getPanel().setShapeAtTick(shapeAtTick);
        if (shapeAtTick == null) {
          timer.stop();
        }
        v.refresh();
      } else if (e.getSource() == timer) {
        elapsedTime += 1000 / this.speed;
        List<IShape> shapeAtTick = m.getShapeAtTick(elapsedTime, speed);
        if (e.getSource() == v.getIncreaseSpeedB()
            || e.getSource() == v.getDecreaseSpeedB()) {
          // speed =
          elapsedTime += 1000 / this.speed;
          shapeAtTick = m.getShapeAtTick(elapsedTime, speed);
        }
        v.getPanel().setShapeAtTick(shapeAtTick);
        if (shapeAtTick == null) {
          if (cycleFlag) {
            elapsedTime = 0;
          } else {
            timer.stop();
          }
        }
        v.refresh();
      } else if (e.getSource() == v.getCycleButton()) {
        cycleFlag = true;
        if (timer == null) {
          timer = new Timer(1000 / speed, this);
          timer.start();
        }
      } else if (e.getSource() == v.getStopCycleButton()) {
        cycleFlag = false;
        timer.stop();
        elapsedTime = 0;
      } else if (e.getSource() == v.getSaveSVGButton()) {
        try {
          saveSVG();
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      } else if (e.getSource() == v.getSaveTextButton()) {
        try {
          saveText();
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
    } else if (v.getViewType().equals("visual")) {
      elapsedTime += 1000 / speed;
      List<IShape> shapeAtTick = m.getShapeAtTick(elapsedTime, speed);
      if (shapeAtTick == null) {
        timer.stop();
      }
      v.getPanel().setShapeAtTick(shapeAtTick);
      v.refresh();
    }
  }


  private void saveSVG() throws IOException {
    FileWriter writer = null;
    try {
      writer = new FileWriter("out.svg");
    } catch (IOException s) {
      JOptionPane.showMessageDialog(null, "file can't be created");
    }

    SVGView view = new SVGView(m.getShapeList(), m.getTransList(), speed,
        m.getWidth(), m.getHeight(), m.getX(), m.getY());
    view.play();
    writer.write(view.getOutPut());
    writer.close();
  }

  private void saveText() throws IOException {
    FileWriter writer = null;
    try {
      writer = new FileWriter("out.txt");
    } catch (IOException s) {
      JOptionPane.showMessageDialog(null, "file can't be created");
    }
    TextView view = new TextView(m.getShapeList(), m.getTransList());
    view.play();
    writer.write(view.getOutPut());
    writer.close();
  }
}
















