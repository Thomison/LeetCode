/*

方法：动态规划

状态转移方程：dp[i]=dp[i-1]+dp[i-2]

*/



//dp[i]=dp[i-1]+dp[i-2]
class Solution {
    public int climbStairs(int n) {
        if(n==0) return 0;
        int[] dp=new int[n+1];
        for(int i=1; i<=n; i++) {
            if(i==1) dp[i]=1;
            else if(i==2) dp[i]=dp[1]+1;    //dp[1]=1, dp[2]=2
            else dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
