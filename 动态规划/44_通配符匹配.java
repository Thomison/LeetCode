/*
这道题跟10. 正则表达式匹配很像，但是难度要下降不少，
比如正则表达式匹配中的#*可以匹配空，在那里的*是用来匹配0个或任意长度的前一个字符，
而在这里则不能匹配空，*只能匹配0个或任意长度的任意字符子串。

关于那道题的题解：LeetCode-10.Regular Expression Matching

还是用动态规划，开整：

状态转移方程：

s.charAt(i-1) == p.charAt(j-1) : dp[i][j]=dp[i-1][j-1]
s.charAt(i-1) != p.charAt(j-1)：
  p.charAt(j-1) == '?' : dp[i][j]=dp[i-1][j-1]
  p.charAt(j-1) == '*' ： dp[i][j] = dp[i-1][j] U dp[i][j-1] 意思是当前的*匹配0个字符或者多个字符，
    如#*匹配#c这种情况，继承#是否能匹配#c，若能匹配，则*匹配空
    如#*匹配#c这种情况，继承#*是否能匹配#，若能匹配，则*再多匹配个c也无妨。
*/



class Solution {
    public boolean isMatch(String s, String p) {
        int m=s.length(), n=p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        //initial
        for(int j=0; j<=n; j++) {
            if(j==0) dp[0][j]=true;
            else {
                if(p.charAt(j-1)=='*') {
                    if(j==1) dp[0][j]=dp[0][j-1];
                    else if(p.charAt(j-2)=='*') dp[0][j]=dp[0][j-1];
                }
            }
        }
        //dp
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                char charS=s.charAt(i-1), charP=p.charAt(j-1);
                if(charS==charP) dp[i][j]=dp[i-1][j-1];
                else {
                    if(charP=='?') dp[i][j]=dp[i-1][j-1];
                    else if(charP=='*') {
                        if(dp[i-1][j] || dp[i][j-1]) dp[i][j]=true;
                    }
                }
            }
        }
        return dp[m][n];
    }
}
