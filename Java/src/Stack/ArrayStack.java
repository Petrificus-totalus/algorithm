package Stack;

public class ArrayStack {

  private int maxSize;  // 最大长度
  private int[] stack;
  private int top = -1;

  public ArrayStack(int maxSize) {
    this.maxSize = maxSize;
    this.stack = new int[maxSize];
  }

  public boolean isFull() {
    return this.top == this.maxSize - 1;
  }

  public boolean isEmpty() {
    return this.top == -1;
  }

  public int pop() {
    if (isEmpty()) {
      throw new RuntimeException("栈为空");
    }
    return this.stack[top--];
  }

  public int top() {
    if (isEmpty()) {
      throw new RuntimeException("栈为空");
    }
    return this.stack[top];
  }

  public void push(int value) {
    if (isFull()) {
      throw new RuntimeException("栈满了");
    }

    this.stack[++top] = value;
  }

  /**
   * 查看栈中所有元素
   */
  public void list() {

    //是否是空栈
    if (isEmpty()) {
      throw new RuntimeException("空栈，未找到数据");
    }
    for (int i = 0; i < this.size(); i++) {
      System.out.printf("stack[%d]=%d\n", i, this.stack[i]);
    }

  }

  public int size() {
    return this.top + 1;
  }

  public static void main(String[] args) {


    ArrayStack arr = new ArrayStack(6);

//    arr.push(24);arr.list();
//    arr.push(1);arr.list();
//    arr.push(90);arr.list();

    arr.pop();
    arr.list();
    arr.pop();
    arr.list();
    arr.pop();

  }
}
