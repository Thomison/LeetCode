//19ms
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        if(m==0 || n==0) return 0;
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<Math.min(i+1, n); j++) {
                if(i == 0) {
                    dp[0][0] = (s.charAt(i) == t.charAt(j))? 1 : 0;
                }else if(j == 0){
                    dp[i][0] = (s.charAt(i) == t.charAt(j))? dp[i-1][0]+1 : dp[i-1][0];
                }else {
                    dp[i][j] = (s.charAt(i) == t.charAt(j))? dp[i-1][j-1]+dp[i-1][j] : dp[i-1][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
