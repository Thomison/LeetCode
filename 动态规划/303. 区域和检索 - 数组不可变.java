class NumArray {
    int[] dp;
    
    public NumArray(int[] nums) {
        dp = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            if(i == 0)  dp[i] = nums[i];
            else dp[i] = dp[i-1] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        return (i > 0) ? dp[j]-dp[i-1] : dp[j];
    }
}
