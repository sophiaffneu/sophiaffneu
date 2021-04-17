package cs5004.animator.view;

public interface IView {
  void refresh();

  String getViewType();

  void play();

  String getOutPut();
}
