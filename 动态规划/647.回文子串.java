//动态规划 
//if size>2 dp[i][j] = dp[i+1][j-1] && s[i]==s[j]
//if size==2 dp[i][j] = s[i]==s[j]
//if size==1 dp[i][j] = true
//构造dp数组的同时就统计回文子串的个数
class Solution {
    boolean[][] dp = new boolean[1000][1000];
    int ans = 0;

    public int countSubstrings(String s) {
        int length = s.length();

        for(int l=1; l<=length; l++) {
            for(int i=0; i<length-l+1; i++) {
                int j = i+l-1;

                if(l > 2) 
                    dp[i][j] = dp[i+1][j-1] && (s.charAt(i) == s.charAt(j));
                else if(l == 2)
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                else 
                    dp[i][j] = true;

                if(dp[i][j])    ans++;
            }
        }
        return ans;
    }
}
