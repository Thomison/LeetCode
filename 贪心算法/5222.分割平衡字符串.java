/*
思路：每个子串是连续的，L和R个数相等，并且要找到最小可分割子串，所以遍历字符串数组，只要遍历到L和R数目相等，就做一次分割，而且要保证前面的分割不会影响后续子串的统计。

两次遍历数组，

第一次遍历数组是将原字符串数组转化为1 , -1的整型数组，分别L表示-1，R表示1。

第二次遍历整型数组，每遍历到一个元素时就计算前面子数组的和，当和为0时，表示这里是一个分割点， 从这里分割因为和未0，所以不会影响后续分割。

T O(2n)
*/

class Solution {
    public int balancedStringSplit(String s) {
        int[] helper = new int[s.length()];
        for(int i=0; i<s.length(); i++) {
            helper[i] = (s.charAt(i) == 'L') ? -1 : 1;
        }
        int tmp = 0, ans = 0;
        for(int i=0; i<helper.length; i++) {
            tmp += helper[i];
            if(tmp == 0)    ans ++;
        }
        return ans;
    }
}

/*
改进：

一次遍历数组

T O(n)
*/
class Solution {
    public int balancedStringSplit(String s) {
        int tmp = 0, ans = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == 'L')  tmp--;
            else    tmp++;
            
            if(tmp == 0)    ans++;
        }
        return ans;
    }
}
