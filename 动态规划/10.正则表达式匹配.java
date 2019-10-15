/*
方法：动态规划

这道题很坑，需要仔细读题，如b*可以匹配null，因为*可以匹配0个及多个它之前的元素，意思是*不会出现在字符串头部，它往往跟着一个字符，成对出现进行匹配。

按照题目给的规律，我一开始是考虑正做的，建立一个堆栈，s和p对应字符进行匹配，发现回溯有点麻烦。就放弃了。转而用动态规划来做。思路稍微清晰点。

建立二维状态数组dp[s.length()+1][p.length()+1]，dp[i][j]表示 以i、j结尾的两个子串是否能够匹配，类型是boolean。

需要先对dp[0][]这一行进行初始化，dp[0][0] = 0，即空字符串可以匹配空字符串，然后对于有些匹配规则，是可以匹配空字符串的，如c*、c*b*等。即当前字符为*时，dp[0][k] = dp[0][k-2]

状态转移规律：

-s.charAt(i) = p.charAt(j)时，dp[i][j] = dp[i-1][j-1];
- s.charAt(i) != p.charAt(j)时：
  - p.charAt(j) = '.' 时：dp[i][j] = dp[i-1][j-1]
  - p.charAt(j) = '*' 时： 需要追溯p的前一个字符
    - s.charAt(i) ！= p.charAt(j-1) 时：匹配#a #b* 则b*用于匹配0
      - dp[i][j] = dp[i][j-2] 判断#a #是否匹配
    - s.charAt(i) = p.charAt(j-1) 时，匹配#b 和##b* ,需要分为三种情况：
      - 判断# ##b*是否匹配：dp[i-1][j]
      - 判断#b ##b是否匹配：dp[i][j-1]
      - 判断#b ##是否匹配：dp[i][j-2]
      三种情况，只要其中有一个匹配即可推导
      
注意：三种情况不用判断# ##是否匹配，因为dp[i-1][j-2]其实就是dp[i][j-1] 的子问题。

难点：

- 对于匹配目标空字符串时的初始化
- 对于*匹配的两种情况：需要判断p中前一个字符是否和 s的当前字符相等
- p中前一个字符和s的当前字符相等的情况下，有三种情况(满足其一)即可使得当前状态为true

*/

class Solution {
    public boolean isMatch(String s, String p) {
        //初始化
        int n1 = s.length(), n2 = p.length();
        boolean[][] dp = new boolean[n1+1][n2+1];
        dp[0][0] = true;
        for(int k=1; k<n2; k++) {
            if(p.charAt(k) == '*') {
                if(k == 1 || p.charAt(k-2) == '*')  dp[0][k+1] = dp[0][k-1];
            }
        }
        //状态转移
        for(int i=1; i<=n1; i++) {
            for(int j=1; j<=n2; j++) {
                char s_curr = s.charAt(i-1), p_curr = p.charAt(j-1);
                if(s_curr == p_curr)    dp[i][j] = dp[i-1][j-1];
                else {
                    if(p_curr == '.')    dp[i][j] = dp[i-1][j-1];
                    else if(p_curr == '*') {
                        char p_pre = p.charAt(j-2);
                        if(p_pre != s_curr) dp[i][j] = dp[i][j-2];  //如匹配#a  #b*    消除b*
                        if(p_pre == s_curr || p_pre == '.') {  //如#b ##b*  ,需要分为三种情况
                            if(dp[i-1][j])  dp[i][j] = true;    //判断# ##b*是否匹配
                            if(dp[i][j-1])  dp[i][j] = true;    //判断#b ##b是否匹配
                            if(dp[i][j-2])  dp[i][j] = true;    //判断#b ##是否匹配
                        }
                    }
                }
            }
        }
        //返回最终状态
        return dp[n1][n2];
    }
}
