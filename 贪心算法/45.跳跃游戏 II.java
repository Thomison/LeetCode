/*
方法一：动态规划

T O(n^2) S O(n)

暴力dp ，在求解dp[i]时要考虑dp[1....i-1] ，找到能够到达i的最小值。也就是每次每次求解子问题时，都要将之前已经求解的子问题都选择一遍。

结果：超出时间限制 T T
*/

class Solution {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length+1];
        for(int i=2; i<=nums.length; i++)   //initial
            dp[i] = i-1;
        for(int i=2; i<=nums.length; i++) {
            int index = i-1;
            for(int j=0; j<index; j++) {
                if(nums[j] >= (index-j)) {  //能够到达index处
                    dp[i] = Math.min(dp[i], dp[j+1]+1);
                }
            }
        }
        return dp[nums.length];
    }
}

/*
方法二：贪心算法

T O(n) S O(1)

思想：因为当前元素是最大跳跃长度，即下一跳存在一个范围，贪心选择关键在于在该范围内选择使得下下一跳距离最远
*/
class Solution {
    public int jump(int[] nums) {
        int ans = 0, i=0;
        while(i < nums.length-1) {
            int start = i + 1, end = i + nums[i], tmp = -1;
            //最后一跳的范围包括终点
            if(start <= nums.length-1 && end >= nums.length-1) {ans ++; break;}

            int max_pos = end;
            for(int j=start; j<=end; j++) {
                if(j + nums[j] > max_pos) {     //找到能够使得下下跳达到最远距离的下一跳的下标
                    max_pos = j + nums[j];
                    tmp = j;
                }
            }
            i = tmp;    //执行跳，更新当前下标
            ans++;      //跳跃次数+1
        }
        return ans;
    }
}
