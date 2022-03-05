package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SerializeAndReconstructTree {

  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }
  public static Queue<String> preSerial(Node head) {
    Queue<String> ans = new LinkedList<>();
    pres(head, ans);
    return ans;
  }

  public static void pres(Node head, Queue<String> ans){
    if(head == null){
      ans.add(null);
    }else{
      ans.add(String.valueOf(head.value));
      pres(head.left, ans);
      pres(head.right, ans);
    }
  }

  public static Node buildByPreQueue(Queue<String> prelist) {
    if (prelist == null || prelist.size() == 0) {
      return null;
    }
    return preb(prelist);
  }

  public static Node preb(Queue<String> prelist) {
    String value = prelist.poll();
    if (value == null) {
      return null;
    }
    Node head = new Node(Integer.valueOf(value));
    head.left = preb(prelist);
    head.right = preb(prelist);
    return head;
  }

  public static Queue<String> postSerial(Node head) {
    Queue<String> ans = new LinkedList<>();
    post(head, ans);
    return ans;
  }

  public static void post(Node head, Queue<String> ans) {
    if (head == null) {
      ans.add(null);
    } else {
      post(head.left, ans);
      post(head.right, ans);
      ans.add(String.valueOf(head.value));
    }
  }

  public static Node buildByPosQueue(Queue<String> poslist) {
    if (poslist == null || poslist.size() == 0) {
      return null;
    }
    // 左右中  ->  stack(中右左)
    Stack<String> stack = new Stack<>();
    while (!poslist.isEmpty()) {
      stack.push(poslist.poll());
    }
    return posb(stack);
  }

  public static Node posb(Stack<String> posstack) {
    String value = posstack.pop();
    if (value == null) {
      return null;
    }
    Node head = new Node(Integer.valueOf(value));
    head.right = posb(posstack);
    head.left = posb(posstack);
    return head;
  }

  public static Queue<String> levelSerial(Node head) {
    Queue<String> ans = new LinkedList<>();
    if (head == null) {
      ans.add(null);
    } else {
      ans.add(String.valueOf(head.value));
      Queue<Node> queue = new LinkedList<Node>();
      queue.add(head);
      while (!queue.isEmpty()) {
        head = queue.poll(); // head 父   子
        if (head.left != null) {
          ans.add(String.valueOf(head.left.value));
          queue.add(head.left);
        } else {
          ans.add(null);
        }
        if (head.right != null) {
          ans.add(String.valueOf(head.right.value));
          queue.add(head.right);
        } else {
          ans.add(null);
        }
      }
    }
    return ans;
  }

  public static Node buildByLevelQueue(Queue<String> levelList) {
    if (levelList == null || levelList.size() == 0) {
      return null;
    }
    Node head = generateNode(levelList.poll());
    Queue<Node> queue = new LinkedList<Node>();
    if (head != null) {
      queue.add(head);
    }
    Node node = null;
    while (!queue.isEmpty()) {
      node = queue.poll();
      node.left = generateNode(levelList.poll());
      node.right = generateNode(levelList.poll());
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
    return head;
  }

  public static Node generateNode(String val) {
    if (val == null) {
      return null;
    }
    return new Node(Integer.valueOf(val));
  }



  public static void main(String[] args) {

  }
}
