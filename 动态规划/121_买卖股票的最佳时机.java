/*
方法一：暴力

找到所有可能得买入卖出方案，找出满足要求的方案中收益最大的。

T O(n^2) S O(1)
*/
class Solution {
    public int maxProfit(int[] prices) {
        int ans=0;
        for(int i=0; i<prices.length; i++) {
            for(int j=i+1; j<prices.length; j++) {
                ans = Math.max(ans, prices[j]-prices[i]);
            }
        }
        return ans;
    }
}
/*
方法二：一次遍历

要实现最大收益，买入的时机总是在卖出时机之前的最小值min，所以遍历数组时，需要更新i之前的最小值min。
一次遍历，只有n-1种卖出股票的方案，因为买入时机已经确定为卖出时机之前的最小值。 只需要找出这n-1种方案的最大值即可。
*/
T O(n) S O(1)

class Solution {
    public int maxProfit(int[] prices) {
        int ans=0;
        if(prices.length<=1) return 0;
        int min=prices[0];
        for(int i=1; i<prices.length; i++) {
            ans=Math.max(prices[i]-min, ans);
            min=Math.min(min, prices[i]);
        }
        return ans;
    }
}
/*
方法三：动态规划

我们要求数组中两个元素差的最大值，其实可以转化为求最大连续子序和问题。

先将目标数组转化为变化数组，即变化数组中的元素为目标数组的当前元素和前一元素的差值。

目标数组求最大的两个元素差 -> 变化数组求最大连续的子序和

再针对最大子序和问题使用动态规划求解，同样也是一次遍历。但空间复杂度有所提升。

T O(n) S O(n)
*/
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length<=1)    return 0;
        //目标数组 -> 变化数组
        int[] p_change=new int[prices.length];
        p_change[0]=0;
        for(int i=1; i<prices.length; i++) {
            p_change[i]=prices[i]-prices[i-1];
        }
        return maxSubArray(p_change);
    }
    //求数组的最大连续子序和(动态规划)
    public int maxSubArray(int[] nums) {
        int[] dp = new int[n];  
        int ans = nums[0];
        for(int i=0; i<nums.length; i++) {
            if(i == 0) dp[i] = nums[i];
            else dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            ans = Math.max(dp[i], ans);
        } 
        return ans;
    }
}
