/*
方法：动态规划

如果我们采用暴力求解，先要求得text1的所有子串，在对每个子串进行判断是否为text2的最长子串，时间复杂度为2^n指数级，效率太低，放弃。

从top->bottom对问题进行刻画，最优子结构：

- 如果t1[m] = t2[n]，则它们的最长公共子串长度为 t1[m-1]和t2[n-1]的最长公共子串长度再加上1
- 如果 t1[m] != t2[n]，则它们的最长公共子串长度为一下两者的最大值：
  - t1[m]和t2[n-1]的最长公共子串长度
  - t1[m-1]和t2[n]的最长公共子串长度
状态转移方程：

建立二维状态数组dp[text1.length()+1][text2.length()+1]

- text1.charAt(i) == text2.charAt(j)
  - dp[i][j] = dp[i-1][j-1] + 1
- text1.charAt(i) != text2.charAt(j)
  - dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])

*/

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        int[][] dp = new int[n1+1][n2+1];  
        for(int i=1; i<=n1; i++) {
            for(int j=1; j<=n2; j++) {
                char c1 = text1.charAt(i-1), c2 = text2.charAt(j-1);
                if(c1 == c2)    dp[i][j] = dp[i-1][j-1] + 1;
                else    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[n1][n2];
    }
}
