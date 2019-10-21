/*
在198. 打家劫舍的基础上增加了难度，就是说将数组第一个元素和数组最后一个元素连接在一起，成环。

也就是说第一家和最后一家需要单独考虑，它们之间只能最多rob一家。偷了第一家就不能偷最后一家，偷了最后一家就不能偷第一家。

则将原问题分解为求解求偷1.....n-1和偷2.....n的最大值。

可以稍微改下198. 打家劫舍的传入参数，就可以直接调用了。
*/

class Solution {
    public int rob(int[] nums) {
        if(nums==null)  return 0;
        else if(nums.length==1) return nums[0];
        else return Math.max(
                rob(nums, 0, nums.length-2), 
                rob(nums, 1, nums.length-1));
    }
    public int rob(int[] nums, int start, int end) {
        int dp_pre=0, dp=nums[start];
        for(int i=start+1; i<end; i++) {
                int tmp=dp;
                dp=Math.max(dp, dp_pre+nums[i]);
                dp_pre=tmp;
        }
        return dp;
    }
}
