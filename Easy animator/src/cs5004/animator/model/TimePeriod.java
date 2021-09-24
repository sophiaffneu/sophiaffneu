package cs5004.animator.model;

/**
 * This class represents the time period of a shape.
 */
public class TimePeriod {
  private int start;
  private int end;

  /**
   * Construct the initial time period.
   *
   * @param start start time
   * @param end   end time
   */
  public TimePeriod(int start, int end) {
    if (end >= start && start >= 0) {
      this.start = start;
      this.end = end;
    } else {
      throw new IllegalArgumentException("invalid time period");
    }
  }

  /**
   * Return the start time.
   *
   * @return the start time
   */
  public int getStart() {
    return start;
  }

  /**
   * Return the end time.
   *
   * @return the end time
   */
  public int getEnd() {
    return end;
  }
}
