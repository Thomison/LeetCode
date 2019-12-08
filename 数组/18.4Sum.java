/*************************************************************************
    > File Name: 18.4Sum.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月09日 星期一 00时15分04秒
 ************************************************************************/
//37ms
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        if(n < 4) return ans;
        //排序
        Arrays.sort(nums);

        for(int i=0; i<n-3; i++) {  
            if(i>0 && nums[i]==nums[i-1])   continue;   //去重
            for(int j=i+1; j<n-2; j++) {
                if(j>i+1 && nums[j]==nums[j-1])   continue; //去重
                    int l = j+1, r = n-1;
                    while(l < r) {
                        if(nums[i] + nums[j] + nums[l] + nums[r] < target) {
                            l++;  
                        }else if(nums[i] + nums[j] + nums[l] + nums[r] > target) {
                            r--;
                        }else {
                            List<Integer> tmp = new ArrayList<>();
                            tmp.add(nums[i]);
                            tmp.add(nums[j]);
                            tmp.add(nums[l]);
                            tmp.add(nums[r]);
                            ans.add(tmp);
                            //去重
                            while(l<r && tmp.get(2)==nums[l]) l++;
                            while(r>l && tmp.get(3)==nums[r]) r--;
                        }
                    } 
            }
        }
        return ans;
    }
}

