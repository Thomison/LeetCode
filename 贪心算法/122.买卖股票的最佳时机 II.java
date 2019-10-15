/*
方法：贪心算法

T O(n) S O(1)

一次遍历数组，只要今天的价格大于昨天的价格就执行昨天买进今天卖出的操作， 局部最优的同时逼近全局最优
*/

class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        for(int i=1; i<prices.length; i++) {
            if(prices[i] > prices[i-1])
                ans += prices[i] - prices[i-1];
        }
        return ans;
    }
}
