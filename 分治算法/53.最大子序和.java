//方法一：暴力匹配O(n^2)

class Solution {
    public int maxSubArray(int[] nums) {
        int ans = nums[0], n = nums.length;
        for(int i=0; i<n; i++) {
            int temp = 0;
            for(int j=i; j<n; j++) {
                temp += nums[j];
                ans = Math.max(temp, ans);
            }
        }
        return ans;
    }
}

//方法二：动态规划O(n)---最优
/*
注意题目中描述的largest、contiguous这些字眼，表明这道题是一道最优化的问题，我们可以尝试用动态规划进行求解，
我们将原问题转化为 ：在n个以当前下标整数结尾的子序列和中，得到其中最大的一个。
通俗来说，就是若当前元素对上一个子问题的最大子序列和有增益的话，就加上当前元素，否则从当前元素开始，重新构造新的子序列。

给出状态转移方程：dp[i] = max( dp[i-1] + nums[i], nums[i] )
*/

class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];  //dp[i]表示当前下标为结尾的最大子序列和
        int ans = nums[0];
        for(int i=0; i<n; i++) {
            if(i == 0) {
                dp[i] = nums[i];
            }else {
                dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            }
            ans = Math.max(dp[i], ans);
        } 
        return ans;
    }
}


//方法三：分治算法O(n*logn)
/*
我们要得到的子序列最大和的子序列的位置有三种情况：序列左边、序列右边、序列中部，
前两者我们可以分治递归得到，后者我们直接计算，
比较三者，最大值即为我们想要的结果。
*/

class Solution {
    public int maxSubArray(int[] nums) {
        int ans = helper(nums, 0, nums.length-1);
        return ans;
    }
    //用于递归的辅助方法
    public int helper(int[] nums, int left, int right) {
        int max_left=0, max_right=0, max_middle=0;
        int middle = (left+right)/2;
        if(left == right) {  //终止条件
            return nums[left]; 
        }
        //单次递归的过程
        //计算左、右两边的最大子序列
        else {
            max_left = helper(nums, left, middle);
            max_right = helper(nums, middle + 1, right);
        }
        //计算中间最大子序列，分成两部分，左边部分从右向左，右边部分从左向右
        int max_middle_left=nums[middle], max_middle_right=nums[middle+1], temp=0;
        for(int i=middle; i>=left; i--) {
            temp += nums[i];
            max_middle_left = Math.max(temp, max_middle_left);
        }
        temp = 0;
        for(int i=middle+1; i<=right; i++) {
            temp += nums[i];
            max_middle_right = Math.max(temp, max_middle_right);
        }
        max_middle = max_middle_left + max_middle_right;
        //比较左、中、右的最大子序列，返回最大值
        return max(max_left, max_middle, max_right);
    }
    //比较三者，返回最大值
    public int max(int a, int b, int c) {
        if(a > b) {
            if(a > c) return a;
            else return c;
        }else {
            if(b > c) return b;
            else return c;
        }
    }
} 
