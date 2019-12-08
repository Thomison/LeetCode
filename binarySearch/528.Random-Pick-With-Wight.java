/*************************************************************************
    > File Name: 528.Random-Pick-With-Wight.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月09日 星期一 00时16分29秒
 ************************************************************************/
//O(logn)   37ms
class Solution {
    int n;      //元素个数
    int[] w;        //权重数组
    int[] preFixSum;     //前缀和数组
    Random random;      //Random实例

    public Solution(int[] w) {
        this.n = w.length;
        this.w = w;
        this.preFixSum = new int[n];
        this.random = new Random();
        //构造前缀数组和
        preFixSum[0] = w[0];
        for(int i=1; i<n; i++) {
            preFixSum[i] = preFixSum[i-1] + w[i];
        }
    }
    // return index of element
    public int pickIndex() {
        int target = random.nextInt(preFixSum[n-1]) + 1; //[1,sum]
        //二分查找
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + (right-left)/2;
            if(target == preFixSum[mid]) {
                return mid;
            }else if(target < preFixSum[mid]) {
                right = mid - 1;
            }else if(target > preFixSum[mid]) {
                left = mid + 1;
            }
        }
        if(preFixSum[left] < target) {
            return left + 1;
        }else {
            return left;
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

