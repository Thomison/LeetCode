/*
方法：动态规划

dp[i]来记录以数字i结尾的最长等差子序列的长度 ，以下两种情况：

dp[arr[i]] = 1 // 说明没有以 i 结尾的等差子序列
dp[arr[i]] = dp[arr[i] - difference] + 1 // 说明以 i 结尾的等差子序列，在之前的子序列长度上加1
由于这里需要通过查询子问题的元素，来获得状态值。所以用HashMap来设计状态表。

map存储的是数组中元素-该元素结尾的最长等差子序列的长度。
由于我们只需要得到整个数组的最长等差子序列，所以map中会出现键值对覆盖的情况，不过跟我们求解问题答案没有影响。

*/

class Solution { 
    public int longestSubsequence(int[] arr, int difference) {
        int ans = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            int tmp = map.getOrDefault(arr[i] - difference, 0) + 1;
            map.put(arr[i], tmp);   
            ans = Math.max(ans, tmp);   
        }
        return ans;
    }
}
