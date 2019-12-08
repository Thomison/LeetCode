/*************************************************************************
    > File Name: 16.3Sum-Closest.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月09日 星期一 00时12分57秒
 ************************************************************************/
//O(n^3)    15ms
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int dist = Integer.MAX_VALUE;
        int ans = nums[0] + nums[1] + nums[2];
        int n = nums.length;
        for(int i=0; i<=n-3; i++) {
            for(int j=i+1; j<=n-2; j++) {
                for(int k=j+1; k<=n-1; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if(sum == target) return target;    //提前终止
                    if(Math.abs(target-sum) < dist) {   //距离缩小
                        dist = Math.abs(target-sum);
                        ans = sum;
                    }
                }
            }
        }
        return ans;
    }
}
//O(n^2)    5ms
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int dist = Integer.MAX_VALUE;
        int ans = nums[0] + nums[1] + nums[2];
        int n = nums.length;    //默认n>=3
        Arrays.sort(nums);      //排序
        for(int i=0; i<=n-3; i++) {
            if(i>0 && nums[i]==nums[i-1]) {
                continue;
            }
            int l = i+1, r = n-1;
            while(l < r) {
                int sum = nums[l] + nums[r] + nums[i];
                if(sum < target) {
                    l++;
                }else if(sum > target) {
                    r--;
                }else {
                    return target;  //提前终止
                }
                if(Math.abs(target-sum) < dist) {   //缩小距离
                    dist = Math.abs(target - sum);  //更新距离
                    ans = sum;      //更新答案
                }
            }
        }
        return ans;
    }
}

