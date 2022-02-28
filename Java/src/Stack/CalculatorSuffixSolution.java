package Stack;

/**
 * A.先将表达式转为后缀表达式
 * 从头到尾读取中缀表达式的每个对象，对不同对象按不同的情况处理。
 * 1 运算数:直接输出;
 * 2 左括号:压入堆栈;
 * 3 右括号:将栈顶的运算符弹出并输出，直到遇到左括号(出栈，不输出);
 * 4 运算符:
 * • 若优先级大于栈顶运算符时，则把它压栈;
 * • 若优先级小于等于栈顶运算符时，将栈顶运算符弹出并输出;再比
 * 较新的栈顶运算符，直到该运算符大于栈顶运算符优先级为止，然
 * 后将该运算符压栈;
 * 5 若各对象处理完毕，则把堆栈中存留的运算符一并输出。
 * <p>
 * B. 对后缀表达式依次进行
 * 1 运算数:直接压栈;
 * 2 运算符:出栈两个计算后将结果压栈。注意运算顺序: 后出栈的 运算符 先出栈的
 */
public class CalculatorSuffixSolution {

  // 将表达式转为后缀表达式
  public static String toSuffix(String str) {
    int length = str.length();
    String res = "";
    ArrayStack a = new ArrayStack(length);

    for (int i = 0; i < length; i++) {
      char ch = str.charAt(i);
      if (ch >= '0' && ch <= '9') {
        res += ch;
      } else {
        if (ch == '(' || a.isEmpty()) {
          a.push(ch);
        } else if (ch == ')') {
          while ((char) a.top() != '(') {
            res += (char) a.pop();
          }
          a.pop(); // 把 ( 弄出来
        } else {
          while (!a.isEmpty() && priority(ch) <= priority((char) a.top())) {
            res += (char) a.pop();
          }
          a.push(ch);
        }
      }
    }
    // 剩余的全部弹出来
    while (!a.isEmpty()) {
      res += (char) a.pop();
    }
    return res;
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

  // 得到最后结果
  public static int getResult(String str) {
    int length = str.length();
    ArrayStack a = new ArrayStack(length);

    for (int i = 0; i < length; i++) {
      char ch = str.charAt(i);
      if (ch > '0' && ch < '9') {
        a.push(ch - '0');
      } else {
        int num1 = a.pop();
        int num2 = a.pop();
        a.push(calculate(num2, num1, ch));
      }
    }
    return a.pop();
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


  public static void main(String[] args) {
    String str = "(1+2)*3+4-(5+6)*7";
    String res = toSuffix(str);

    System.out.println(res);
    System.out.println(getResult(res));
  }
}
