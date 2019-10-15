/*
像这种典型的子问题含有重叠部分，并且具有最优子结构的问题，采用递归 -> 备忘录的递归 -> 动态规划 的步骤进行求解。

在[62. Unique Paths]的基础上，路径上设置了障碍物，基本思想还是动态规划，在有障碍物的坐标点，显然不可达，直接使dp[i][j] == 0 ，然后down-up的dp即可。

LeetCode-62.Unique Paths
​
图标
状态转移方程：建立表示状态的二维数组dp[m-1][n-1]表示从(1, 1)到(m, n)的 路径条数。

当i == 0 && j == 0 时，dp[i][j] = 1

当i == 0 && j > 0时，dp[i][j] = dp[i][j-1];

当j == 0 && i > 0时，dp[i][j] = dp[i-1][j];

否则，dp[i][j] = dp[i-1][j] + dp[i][j-1]

*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                }else {
                    if(i == 0 && j == 0) {
                        dp[i][j] = 1;
                    }else if(i == 0) {
                        dp[i][j] = dp[i][j-1];
                    }else if(j == 0) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = dp[i-1][j] + dp[i][j-1];
                    }
                }
            }
        }
        return dp[m-1][n-1];
    }

