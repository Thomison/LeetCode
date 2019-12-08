/*************************************************************************
    > File Name: 15.3Sum.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月09日 星期一 00时12分08秒
 ************************************************************************/
//O(n^2)
//35ms
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        if(n < 3) return ans;   
        for(int i=0; i<n-2; i++) {  
            //左右双指针
            if((i>0 && nums[i]==nums[i-1]) || nums[i]>0)   continue;   //去重
            int l = i+1, r = n-1;
            while(l < r) {
                if(nums[i] + nums[l] + nums[r] < 0) {
                    l++;  
                }else if(nums[i] + nums[l] + nums[r] > 0) {
                    r--;
                }else {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[l]);
                    tmp.add(nums[r]);
                    ans.add(tmp);
                    //去重
                    while(l<r && tmp.get(1)==nums[l]) l++;
                    while(r>l && tmp.get(2)==nums[r]) r--;
                }
            } 
        }
        return ans;
    }
}

