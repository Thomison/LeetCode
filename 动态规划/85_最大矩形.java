/*
方法一：

将问题转化为对row = matrix.length个柱形数组，在每个矩形数组中求解最大矩形， 再比较这row个数的矩形，得到最大矩形。

我们只需要遍历这个二维数组的行，每一行计算出一个柱状图的数组，再调用84题利用单调栈（一次遍历）计算得到结果，每次遍历更新最大值。

如row == 2时，我们得到的柱状图数组{3, 1, 3, 2, 2} ;

T O(MN) S O(MN) :M为二维数组行数，N为列数
*/
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int ans=0;
        for(int i=0; i<matrix.length; i++) {
            int[] heights=new int[matrix[i].length];//构造每一行的高度数组
            for(int j=0; j<matrix[i].length; j++) {
                for(int k=i; k>=0; k--) {   //从下往上计算高度
                    if(matrix[k][j]=='1') heights[j]++;
                    else break;
                }
            }
            ans=Math.max(largestRectangleArea(heights), ans);//更新最大矩形面积
        }
        return ans;
    }
    //调用84题的方法，计算一个表示高度的数组所能构成的最大矩形面积(单调栈)
    public int largestRectangleArea(int[] heights) {
        if(heights == null) return 0;
        else if(heights.length == 1) return heights[0];

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

/*
方法二：动态规划
我们现在尝试不用单调栈来计算每一行的最大矩形面积，而是采用动态规划

维护三个数组：
  left_j[j]表示第i行，第j列元素柱形高度从左起第一小于它的下标
  right_j[j]表示第i行，第j列元素柱形高度从右起第一小于它的下标
  height_j[j]表示第i行，第j列元素柱形高度

因为我们要遍历每一行，求解每一行所能构成的最大矩形面积，当遍历到元素(i, j)时，在计算以当前元素的柱形作为矩形高度的最大矩形面积时，就需要用到三个值：
  当前柱形的高度：用来确定矩形的宽
  从左起第一个高度小于它的下标：用来确定矩形的长
  从右起第一个高度小于它的下标：用来确定矩形的长
矩形的面积 = height_j[j] * (right_j[j] - left)j[j] - 1) 

且我们发现我们维护的三个数组到下一行时，是跟上一行存在关联的，也就是说存在状态转移规律的，这一点也支撑了我们用动态规划来记忆每个元素的这几个关键值。

T O(MN) S O(MN) :M为二维数组行数，N为列数
*/
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0) return 0;
        
        int ans=0;
        int row=matrix.length, col=matrix[0].length;
        int[] left_j=new int[col];
        Arrays.fill(left_j, -1);
        int[] right_j=new int[col];
        Arrays.fill(right_j, col);
        int[] height_j=new int[col];
        
        for(int i=0; i<row; i++) {
            int curr_left=-1, curr_right=col;
            //dp
            for(int j=0; j<col; j++) {
                if(matrix[i][j]=='1') height_j[j]++;
                else height_j[j]=0;
            }
            for(int j=0; j<col; j++) {
                if(matrix[i][j]=='1') left_j[j]=Math.max(left_j[j], curr_left);
                else {
                    curr_left=j;
                    left_j[j]=-1;
                }
            }
            for(int j=col-1; j>=0; j--) {
                if(matrix[i][j]=='1') right_j[j]=Math.min(right_j[j], curr_right);
                else {
                    curr_right=j;
                    right_j[j]=col;
                }
            }
            //get the area
            for(int j=0; j<col; j++) {
                 ans = Math.max(height_j[j] * (right_j[j]- left_j[j]-1), ans);
            }
        }
        return ans;
    }
}
