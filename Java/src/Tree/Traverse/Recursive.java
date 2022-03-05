package Tree.Traverse;

import java.util.Stack;

public class Recursive {
  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int v) {
      value = v;
    }
  }

  /**
   * 1. 头节点压栈
   * 2. 弹出栈顶记为cur并输出
   * 3. cur有右压入右，有左压入左，先右再左。回到2
   */
  public static void pre(Node head) {
    System.out.print("pre-order: ");
    if (head != null) {
      Stack<Node> stack = new Stack<Node>();
      stack.add(head);
      while (!stack.isEmpty()) {
        head = stack.pop();
        System.out.println(head.value);
        if (head.right != null) {
          stack.push(head.right);
        }
        if (head.left != null) {
          stack.push(head.left);
        }
      }
    }
    System.out.println();
  }

  /**
   * 1. 当前节点为cur，cur的所有left依次压栈直到null
   * 2. 弹栈记为cur并输出，cur = cur.right 回到1
   */
  public static void mid(Node cur) {
    System.out.print("mid-order: ");
    if (cur != null) {
      Stack<Node> stack = new Stack<Node>();
      while (!stack.isEmpty() || cur != null) {
        if (cur != null) {
          stack.push(cur);
          cur = cur.left;
        } else {
          cur = stack.pop();
          System.out.print(cur.value + " ");
          cur = cur.right;
        }
      }
    }
    System.out.println();
  }


  /**
   * 对于先序遍历，若先压入右，再压入左，输出时就是头右左的顺序。
   * 如果不输出，而是压入另一个栈，最后再全部弹栈，得到的就是左右头
   */

  public static void pos1(Node head) {
    System.out.print("pos-order: ");
    if (head != null) {
      Stack<Node> s1 = new Stack<Node>();
      Stack<Node> s2 = new Stack<Node>();
      s1.push(head);
      while (!s1.isEmpty()) {
        head = s1.pop(); // 头 右 左
        s2.push(head);
        if (head.left != null) {
          s1.push(head.left);
        }
        if (head.right != null) {
          s1.push(head.right);
        }
      }
      // 左 右 头
      while (!s2.isEmpty()) {
        System.out.print(s2.pop().value + " ");
      }
    }
    System.out.println();
  }

  /**
   * 一个栈实现，不必掌握
   */
  public static void pos2(Node h) {
    System.out.print("pos-order: ");
    if (h != null) {
      Stack<Node> stack = new Stack<Node>();
      stack.push(h);
      Node c = null;
      while (!stack.isEmpty()) {
        c = stack.peek();
        if (c.left != null && h != c.left && h != c.right) {
          stack.push(c.left);
        } else if (c.right != null && h != c.right) {
          stack.push(c.right);
        } else {
          System.out.print(stack.pop().value + " ");
          h = c;
        }
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Node head = new Node(1);
    head.left = new Node(2);
    head.right = new Node(3);
    head.left.left = new Node(4);
    head.left.right = new Node(5);
    head.right.left = new Node(6);
    head.right.right = new Node(7);

    pre(head);
    System.out.println("========");
    mid(head);
    System.out.println("========");
    pos1(head);
    System.out.println("========");
    pos2(head);
    System.out.println("========");
  }
}
