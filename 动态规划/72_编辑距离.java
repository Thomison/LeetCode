/*

如果这道题对于字符串的操作中：没有替换操作，只有插入和删除操作的话，我们可以将这个问题转化为求解两个字符串的最长公共子序列。

但是：这道题加上了字符串替换操作，看上去变得很棘手，因为一会儿插入、一会儿删除、一会儿替换，对字符串的长度分别造成+1、-1、不变这些变化，根本不知道该如何对这些操作进行选择。

那么我们为什么不用动态规划呢？反正都要做选择，就假设子问题已经最优了，只需要对word1[i]和word2[j]做三种操作的其中一种，就都试一试呗，做了哪种操作之后的dp值更大就使用那种操作 。

定义dp[i][j]表示word1[1...i] 转化为word2[1..j]所需要的最少操作数

状态转移方程：

word1[i] == word2[j]： 不执行操作，直接继承
  dp[i][j] = dp[i-1][j-1]
word1[i] != word2[j]：执行三种操作之一
  插入操作：insert=dp[i][j-1]+1
  删除操作：delete=dp[i-1][j]+1
  替换操作：replace=dp[i-1][j-1]+1
  dp[i][j] = Math.min(insert,delete,replace)


*/





class Solution {
    public int minDistance(String word1, String word2) {
        int n1=word1.length(), n2=word2.length();
        int[][] dp=new int[n1+1][n2+1];
        //initial
        for(int i=0; i<=n1; i++) {
            for(int j=0; j<=n2; j++) {
                if(i==0) dp[i][j]=j;
                else if(j==0) dp[i][j]=i;
            }
        }
        //dp
        for(int i=1; i<=n1; i++) {
            for(int j=1; j<=n2; j++) {
                if(word1.charAt(i-1)==word2.charAt(j-1)) dp[i][j]=dp[i-1][j-1];
                else {
                    dp[i][j]=Math.min(dp[i][j-1]+1, dp[i-1][j]+1);
                    dp[i][j]=Math.min(dp[i][j], dp[i-1][j-1]+1);
                }
            }
        }
        return dp[n1][n2];
    }
}
