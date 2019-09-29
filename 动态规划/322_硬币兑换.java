/*
拿到这道题，常见的思路是递归法求解，但我们尝试了一下，发现暴力递归法的时间复杂度呈2^n，显然是无法接受的，
有一种特殊的分治思想叫做动态规划，常用于求解最优化问题，本质上是将子问题的答案存储以供后续问题直接使用，
用空间换时间的做法来降低时间复杂度到O(n^2)，
需要注意到的几个关键点是：
1，最优子结构：当前问题的最优解由子问题的最优解组成；
2，状态转移方程：就是描述父问题与子问题之间最优解的关系。
*/

//方法一：暴力递归
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0)     return 0;   //退出条件
        int ans = amount + 1; //初始化，使值不可达
        for(int coin : coins) {     //单次递归过程：穷举硬币种类，尝试找零
            //金额不够找零
            if(amount < coin)   continue;
            int subAns = coinChange(coins, amount-coin);
            //子问题无解
            if(subAns == -1)    continue;
            //用子问题的解更新当前问题的解
            ans = Math.min(ans, subAns + 1);
        }
        return (ans == amount + 1) ? -1 : ans; //返回值：当前的金额需要凑的硬币数
    }
}

//方法二：带备忘录的递归
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] mem = new int[amount+1]; 
        for(int i=0; i<mem.length; i++) mem[i] = amount + 1;
        return helper(coins, amount, mem);
    }
    public int helper(int[] coins, int amount, int[] mem) {
        if(amount == 0) return 0;
        if(mem[amount] != amount + 1)     return mem[amount];   //从备忘录取值
        int ans = amount + 1;
        for(int coin : coins) {     //单次递归过程：穷举硬币种类，尝试找零
            if(amount < coin)   continue;   //不够找零
            else {        //能够找零
                int subAns = coinChange(coins, amount-coin);   //拿子问题的解
                if(subAns == -1)    continue;  //子问题无解
                ans = Math.min(ans, subAns + 1);
            }
        }
        mem[amount] = (ans == amount + 1) ?  -1 : ans;
        return mem[amount]; //返回值：当前的金额需要凑的硬币数
    }
}

//方法三：动态规划：自底向上
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1]; //备忘录
        for(int i=0; i<dp.length; i++) dp[i] = amount + 1;  //初始化，其值不可达，取大值
        dp[0] = 0;
        for(int i=1; i<dp.length; i++) {
            //求子问题+1的最小值
            for(int coin: coins) {
                if(i < coin)    continue;
                else dp[i] = Math.min(dp[i], 1 + dp[i-coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
