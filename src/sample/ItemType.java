package sample;

/** @author Dory Mauretour Enum ItemType */
public enum ItemType {
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  public final String code;

  ItemType(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
