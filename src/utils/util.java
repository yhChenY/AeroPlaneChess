package utils;

public class util {
  /**
   * @param min radom minimum
   * @param max random maxmum
   * @return a number between min and max,included.
   */
  public static int random(int min, int max) {
    int ans;
    int t = Math.abs(max - min);
    ans = (int) (Math.random() * (t + 1)) + min;
    return ans;
    //Tested. Possibility is the same.
  }
  /**
   * @param s password to be examined
   * @return whether s is legitimate
   */
  public static boolean Allowed(String s) {
    if (s.isEmpty()) {
      return false;
    }
    if (s.length()<8||s.length() > 16) {
      return false;
    }
    boolean temp = true;
    for (int i = 0; i < s.length(); i++) {
      if (!isOK(s.charAt(i))) {
        temp = false;
        break;
      }
    }
    return temp;
  }
  
  /**
   * @param c char to be examined
   * @return whether c is allowed
   */
  private static boolean isOK(char c) {
    return c >= '!' && c <= '~';
  }
//  private boolean betAZ(char c) {
//    return c >= 'A' && c <= 'Z';
//  }
//
//  private boolean betaz(char c) {
//    return c >= 'a' && c <= 'z';
//  }

}
