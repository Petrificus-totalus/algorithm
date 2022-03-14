package DP;

/**
 * 规定 1 和 A 对应...... 26 和 Z 对应
 * 那么一个数字串 "111" 就可以转化为 "AAA" 或 "AK" 或 "KA"
 * 给定一个字符串 str, 返回转为多少种
 */
public class ConvertToLetterString {
  public static int number(String str) {
    if (str == null || str.length() == 0) {
      return 0;
    }
    return process(str.toCharArray(), 0);
  }

  // str[0..i-1]转化无需过问
  // str[i.....]去转化，返回有多少种转化方法
  public static int process(char[] str, int i) {
    if (i == str.length) {
      return 1;
    }
    // i没到最后，说明有字符
    if (str[i] == '0') {  // 之前的决定有问题
      return 0;
    }
    int ways = process(str, i + 1);
    // 到末尾或者连着的两个组成的数大于26就不能翻译了
    if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
      ways += process(str, i + 2);
    }
    return ways;
  }

  // 从右往左的动态规划
  public static int dp(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int[] dp = new int[N + 1];
    dp[N] = 1;
    for (int i = N - 1; i >= 0; i--) {
      if (str[i] != '0') {
        int ways = dp[i + 1];
        if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
          ways += dp[i + 2];
        }
        dp[i] = ways;
      }
    }
    return dp[0];
  }


  public static void main(String[] args) {
    System.out.println(number("1111"));
    System.out.println(dp("1111"));

  }

}
