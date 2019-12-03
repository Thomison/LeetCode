/*************************************************************************
    > File Name: 462.MinimumMoveToEqualArrayElement_II.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月03日 星期二 20时03分08秒
 ************************************************************************/
/*
 * Given a non-empty integer array, find the minimum number of moves required to **make all array elements equal**, where **a move is incrementing a selected element by 1 or decrementing a selected element by 1**
 */
//7ms
//seem not be the average number, but medium number.
class Solution {
    //length of nums: [0, 10000].
    public int minMoves2(int[] nums) {
        if(nums.length==0 || nums.length==1) return 0;
        int ans=0;
        //sort the nums
        Arrays.sort(nums);
        //select medium of nums
        int middle=nums.length/2;
        if(nums.length % 2 == 1) {
            int m=nums[middle];
            for(int num: nums) {
                ans += Math.abs(num-m);
            }
            return ans;
        }else {
            int m1=nums[middle], ans1=0;
            int m2=nums[middle-1], ans2=0;
            for(int num: nums) ans1 += Math.abs(num-m1);
            for(int num: nums) ans2 += Math.abs(num-m2);
            return Math.min(ans1, ans2);
        }
    }
}
// T:O(nlogn)
// S:O(1)
