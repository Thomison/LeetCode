/*
方法：动态规划

找到最优子结构：当前能够偷到的最大值dp[i]由当前房屋是否rob组成，rob的话就是dp[i-2]+nums[i]
（因为不能连续偷取相邻房屋），不rob的话就是dp[i-1]+0，选取两者最大值就是当前的最优解。

状态转移：dp[i]=Math.max(dp[i-2]+nums[i], dp[i-1])
*/

//T O(n) S O(n)
class Solution {
    public int rob(int[] nums) {
        int[] dp=new int[nums.length+1];
        for(int i=1; i<=nums.length; i++) {
            if(i==1) dp[i]=nums[i-1];
            else dp[i]=Math.max(dp[i-1], dp[i-2]+nums[i-1]);
        }
        return dp[nums.length];
    }
}

/*
改进：事实上，我们不用记录之前所有的状态值，只需要两个状态值标记即可。

空间降维度
*/

//改进 T O(n) S O(1)
class Solution {
    public int rob(int[] nums) {
        int dp_pre=0, dp=nums[0];
        for(int i=1; i<nums.length; i++) {
                int tmp=dp;
                dp=Math.max(dp, dp_pre+nums[i]);
                dp_pre=tmp;
        }
        return dp;
    }
}
