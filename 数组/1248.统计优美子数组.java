class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        List<Integer> loc = new ArrayList<>();
        int ans = 0;
        
        loc.add(-1);
        for(int i=0; i<nums.length; i++) {
            if(nums[i] % 2 == 1) {
                loc.add(i);//区间下标
            }
        }
        loc.add(nums.length);
        
        for(int i=k; i<loc.size()-1; i++) {
            int left = loc.get(i-k+1) - loc.get(i-k);//前缀区间数目
            int right = loc.get(i+1) - loc.get(i);//后缀区间数目
            ans += left * right;
        }
        
        return ans;
    }
}
