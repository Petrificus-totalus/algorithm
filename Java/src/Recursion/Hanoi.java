package Recursion;

public class Hanoi {

  // 把N从from移到to，需要先将N-1移到other，再把那一个移到to，再把other上的N-1移到to
  public static void func(int N, String from, String to, String other) {
    if (N == 1) {
      System.out.println("Move 1 from " + from + " to " + to);
    } else {
      func(N - 1, from, other, to);
      System.out.println("Move " + N + " from " + from + " to " + to);
      func(N - 1, other, to, from);
    }
  }

  public static void main(String[] args) {
    func(3, "left", "right", "mid");
  }
}
