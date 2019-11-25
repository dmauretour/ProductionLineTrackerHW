package sample;

/** @author Dory Mauretour sreen class implemts ScreenSpec */
public class Screen implements ScreenSpec {
  private final String resolution;
  private final int refreshrate;
  private final int responsetime;

  /**
   * @param resolution pass a string value
   * @param refreshrate pass an integer value
   * @param responsetime pass an integer
   */
  public Screen(String resolution, int refreshrate, int responsetime) {
    this.resolution = resolution;
    this.refreshrate = refreshrate;
    this.responsetime = responsetime;
  }
  /** Setters and getters return string return integer return integer */
  public String getResolution() {
    return resolution;
  }

  public int getRefreshRate() {
    return refreshrate;
  }

  public int getResponseTime() {
    return responsetime;
  }

  /** @return str */
  public String toString() {
    String str4 =
        String.format(
            "\nResolution: %s\nRefresh Rate: %s\nResponse Time: %s",
            resolution, refreshrate, responsetime);
    return str4;
  }
}
