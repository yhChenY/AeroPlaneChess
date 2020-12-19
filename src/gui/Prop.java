package gui;

public class Prop {
  private final String propName;
  private final String photoAddress;
  private final String instruction;

  public Prop(String propName, String photoAddress, String instruction) {
    this.propName = propName;
    this.photoAddress = photoAddress;
    this.instruction = instruction;
  }

  public String getPropName() {
    return propName;
  }

  public String getPhotoAddress() {
    return photoAddress;
  }

  public String getInstruction() {
    return instruction;
  }
}
