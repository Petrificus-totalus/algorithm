package Stack;


/**
 * 双栈实现
 * 1 操作数直接入左栈
 * 2 运算符:
 * a 栈空直接入右栈
 * b '(': 直接入右栈
 * c 不空看优先级:
 * • 大于栈顶直接入右栈
 * • 小于等于栈顶则从左栈弹出两个，
 * 右栈弹出一个运算符做计算，结果压入左栈...
 * 直到大于右栈栈顶优先级
 * d ')': 运算弹栈直到遇到 '(' , '('也要弹栈
 */

public class CalculatorDoubleStack {

  public static int calculate(String str) {
    int length = str.length();
    ArrayStack a1 = new ArrayStack(length);
    ArrayStack a2 = new ArrayStack(length);

    for (int i = 0; i < length; i++) {
      char ch = str.charAt(i);
      if (ch >= '0' && ch <= '9') {
        a1.push(ch - '0');
      } else {
        if (a2.isEmpty() || ch == '(') {
          a2.push(ch);
        } else if (ch == ')') {
          while ((char) a2.top() != '(') {
            calculateAndPush(a1, a2);
          }
          a2.pop(); // 弹出 (
        } else {
          while (!a2.isEmpty() && (priority(ch) <= priority((char) a2.top()))) {
            calculateAndPush(a1, a2);
          }
          a2.push(ch);
        }
      }
    }
    while (!a2.isEmpty()) {
      calculateAndPush(a1, a2);
    }
    return a1.pop();
  }

  // 左栈弹出两个数，右栈弹出运算符，将运算结果压入左栈
  public static void calculateAndPush(ArrayStack a1, ArrayStack a2) {
    int num1 = a1.pop();
    int num2 = a1.pop();
    char c = (char) a2.pop();
    a1.push(calculate(num2, num1, c));
  }

  public static int calculate(int a, int b, char ch) {
    switch (ch) {
      case '+':
        return a + b;
      case '-':
        return a - b;
      case '*':
        return a * b;
      default:
        return a / b;
    }
  }

  public static int priority(char ch) {
    switch (ch) {
      case '*':
      case '/':
        return 2;
      case '+':
      case '-':
        return 1;
      default:
        return 0;
    }
  }

  public static void main(String[] args) {
    String str = "8-9/(2+1)";
    System.out.println(calculate(str));
  }
}
