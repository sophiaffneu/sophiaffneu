package cs5004.animator.view;

import cs5004.animator.model.IShape;
import cs5004.animator.model.ITransformation;
import cs5004.animator.model.TransType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;

/**
 * This is a TextView class. It represents a text of animation.
 */
public class TextView implements IView {
  private String outputTemp;
  private List<IShape> shapeList;
  private List<ITransformation> transList;

  /**
   * Construct the text view.
   *
   * @param shapeList a list of shapes
   * @param transList a list of transformations
   */
  public TextView(List<IShape> shapeList, List<ITransformation> transList) {
    this.shapeList = shapeList;
    this.transList = transList;
  }

  @Override
  public SwingPanel getPanel() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public String getViewType() {
    return "text";
  }

  @Override
  public String getOutPut() {
    return outputTemp;
  }

  @Override
  public void play() {
    String output = "";
    for (IShape shape : shapeList) {
      output += shape.toString() + "\n";
    }
    output += "\n\n";

    for (IShape s : shapeList) {
      List<ITransformation> singleList = new ArrayList<>();
      for (ITransformation t : transList) {
        if (t.getTransShape().getName().equals(s.getName())
            && t.getTransShape().getShapeType() == s.getShapeType()) {
          singleList.add(t);
        }
      }
      List<ITransformation> sortedList = singleList.stream().sorted(Comparator.comparingInt(t ->
          t.getTimePeriod().getStart())).collect(Collectors.toList());

      int begin = sortedList.get(0).getTimePeriod().getStart();
      int end = sortedList.get(sortedList.size() - 1).getTimePeriod().getEnd();
      output += s.getName() + " appears at time t=" + begin + " and disappears at time t=" + end
          + "\n";
    }
    output += "\n\n";

    List<ITransformation> sortedList = transList.stream().sorted(Comparator.comparingInt(t ->
        t.getTimePeriod().getStart())).collect(Collectors.toList());
    for (ITransformation t : sortedList) {
      if (t.getTransType() != TransType.NOCHANGE) {
        output += t.toString() + "\n";
      }

    }
    outputTemp = output;
  }



  @Override
  public JButton getStartButton() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public JButton getResumeButton() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public JButton getPauseButton() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public JButton getRestartButton() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public JButton getIncreaseSpeedB() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public JButton getDecreaseSpeedB() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public JButton getCycleButton() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public JButton getStopCycleButton() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public JButton getSaveSVGButton() {
    throw new UnsupportedOperationException("unsupported");
  }

  @Override
  public JButton getSaveTextButton() {
    throw new UnsupportedOperationException("unsupported");
  }
}