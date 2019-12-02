/*************************************************************************
    > File Name: 4.分割回文串　III.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月02日 星期一 13时47分57秒
 ************************************************************************/
class Solution {
    public int palindromePartition(String s, int k) {
        int n=s.length();
        char[] chars=s.toCharArray();
        //dp1[i][j]:子字符串S_ij修改字符的最小个数
        int[][] dp1=new int[n][n]; 
        //dp2[i][j]:字符串前缀S_0i分割为j个回文字符修改字符的最小个数
        int[][] dp2=new int[n][k+1];  

        //构造dp1
        for(int l=1; l<=n; l++) {       //根据子串的长度进行迭代
            for(int i=0; i<n-l+1; i++) {    //左下标
                int j=i+l-1;    //右下标
                if(chars[i]==chars[j]) {    //长度为１或２时单独考虑
                    if(i==j || i+1==j) dp1[i][j]=0;
                    else dp1[i][j]=dp1[i+1][j-1];
                }else {
                    if(i+1==j) dp1[i][j]=1;
                    else dp1[i][j]=dp1[i+1][j-1]+1;
                }
            }
        }
        //构造dp2
        for(int i=0; i<n; i++) {    //根据前缀的下标进行迭代
            for(int j=1; j<=Math.min(i, k); j++) {
                if(j==1) {
                    dp2[i][1]=dp1[0][i];    //边界：划分为一个回文串的情况
                }else {
                    dp2[i][j]=Integer.MAX_VALUE;
                    for(int m=(j-1)-1; m<i; m++) {
                        // 0-m:j-1个回文, （m+1）-i:1个回文
                        dp2[i][j]=Math.min(dp2[i][j],
                                    dp2[m][j-1]+dp1[m+1][i]);
                    }
                }
                
            }
        }
        return dp2[n-1][k];
    }
}
