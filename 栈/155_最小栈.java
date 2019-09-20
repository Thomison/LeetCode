/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

//维护两个栈：分别用于存储正常元素和最小元素，空间换取时间
//辅助栈: 存储的是当前元素（含）之前的最小值
//使得getMin()方法时间复杂度O(1),空间复杂度O(2n)
//需要注意的是主栈和辅助栈的同步
//  入栈，最小值入栈
//  出栈，最小值出栈
class MinStack {
    private Stack<Integer> norm_stack;   //存储压如栈的元素
    private Stack<Integer> min_stack;        //维护一个存储最小值得栈
    
    public MinStack() {
        norm_stack = new Stack<>();
        min_stack = new Stack<>();
    }
    
    public void push(int x) {
        if(norm_stack.empty())      //栈为空时
            min_stack.push(x);
        else {  //栈不为空时
            if(x < min_stack.peek())    //最小值入辅助栈
                min_stack.push(x);
            else
                min_stack.push(min_stack.peek());   //未更新，取辅助栈之前的栈顶元素，然后入栈
        }  
        norm_stack.push(x); //入主栈
    }
    
    public void pop() {
        norm_stack.pop();   //出主栈
        min_stack.pop();    //最小值出辅助栈
    }
    
    public int top() {
        return norm_stack.peek();
    }
    
    //要求：O(1)时间复杂度检索到最小元素
    public int getMin() {
        return min_stack.peek();
    }
}
