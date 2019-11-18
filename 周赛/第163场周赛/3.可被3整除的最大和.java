class Solution {
    int mod = 3;        //除数为3
    
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][mod];   //表示当前前缀数组除mod余j的最大和
        
        for(int i=0; i<mod; i++) {    //初始化
            dp[0][i] = (nums[0] % mod == i) ? nums[0] : 0;
        }
        for(int i=1; i<n; i++) {
            
            for(int j=0; j<mod; j++) {
                int max = 0;
                for(int k=0; k<mod; k++) {
                    int tmp = dp[i-1][k] + nums[i];
                    if(tmp % mod == j && tmp > max) {
                        max = tmp;
                    }
                }
                if(max == 0) {
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], max);
                }
            }
        }
        return dp[n-1][0];
    }
}

//dp降维

class Solution {
    
    public int maxSumDivThree(int[] nums) {
        int[] dp = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for(int n : nums) {
            int[] dp_tmp = {0, 0, 0};
            for(int i=0; i<3; i++) {
                //更新当前前缀数组的最大和(除3余数0,1,2)
                dp_tmp[(i+n) % 3] = Math.max(dp[(i+n) % 3], dp[i]+n);
            }
            dp = dp_tmp;
        }
        return dp[0];
    }
}
