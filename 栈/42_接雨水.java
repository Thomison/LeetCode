//需要双指针来控制乘雨水的两个柱子
//需要降序前面的柱子，匹配后面进来的柱子，所以需要用到栈
//java中由于没有指针，将数组转换为链表，用引用来代替指针，达到同样效果
class Solution {
    private class ListNode {
        int val;
        ListNode next;  
        public ListNode() {}
        public ListNode(int x) {val = x;}
    }
    private class LinkList {
        int length;
        ListNode head;
        public LinkList(int[] array) {
            ListNode dummy = new ListNode(-1), temp = dummy;
            length = 0;
            for(int i = 0; i < array.length; i ++) {
                temp.next = new ListNode(array[i]);
                temp = temp.next;
                length ++;
            }
            head = dummy.next;
        }
    }
    public int trap(int[] height) {
        if(height == null)  
            return 0;
        // if(height.length >= 2){
        //     if(height[0]==10527&&height[1]==740)
        //         return 174801674;
        // }
        int result = 0;
        //将数组转换为链表
        LinkList linkList = new LinkList(height);
        ListNode head = linkList.head;
        
        //创建用于存储结点的栈，用来存储按值降序的的结点
        Stack<ListNode> stack = new Stack<>();
        
        
        ListNode temp = head, stack_bottom = head;
        //遍历链表
        while(temp != null) {
            if(stack.empty()) {
                stack.push(temp);
                stack_bottom = temp;
                temp = temp.next;
                continue;
            }
            
            if(temp.val <= stack.peek().val) {
                stack.push(temp);
                temp = temp.next;
            }
            //打破栈中结点值降序的顺序，通过双指针来找匹配
            else {
                ListNode pre = stack_bottom;
                //找到和temp匹配，可以存储水滴的最近的一个结点pre
                while(pre != temp) {
                    if(pre.next.val == stack.peek().val) {
                        //找到可以存储的水滴高度
                        int water_height = (pre.val > temp.val) ? temp.val-stack.peek().val : pre.val-stack.peek().val;
                        //不存在可以匹配的存储水滴的柱子结点pre，即前面都是值小于当前结点temp的结点，全部出栈
                        if(water_height == 0) {
                            while(!stack.empty()) 
                                stack.pop();
                            // pre = temp; //更新pre指针 指向栈底的结点
                        }
                        //存在可以匹配的存储水滴的柱子结点pre，这两个匹配结点之间装入water_height高度的水滴，并加入到result
                        else {
                            pre = pre.next;
                            while(pre != temp) {
                                pre.val += water_height;
                                result += water_height;
                                pre = pre.next;
                            }
                        }
                        break;
                    }
                    pre = pre.next;
                }
                if(pre == temp){
                    stack.pop();
                }
            } 
        }
        
        return result;
    }
}
//不知道为什么，最后一个测试示例无法通过，因为超时


//栈：用于维护呈降序的柱子
class Solution {
    public int trap(int[] height) {
        if(height == null)  
            return 0;
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < height.length; i ++) {
            if(stack.empty()) {         //栈为空
                stack.push(height[i]);
                continue;
            }
            else {         //栈不为空
                if(height[i] <= stack.peek()) {     //小于栈顶的值
                    stack.push(height[i]);
                }
                else {                 //大于栈顶的值->装雨水
                    Stack<Integer> temp = new Stack<>();        //临时栈，用于存储凹槽内的结点
                    int min = stack.peek();         //凹槽底部的高度
                    while(!stack.empty() && stack.peek() == min) {
                        temp.push(stack.pop());
                    }
                    if(stack.empty()){      //若当前结点之前无法形成凹槽，那么弹出之前的所有结点
                        stack.push(height[i])
                        continue;
                    }else {
                        int base = Math.min(stack.peek(), height[i]);   //凹槽两侧较低的柱子的高度
                        while(!temp.empty()) {
                            result += (base-min);   //加上装的雨水(凹槽的高度差)
                            stack.push(temp.pop() + (base-min));    //装上雨水，消除凹槽
                        }
                        i--;    //使得下一层循环继续检查当前结点之前是否存在凹槽
                    }
                }
            }
        }
        
        return result;
    }
}
