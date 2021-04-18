package cs5004.animator.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.*;
import cs5004.animator.model.Rectangle;
import cs5004.animator.view.IView;

public class TextViewNoModel implements IView {
  private String outputTemp;
  private List<IShape> shapeList;
  private List<ITransformation> transList;

  public TextViewNoModel(List<IShape> shapeList, List<ITransformation> transList) {
    this.shapeList = shapeList;
    this.transList = transList;
  }

  @Override
  public SwingPanel getPanel() {
    return null;
  }

  public void refresh() {
  }

  public String getViewType() {
    return "text";
  }

  public void getOutPut() {
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
      output += s.getName() + " appears at time t=" + begin + " and disappears at time t=" + end + "\n";
    }
    output += "\n\n";

    List<ITransformation> sortedList = transList.stream().sorted(Comparator.comparingInt(t ->
            t.getTimePeriod().getStart())).collect(Collectors.toList());
    for (ITransformation t : sortedList) {
      output += t.toString() + "\n";
    }
    outputTemp = output;
  }

  @Override
  public String play() {
    return outputTemp;
  }
}
