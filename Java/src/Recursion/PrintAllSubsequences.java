package Recursion;


import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的所有子序列
 * abc: # a b c ab ac bc abc
 *
 * 若想输出所有不重复的子序列，
 * 直接把ans类型设置为HashSet<String>
 */
public class PrintAllSubsequences {

  public static List<String> subs(String s) {
    char[] str = s.toCharArray();
    String path = "";
    List<String> ans = new ArrayList<>();
    process(str, 0, ans, path);
    return ans;
  }

  /**
   *
   * @param str   原字符串，该参数始终不变
   * @param index  到了原字符串的哪一下标
   * @param ans    结果列表
   * @param path   已选出的字符连接的串
   */
  public static void process(char[] str, int index, List<String> ans, String path) {
    if (index == str.length) {
      ans.add(path);
      return;
    }
    // 不选当前位置上的字符
    process(str, index + 1, ans, path);
    // 选当前位置上的字符
    process(str, index + 1, ans, path + String.valueOf(str[index]));
  }

  public static void main(String[] args) {
    String str = "abc";
    List<String> ans1 = subs(str);
    for (String s : ans1) {
      System.out.println(s);
    }
  }
}
