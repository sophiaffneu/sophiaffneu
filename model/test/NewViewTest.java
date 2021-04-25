import org.junit.Test;

import cs5004.animator.view.IView;
import cs5004.animator.view.NewView;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test for new view.
 */
public class NewViewTest {
  private IView view;

  @Test
  public void testNewView(){
    view = new NewView(20,50,100,80,5);
    assertEquals(view.getViewType(),"playback");
    assertEquals(view.getStartButton().getActionCommand(),"START");
    assertEquals(view.getResumeButton().getActionCommand(),"RESUME");
    assertEquals(view.getRestartButton().getActionCommand(),"RESTART");
    assertEquals(view.getPauseButton().getActionCommand(),"PAUSE");
    assertEquals(view.getIncreaseSpeedB().getActionCommand(),"INCREASE SPEED");
    assertEquals(view.getDecreaseSpeedB().getActionCommand(),"DECREASE SPEED");
    assertEquals(view.getCycleButton().getActionCommand(),"CYCLE");
    assertEquals(view.getStopCycleButton().getActionCommand(),"STOPCYCLE");
    assertEquals(view.getSaveSVGButton().getActionCommand(),"SAVESVG");
    assertEquals(view.getSaveTextButton().getActionCommand(),"SAVETEXT");
  }
}
