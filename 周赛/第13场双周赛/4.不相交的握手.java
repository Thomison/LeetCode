class Solution {
    int mod = (int)Math.pow(10, 9) + 7;
    public int numberOfWays(int num_people) {
        if(num_people % 2 != 0) return 0;
        int n = num_people / 2;
        int[] dp = new int[501];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=n; i++) {  
            for(int j=0; j<i; j++) {
                dp[i] += dp[j] * dp[i-j-1] % mod;
                dp[i] %= mod;
            }
        }
        return dp[n];
    }
}
