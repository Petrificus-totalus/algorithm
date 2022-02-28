package Stack;

public class Palindrome {

  // 回文字符串
  public static boolean test(String str) {
    int length = str.length();
    ArrayStack arr = new ArrayStack(length);

    // 字符串每个字符压栈
    for (int i = 0; i < length; i++) {
      arr.push(str.charAt(i));
    }

    // 弹栈拼起来
    StringBuffer s = new StringBuffer(length);
    while (!arr.isEmpty()) {
      s.append((char) arr.pop());
    }

    return str.equals(s.toString());
  }

  public static void main(String[] args) {

    String str = "racecar";
    System.out.println(test(str));
  }
}
