package GAMING;

public enum Color {
  RED("red"),
  GREEN("green"),
  YELLOW("yellow"),
  BLUE("blue");
  private final String color;
  
  Color(String color) {
    this.color = color;
  }
  
  public String getColor() {
    return color;
  }
}
