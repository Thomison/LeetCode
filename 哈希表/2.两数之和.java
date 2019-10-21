/*
注意题目中要求：找出两数之和为target的两个数字的下标，并且两个数的下标不能相等
即不能重复利用同一元素。

方法一：暴力

暴力搜索所有可能的两个数，判断这两个数之和是否为target

时间复杂度：O(n^2)
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums.length<=1)  return null;

        int[] ans=new int[2];
        Arrays.fill(ans, -1);

        for(int i=0; i<nums.length-1; i++) {
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i]+nums[j]==target) {
                    ans[0]=i; ans[1]=j; 
                    return ans;
                }
            }
        }
        return null;
    }
}

/*
方法二：哈希表

一次遍历数组，同时维护一个哈希表。（遍历和维护同时进行）

主要利用了哈希表查找的时间复杂度为O(1)，维护一个哈希表用来存储数组对应元素的值和下标。
每遍历到数组当前元素的值key，就查询哈希表中是否存在对应的target-key的键。
这种做法就很好的避免了处理[3, 3，5] target=6和[3，5，5] target=6 这两种情况，
因为当前元素并没有加入到哈希表中，不会覆盖相等值的键。
(刚开始我遍历了数组构建了哈希表再来遍历哈希表，发现哈希表中的键值对若键相等会出现重叠情况)

时间复杂度：O(n)
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums.length<=1)  return null;
        int[] ans=new int[2];
        Arrays.fill(ans, -1);
        Map<Integer, Integer> helper=new HashMap<>();
        
        for(int i=0; i<nums.length; i++) {
            if(helper.containsKey(target-nums[i])) {
                ans[0]=helper.get(target-nums[i]); ans[1]=i;
                return ans;
            }
            helper.put(nums[i], i);
        }
        return null;
    }
}
