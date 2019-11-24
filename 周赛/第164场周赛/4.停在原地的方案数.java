class Solution {
    public int numWays(int steps, int arrLen) {
        int[] dp=new int[arrLen];
        int mod=(int)Math.pow(10, 9)+7;
        dp[0]=1;
        for(int i=1; i<=steps; i++) {
            int[] tmp=Arrays.copyOfRange(dp, 0, dp.length);
            for(int j=0; j<arrLen; j++) {
                dp[j] = tmp[j];
                if(j>i) break;  //最大步长都小于下标 肯定无法到达
                if(j==0) {
                    dp[j] += tmp[j+1];
                    if(i==steps) {dp[j]%=mod; break;}
                }else if(j==arrLen-1) {
                    dp[j] += tmp[j-1];
                }else {
                    dp[j] += tmp[j+1];
                    dp[j] %= mod;
                    dp[j] += tmp[j-1];
                }
                dp[j] %= mod;
            }
        }
        return dp[0];
    }
}
