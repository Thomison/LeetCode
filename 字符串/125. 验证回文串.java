//递归从两端向内缩小了判断回文串
//对字符串的处理：只保留字母(小写)和数字字符
class Solution {
    public boolean isPalindrome(String s) {
        s = filter(s);
        return isPalindrome(s, 0, s.length()-1);
    }
    String filter(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if((c>='0' && c<='9') || (c>='a' && c<='z') || (c>='A' && c<='Z')) 
                sb.append(c);
        }
        return sb.toString().toLowerCase();
    }
    boolean isPalindrome(String s, int start, int end) {
        if(start > end || start == end) 
            return true;
        else if(s.charAt(start) == s.charAt(end)) {
            start++;
            end--;
            return isPalindrome(s, start, end);
        }
        return false;
    }
}
