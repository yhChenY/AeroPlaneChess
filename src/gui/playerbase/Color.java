package gui.playerbase;

public enum Color {
  BLUE("blue"), GREEN("green"), RED("red"), YELLOW("yellow");

  private String colorName;

  Color(String name) {
    colorName = name;
  }

  public String getColorName() {
    return colorName;
  }
}
