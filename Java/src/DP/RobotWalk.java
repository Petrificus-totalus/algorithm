package DP;


/**
 * 假设有排成一行的 N 个位置，记为 1~N，N一定大于或等于2
 * 开始时机器人在其中的 M 位置上（M一定是1～N中的一个）
 * 如果机器人来到 1 位置，那么下一步只能往右来到 2 位置；
 * 如果机器人来到 N 位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到 P 位置（P也是1~N中的一个）的方法有多少种
 * 给定四个参数N、M、K、P，返回方法数。
 */
public class RobotWalk {

  public static void main(String[] args) {
    System.out.println(ways1(5, 2, 4, 6));
    System.out.println(ways2(5, 2, 4, 6));
    System.out.println(ways3(2, 6, 4, 5));
  }

  public static int ways1(int N, int start, int aim, int K) {
    if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
      return -1;
    }
    return process1(start, K, aim, N);
  }

  /**
   * @param cur  当前位置
   * @param rest 剩余步数
   * @param aim  目标位置
   * @param N    总共有几个位置
   * @return
   */
  public static int process1(int cur, int rest, int aim, int N) {
    if (rest == 0) {
      return cur == aim ? 1 : 0;
    }
    if (cur == 1) {
      return process1(2, rest - 1, aim, N);
    }
    if (cur == N) {
      return process1(N - 1, rest - 1, aim, N);
    }
    return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
  }

  /**
   * (pos, rest): 要去pos位置，还剩rest步
   * 于是有 (7,10) = (6,9) + (8,9)
   * 而(6,9) 和(8,9) 都需要计算(7,8)。这里重复了
   * 所以可以用二维数组缓存(pos, rest)
   */
  public static int ways2(int N, int start, int aim, int K) {
    if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
      return -1;
    }
    int[][] dp = new int[N + 1][K + 1];
    for (int i = 0; i <= N; i++) {
      for (int j = 0; j <= K; j++) {
        dp[i][j] = -1;
      }
    }
    // dp就是缓存表  N+1 * K+1
    // dp[cur][rest] == -1 -> process1(cur, rest)之前没算过！
    // dp[cur][rest] != -1 -> process1(cur, rest)之前算过！返回值，dp[cur][rest]
    return process2(start, K, aim, N, dp);
  }


  // cur 范: 1 ~ N
  // rest 范：0 ~ K
  public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
    if (dp[cur][rest] != -1) {
      return dp[cur][rest];
    }
    // 之前没算过！
    int ans = 0;
    if (rest == 0) {
      ans = cur == aim ? 1 : 0;
    } else if (cur == 1) {
      ans = process2(2, rest - 1, aim, N, dp);
    } else if (cur == N) {
      ans = process2(N - 1, rest - 1, aim, N, dp);
    } else {
      ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
    }
    dp[cur][rest] = ans;
    return ans;
  }

  /**
   * 二维数组中的每一个元素等于其左上和右上之和
   */
  public static int ways3(int cur, int rest, int aim, int N) {
    int[][] arr = new int[rest + 1][N + 1];
    arr[0][cur] = 1;
    for (int i = 1; i <= rest; i++) {
      for (int j = 1; j <= N; j++) {
        if (j == 1) {
          arr[i][j] = arr[i - 1][2];
        } else if (j == N) {
          arr[i][j] = arr[i - 1][N - 1];
        } else {
          arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j + 1];
        }
      }
    }
    return arr[rest][aim];
  }
}
