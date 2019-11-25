package sample;

/** @author Dory Mauretour MoviePlayer extends Product implements MultimediaControl */
public class MoviePlayer extends Product implements MultimediaControl {
  private final Screen screen;
  private final MonitorType monitorType;

  /**
   * @param name pass a string
   * @param manufacturer pass a string
   * @param screen pass Screen
   * @param monitorType pass MonitorType
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  public void play() {
    System.out.println("Playing movie");
  }

  public void stop() {
    System.out.println("Stopping movie");
  }

  public void previous() {
    System.out.println("Previous movie");
  }

  public void next() {
    System.out.println("Next movie");
  }

  /**
   * toString
   *
   * @return string
   */
  @Override
  public String toString() {
    return String.format("%s\nScreen: %s\nMonitorType: %s", super.toString(), screen, monitorType);
  }
}
