/*

将问题转化为求解当前字符串s和逆序之后的字符串t的最长公共子序列的长度dp，
且字符串的长度n-dp为要想成为回文串必须要删除的字符个数，
k需要大于等于n-dp，才能使得最多删除k个字符使得s成回文串。

*/

/*
转化为求解最长公共子序列 动态规划
*/
class Solution {
    public boolean isValidPalindrome(String s, int k) {
        //逆序字符串s，得到字符串t
        StringBuffer sb  = new StringBuffer();
        for(int i=s.length()-1; i>=0; i--) {
            sb.append(s.charAt(i));
        }
        String t = sb.toString();
        //动态规划得到dp[n][n]即为字符串s和t的最长公共子序列的长度
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                char ch1 = s.charAt(i-1), ch2 = t.charAt(j-1);
                if(ch1 == ch2) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        
        if(n - dp[n][n] <= k)   return true;
        else return false;
    }
}
