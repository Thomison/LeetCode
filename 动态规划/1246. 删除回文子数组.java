class Solution {
    int[][] dp;
    int[] arr;
    int n;
    
    public int minimumMoves(int[] arr) {
        n = arr.length;
        this.arr = arr;
        dp = new int[n][n];
        for(int i=0; i<n; i++)      //初始化dp
            Arrays.fill(dp[i], -1);
        
        return solve(0, n-1);
    }
    int solve(int l, int r) {       //采用递归的方式构造dp
        if(l > r) {     //区间为空
            return 0;
        }else if(l == r) {      //区间长度为1
            return dp[l][r] = 1;
        }else if(l+1 == r) {        //区间长度为2
            if(arr[l] == arr[r]) {
                return dp[l][r] = 1;
            }else {
                return dp[l][r] = 2;
            }
        }else if(dp[l][r] != -1) {    //有备忘，直接返回dp值 
            return dp[l][r];
        }else {     //区间长度大于等于3
            int ret = Integer.MAX_VALUE;
            for(int i=l; i<=r; i++) {
                if(arr[l] == arr[i]) 
                    ret = Math.min(ret, 
                            Math.max(1, solve(l+1, i-1)) + solve(i+1, r));                
            }
            return dp[l][r] = ret;
        }
    }
}
