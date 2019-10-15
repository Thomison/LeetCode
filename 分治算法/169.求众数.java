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
/*
方法二：分治算法

将原问题一步一步向下的分解，分解成子问题，直到子集中只有一个元素，它本身就是该子集的众数，直接返回。然后一步一步往上合并子问题的解。

整体的分解、合并过程由递归来模拟。所以通常分治算法也叫分治递归

关键在于合并：由两个子集的众数得到合集的众数：

  若两个子集的众数相等，直接返回其中任一作为合集的众数
  若两个子集的众数不相等，则需要判断这两个众数哪个在合集中出现的次数多，出现次数更多的，作为合集的众数返回。
时间复杂度O(nlogn)，空间复杂度O(1)

*/
class Solution {
    public int majorityElement(int[] nums) {
        if(nums.length == 1)    return nums[0];
        return helper(nums, 0, nums.length-1);
    }
    public int helper(int[] nums, int start, int end) {
        if(start == end)    return nums[start];
        int middle = (start+end)/2;
        //分解
        int num1 = helper(nums, start, middle);
        int num2 = helper(nums, middle+1, end);
        //合并
        if(num1 == num2)    return num1;
        else {
            return judge(nums, start, end, num1, num2);
        }
    }
    public int judge(int[] nums, int start, int end, int num1, int num2) {
        int tmp1 = 0, tmp2 = 0;
        for(int i=start; i<=end; i++) {
            if(nums[i] == num1) tmp1++;
            if(nums[i] == num2) tmp2++;
        }
        return (tmp1 >= tmp2) ? num1 : num2;
    }
}

/*
方法三：排序

因为这里的众数出现次数超过n/2，所以利用这种情况下中位数等于众数的性质，先排序，对应n/2下标位置的元素必定为众数。

时间复杂度O(nlogn)，空间复杂度O(1)

基本上时间用在排序上，排序采用的一般是归并排序（稳定）或快速排序（不稳定）。
*/

class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
