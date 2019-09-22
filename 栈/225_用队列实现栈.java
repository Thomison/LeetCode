//每次pop和top操作需要花费O(n)的空间创建一条辅助队列
class MyStack {
    private Queue<Integer> queue;
    private int size;
    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
        size = 0;
    }
    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        size ++;
    }
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        Queue<Integer> helper = new LinkedList<>();
        for(int i=0; i<size-1; i++) 
            helper.offer(queue.poll());
        int result = queue.peek();
        queue = helper;
        size --;
        return result;
    }
    /** Get the top element. */
    public int top() {
        int result = pop();     //pop操作使得栈大小减一
        queue.offer(result);
        size ++;   
        return result;
    }
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
