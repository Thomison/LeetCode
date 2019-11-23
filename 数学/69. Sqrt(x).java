Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

//遍历0~n^0.5 出现前一个数的平方小于等于ｘ，而后一个数的平方大于ｘ时，返回这个数，需要注意整型溢出的问题

//19ms O(n^0.5)
class Solution {
    public int mySqrt(int x) {
        // return (int)Math.pow(x, 0.5);
        for(int i=0; i<x; i++) {
            if(i*i<=x && ((i+1)*(i+1)>x || (i+1)*(i+1)<0)) {
                return i;
            }
        }
        return x;
    }
}

//牛顿整除法 按照公式进行迭代
//https://en.wikipedia.org/wiki/Integer_square_root#Using_only_integer_division

//2ms
class Solution {
    public int mySqrt(int x) {
        long r = x;
        while (r*r > x) {
        	r = (r + x / r) / 2;
        }
        return (int)r;
    }
}
