/*************************************************************************
    > File Name: 923.3Sum-With-Multiplicity.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月09日 星期一 00时14分04秒
 ************************************************************************/
//O(n^2)    52ms
class Solution {
    public int threeSumMulti(int[] A, int target) {
        int mod = (int) Math.pow(10, 9) + 7;
        int ans = 0;
        int n = A.length;
        //排序
        Arrays.sort(A);
        //搜索三元组
        for(int i=0; i<=n-3; i++) {
            int l = i+1, r = n-1;   //左右指针
            while(l < r) {
                if(A[i]+A[l]+A[r] < target) l++;
                else if(A[i]+A[l]+A[r] > target) r--;
                else {      //找到三元组, 计算可能的组合数
                    int A_l = A[l], A_r = A[r];
                    int cnt_l = 0, cnt_r = 0;   //对相邻且相等的数计数
                    while(l<n && A[l]==A_l) {
                        l++; cnt_l++;
                    }
                    while(r>i && A[r]==A_r) {
                        r--; cnt_l++;
                    }
                    if(A_l == A_r) {
                        ans += cnt_l*(cnt_r-1)/2;
                    }else {
                        ans += cnt_l*cnt_r;
                    }
                    ans %= mod;
                } 
            }
        }
        return ans;
    }
}

