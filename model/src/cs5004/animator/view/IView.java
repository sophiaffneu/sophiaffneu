package cs5004.animator.view;

public interface IView {

  SwingPanel getPanel();

  void refresh();

  String getViewType();

  void getOutPut();

  String play();
}
