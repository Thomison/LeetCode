/*************************************************************************
    > File Name: 932.Beautiful-Array.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月18日 星期三 22时20分30秒
 ************************************************************************/
class Solution {
    public int[] beautifulArray(int N) {
        return solve(N);
    }
    int[] solve(int N) {
        int[] ret = new int[N];     //odd-even 
        if(N == 1) {
            ret[0] = 1;
        }else {
            int[] left = solve((N+1)/2);
            for(int i=0; i<left.length; i++) {
                ret[i] = left[i] * 2 - 1;       //convert to odd
            }
            int[] right = solve(N/2);
            for(int i=left.length; i<N; i++) {
                ret[i] = right[i-left.length] * 2;  //convert to even
            }
        }
        return ret;
    }   
}


//思想：带备忘录的自顶向下的递归
class Solution {
    Map<Integer, int[]> memo;
    public int[] beautifulArray(int N) {
        memo = new HashMap();
        return f(N);
    }

    public int[] f(int N) {
        if (memo.containsKey(N))
            return memo.get(N);

        int[] ans = new int[N];
        if (N == 1) {
            ans[0] = 1;
        } else {
            int t = 0;
            for (int x: f((N+1)/2))  // odds
                ans[t++] = 2*x - 1;
            for (int x: f(N/2))  // evens
                ans[t++] = 2*x;
        }
        memo.put(N, ans);
        return ans;
    }
}
