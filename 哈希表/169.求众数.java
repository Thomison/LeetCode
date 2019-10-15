/*
方法一：哈希表
先遍历一遍原数组，用哈希表来统计每一种数字出现的次数，再遍历一遍哈希表，找到对应键的最大值，并输出键。
时间复杂度O(n+m)，空间复杂度O(m)
*/

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> helper = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            if(!helper.containsKey(nums[i])) {
                helper.put(nums[i], 1);
            }else {
                int tmp = helper.get(nums[i]);
                tmp ++;
                helper.remove(nums[i]);
                helper.put(nums[i], tmp);
            }
        }
        int ans = nums[0];
        for(Integer key: helper.keySet()) {
            ans = (helper.get(ans) < helper.get(key)) ? key : ans;
        }
        return ans;
    }
}

//方法二：分治算法
//方法三：排序后找中位数
