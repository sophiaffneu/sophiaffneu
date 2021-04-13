package cs5004.animator.model;

/**
 * An enum for transformation type.
 */
public enum TransType {
  MOVE, SCALE, CHANGECOLOR, NOCHANGE;

  @Override
  public String toString(){
    if(this == MOVE){
      return "move";
    }else if(this == SCALE){
      return "scale";
    }else if(this == CHANGECOLOR){
      return "change color";
    }else{
      return "no change";
    }
  }
}
