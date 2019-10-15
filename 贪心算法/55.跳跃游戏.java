/*
思路：

若数组中的元素都为非零，由于数组中每个元素存储的是能够跳的最大长度，若每次都只跳一个单位，总能跳到终点。

跳不到终点的情况是：

(遍历0到n-1的元素)存在元素存储的是0，且该元素之前的元素都只能到该元素，不能跨过去

这种情况返回false
*/

class Solution {
    public boolean canJump(int[] nums) {
        for(int i=0; i<nums.length-1; i++) {
            if(nums[i] == 0) {  //单独讨论0到n-1的元素中步长为0的元素
                boolean tmp = false;
                for(int j=0; j<i; j++) {
                    //只要存在一个能够跨过去的，表示该0不能限制到达终点
                    if(j + nums[j] > i) { tmp = true; break;}
                }
                if(!tmp)    return false;   //如果都不能跨过去，表示不能到达终点
            }
        }
        return true;
    }
}
