package cs5004.animator.view;

import cs5004.animator.model.IAnimator;

public class SVGView {
  private IAnimator model;
  private int tempo;
  public SVGView(IAnimator model, int tempo){
    this.model = model;
    this.tempo = tempo;
  }


  public Appendable getOutPut(){
    String svgInfo = "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n";


  }
}
