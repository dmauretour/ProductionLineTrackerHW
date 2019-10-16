package sample;

public class AudioPlayer extends Product implements MultimediaControl {

  // The class will have 2 fields
  String audioSpecification;
  String mediaType;
  // Create a constructor that will take in 2 parameters – name and audioSpecification.
  // The constructor should call its parents constructor and also setup the media type.
  public AudioPlayer(
      String manufacturer, String name, String audioSpecification, String mediaType) {
    super(mediaType, manufacturer, name);
    this.audioSpecification = audioSpecification;
    this.mediaType = mediaType;
  }
  /*Implement the methods from the MultimediaControl interface by simply writing the action
  to the console. E.g. in play System.out.println("Playing");
  Normally we would have code that would instruct the media player to play,
  but we will simply display a message.*/
  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopped");
  }

  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }
  /*Create a toString method that will display the superclasses toString method,
  but also add rows for Audio Spec and Type.*/

  public void ToString() {
    System.out.print(super.toString());
    System.out.print(
        "\n Audio specification: " + this.audioSpecification + " \n Media Type: " + this.mediaType);
  }
}
