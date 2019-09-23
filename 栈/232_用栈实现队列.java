/*
思路：
    用栈底部模拟队列头部，栈顶部模拟队列尾部
    peek\pop操作需要用到一个辅助栈，逆序输出栈中元素
    来拿到栈底元素，即队列头部元素
    入队：O(1)
    出队：O(n)  n为队列长度
*/
class MyQueue {
    private Stack<Integer> stack;
    private int size;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        size = 0;
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
        size ++;
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        Stack<Integer>  helper = new Stack<>();
        //逆序，得到栈底元素并删除
        while(!stack.empty()) {
            helper.push(stack.pop());
        }
        int result = helper.pop();
        size --;
        //还原
        while(!helper.empty()) {
            stack.push(helper.pop());
        }
        return result;
    }
    
    /** Get the front element. */
    public int peek() {
        Stack<Integer>  helper = new Stack<>();
        //逆序，得到栈底元素
        while(!stack.empty()) {
            helper.push(stack.pop());
        }
        int result = helper.peek();
        //还原
        while(!helper.empty()) {
            stack.push(helper.pop());
        }
        return result;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        // return stack.empty();
        return size == 0;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
