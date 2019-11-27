/*************************************************************************
    > File Name: 8.字符串转整数atoi.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年11月28日 星期四 00时54分54秒
 ************************************************************************/
//去除空格前缀后以数字开头的字符串返回其最多能截取长度的整数形式，否则返回0
/*
 * 一些恶心的测试用例
 * ""
 * " "
 * "+"
 * "- 0000123"
 *
 */
/*
Integer:
    parseInt(String s)
    - Parses the string argument as a signed decimal integer.
    - return int.
*/
//3ms
class Solution {
    public int myAtoi(String str) {
        // return Integer.parseInt(str);
        int max=Integer.MAX_VALUE;
        int min=Integer.MIN_VALUE;
        if(str==null || str.length()==0) return 0;
        StringBuilder builder=new StringBuilder(str);
        while(builder.length()>0 && builder.charAt(0)==' ') builder.deleteCharAt(0);    //删除前缀空格
        if(builder.length()==0) return 0;

        if(builder.charAt(0)=='-') {
            int start=1, end=start;
            while(end<builder.length() && builder.charAt(end)>='0' && builder.charAt(end)<='9') {
                if(builder.charAt(start)=='0') start++;
                end++;
            }
            String tmp="-"+builder.substring(start, end);
            if(tmp.equals("-")) return 0;
            else {
                if(tmp.length()>11) return min;
                long ret=Long.parseLong(tmp);
                if(ret<=min) return min;
                else return (int)ret;
            }
        }else if(builder.charAt(0)=='+' || (builder.charAt(0)>='0' && builder.charAt(0)<='9')) {
            int start=1, end=start;
            if(builder.charAt(0)!='+') start=0;
            while(end<builder.length() && builder.charAt(end)>='0' && builder.charAt(end)<='9') {
                if(builder.charAt(start)=='0') start++;
                end++;
            }
            String tmp=builder.substring(start, end);
            if(tmp.length()==0) return 0;
            else {
                if(tmp.length()>11) return max;
                long ret=Long.parseLong(tmp);
                if(ret>=max) return max;
                else return (int)ret;
            }
        }
        else return 0;
    }
}
