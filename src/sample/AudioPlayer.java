package sample;

public class AudioPlayer extends Product implements MultimediaControl {
  String audioSpecification;
  String mediaType;

  public AudioPlayer(
      String name, String manufacturer, String audioSpecification, String mediaType) {
    super(name, manufacturer, ItemType.AUDIO);
    this.audioSpecification = audioSpecification;
    this.mediaType = mediaType;
  }

  public String getAudioSpecification() {
    return this.audioSpecification;
  }

  public String getMediaType() {
    return this.mediaType;
  }

  public void setAudioSpecification(String audioSpecification) {
    this.audioSpecification = audioSpecification;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  public void play() {
    System.out.println("Playing");
  }

  public void stop() {
    System.out.println("Stopping");
  }

  public void previous() {
    System.out.println("Previous");
  }

  public void next() {
    System.out.println("Next");
  }

  public String toString() {
    return String.format(
        "%s\nSupported Audio Formats: %s\nSupported Playlist Formats: %s",
        super.toString(), this.audioSpecification, this.mediaType);
  }
}
