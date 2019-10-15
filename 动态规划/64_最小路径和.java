/*
方法：动态规划

由于行动方向只能向右或者向下，那么当前问题的解由子问题最优解要么向下走、要么向右走两种方案组成，要得到当前问题的最优解，只要比较两种方案的最优值即可。

状态转移：dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
*/


class Solution {
    public int minPathSum(int[][] grid) {
        int m=grid.length, n=grid[0].length;
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                if(i==0 || j==0) dp[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(i==1 && j==1) dp[i][j]=grid[i-1][j-1];
                else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }
}
