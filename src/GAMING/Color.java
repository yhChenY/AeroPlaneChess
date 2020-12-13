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
  
  public static Color getColor(String s) {
   if(s.equals("RED"))return RED;
   if(s.equals("GREEN"))return GREEN;
   if(s.equals("YELLOW"))return YELLOW;
   return BLUE;
  }
}
