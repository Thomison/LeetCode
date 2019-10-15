/*
一开始想采用正做，pow(6, n)减掉重复的序列数，发现判重好像特别麻烦，就没做出来。

比赛完了之后，看了下排行榜的code ，发现还可以用动态规划来做，看来我还是太年轻，刷题太少了。

思路：动态规划

状态数组：int[][][] dp = new int[n+1][6+1][15+1];

dp[i][j][k] i表示第几次掷骰子，j表示结尾的点数，k表示以点数j结尾的连续次数

状态转移方程：
// dp[轮数][点数][1] = 上一轮总方案数-上一轮这个点数的出现次数
// dp[轮数][点数][连续次数]=dp[上一轮][点数][连续次数-1]

dp[i][j][1] = sum( dp[i-1][k][1...rollMax(k)] ) k=1...(j-1) U (j+1) ...6继承上一轮点数和这一轮点数不同的所有dp

需要一个两层循环求和。

dp[i][j][m] = dp[i-1][j][m-1]继承上一轮该点数且连续次数为m-1的dp
*/

class Solution {
    public static final int MOD = 1000000007;
    public int dieSimulator(int n, int[] rollMax) {
        int[][][] dp = new int[n+1][6+1][15+1];
        /*initial*/
        for(int i=1; i<=6; i++) {    
            dp[1][i][1] = 1;
        }
        /*dp*/
        for(int i=2; i<=n; i++) {   
            for(int j=1; j<=6; j++) {   //第i次掷骰 点数
                for(int k=1; k<=6; k++) {    //第i-1次掷骰 点数
                    if(j != k) {    //不连续 需要两层循环统计前面第i-1次掷骰的所有子问题解的和
                        for(int m=1; m<=rollMax[k-1]; m++) {
                            dp[i][j][1] += dp[i-1][k][m];
                            dp[i][j][1] %= MOD;
                        }
                    }else {     //连续 需要一层循环统计前面第i-1次掷骰的子问题中：连续次数比当前连续次数刚好小于1的子问题解
                        for(int m=1; m < rollMax[k-1]; m++) {
                            dp[i][j][m+1] += dp[i-1][k][m];
                            dp[i][j][m+1] %= MOD;
                        }
                    }
                }
            }
        }
        /*sum*/
        int ans = 0;
        for(int j=1; j<=6; j++) {
            for(int m=1; m<=rollMax[j-1]; m++) {
                ans += dp[n][j][m];
                ans %= MOD;
            }
        }
        return ans;
    }
}
// dp[轮数][点数][1] = 上一轮总方案数-上一轮这个点数的出现次数
// dp[轮数][点数][连续次数]=dp[上一轮][点数][连续次数-1]
