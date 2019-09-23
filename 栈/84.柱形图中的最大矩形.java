/*
思路：
    暴力法(优化)
    第一层循环遍历数组各个元素，
    第二层循环从该元素开始内层遍历，找局部范围面积，更新最大面积值
*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null)
            return 0;
        else if(heights.length == 1)
            return heights[0];
        int maxArea = 0;        //初始化
        for(int i=0; i<heights.length; i++) {
            int tempMin = heights[i];
            for(int j=i; j<heights.length; j++) {
                tempMin = (tempMin > heights[j]) ? heights[j] : tempMin;    //更新范围内最小值
                int tempArea = tempMin*(j-i+1);
                maxArea = (maxArea < tempArea) ? tempArea : maxArea;    //更新最大面积值
            }
        }
        return maxArea;
    }
}
/*
O(n^2)的时间复杂度
*/

/*
改进：O(n^2) -> O(n)
思路：
    采用递增栈来找当前柱子的左边、右边起头个更小的柱子的下标。
    以此来找到最大矩形的宽度                
*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null)         //处理特殊情况
            return 0;
        else if(heights.length == 1)
            return heights[0];

        int[] leftMinIndex = getleftMinIndex(heights);  //item:当前元素从左起头个更小元素的下标
        int[] rightMinIndex = getRightMinIndex(heights);    //item:当前元素从右起头个更小元素的下标

        int maxArea = 0;
        for(int i=0; i<heights.length; i++) {   //计算使用当前柱子作为矩形高度的矩形的面积, 并更新最大面积值
            int width = rightMinIndex[i] - leftMinIndex[i] - 1, height = heights[i]; //矩形的宽和高
            int tempArea = width * height;
            maxArea = Math.max(maxArea, tempArea);
        }
        return maxArea;
    }
    //得到每个元素从当前位置向右起，找到的第一个小于当前元素的位置，若没有则返回list.length
    public int[] getRightMinIndex(int[] list) {
        Stack<Integer> s = new Stack<>();
        int[] result = new int[list.length];
        for(int i=0; i<list.length; i++) {
            if(s.empty())  s.push(i);
            else {
                while(!s.empty() && list[i] < list[s.peek()])
                    result[s.pop()] = i;
                s.push(i);
            }
        }
        while(!s.empty())   result[s.pop()] = list.length;
        return result;
    }
    //得到每个元素从当前位置向左起，找到的第一个小于当前元素的位置，若没有则返回-1
    public int[] getleftMinIndex(int[] list) {
        Stack<Integer> s = new Stack<>();
        int[] result = new int[list.length];
        for(int i=list.length-1; i>=0; i--) {
            if(s.empty())  s.push(i);
            else {
                while(!s.empty() && list[i] < list[s.peek()])
                    result[s.pop()] = i;
                s.push(i);
            }
        }
        while(!s.empty())   result[s.pop()] = -1;
        return result;
    }
}
/*
时间复杂度降到了O(n)，但我们遍历了三次原数组，还创建了两个下标数组，两个递增栈
感觉有点多余了
改进：使用一个递增栈同时找到左起最小下标和右起最小下标，还计算此时面积值，并进行最大面积更新
    一次遍历原数组即可做到
*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null)         //处理特殊情况
            return 0;
        else if(heights.length == 1)
            return heights[0];

        Stack<Integer> increStack = new Stack<>();  //栈中用于维护递增元素的下标
        increStack.push(-1);    //用来设置该元素没有左起最小元素的情况
        int maxArea = 0;
        for(int i=0; i<heights.length; i++) {
            if(increStack.peek() == -1) increStack.push(i);
            else {
                while(increStack.peek() != -1 && heights[i] < heights[increStack.peek()]) {//维护栈中的递增元素
                    int height = heights[increStack.pop()]; 
                    int width = i - increStack.peek() - 1;
                    maxArea = Math.max(height*width, maxArea);  //计算当前元素的高度为矩形高的矩形面积，并更新最大面积
                }
                increStack.push(i);
            }
        }
        while(increStack.peek() != -1) {
            int height = heights[increStack.pop()];
            int width = heights.length - increStack.peek() -1;
            maxArea = Math.max(height*width, maxArea);
        }
        return maxArea;
    }
}
