/*************************************************************************
    > File Name: 453.MinimumMoveToEqualArrayElement.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月03日 星期二 20时01分32秒
 ************************************************************************/
/*
 *Given a non-empty integer array of size n, find the minimum number of moves required to **make all array elements equal**, where **a move is incrementing n - 1 elements by 1**
 */

class Solution {
    public int minMoves(int[] nums) {
        int ans=0;
        if(nums.length==1) return ans;
        int min=Integer.MAX_VALUE;
        //找到最小值
        for(int i: nums) {
            min=Math.min(i, min);
        }
        //累加每个数需要移动的次数
        for(int i: nums) {
            ans += i-min;
        }
        return ans;
    }
}
