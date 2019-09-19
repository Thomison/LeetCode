/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
思路：要找到每一个结点的next_larger_value，需要遍历链表每一个结点，当遍历到结点node_i
        再嵌入一个node_i+1结点开始的遍历，找到next_larger_node
        其值即是我们输出的数组元素answerp[i-1]值
    时间复杂度O(n^2)，空间复杂度O(n)
*/
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        if(head == null)
            return null;
        
        //计算链表长度
        ListNode temp = head;
        int length = 0;
        while(temp != null) {
            temp = temp.next;
            length ++;
        }
        temp = head;
        //初始化目标数组
        int[] answer = new int[length];
        
        //遍历链表，找next_large_node
        for(int i = 0; i < length; i ++){
            ListNode next_larger = temp.next;
            
            //嵌入遍历子链表，从temp当前结点的下一个结点开始
            while(next_larger != null) {
                if(next_larger.val > temp.val) {
                    answer[i] = next_larger.val;
                    break;
                } else {
                    next_larger = next_larger.next;
                }
            }
            
            //子链表中不存在比当前结点的值大的结点
            if(next_larger == null)
                answer[i] = 0;
            temp = temp.next;
        }
        
        return answer;
    }
}
//总结：时间复杂度有点高，跑完测试用例用了800ms, 此方法是暴力法

//思路二：
//我们发现，当其中一段序列以递减顺序时，直到遇到打破这个顺序的结点
//递减顺序中小于这个结点的部分，这些结点的next_larger都是这个结点
//然后这个结点加入到递减序列，直到遇到下一个打破此顺序的结点
//需要比较这个结点和之前递减顺序的节点的值，而且逆序比较
//要用到栈的回溯特性FILO

class Solution {
    public int[] nextLargerNodes(ListNode head) {
        if(head == null)
            return null;
        
        //计算链表长度
        ListNode temp = head;
        int length = 0;
        while(temp != null) {
            temp = temp.next;
            length ++;
        }
        temp = head;
        
        //初始化目标数组和用于存储结点的栈
        int[] answer = new int[length]; 
        Stack<ListNode> stack = new Stack<>();
        while(temp != null) {
            if(stack.empty())
                stack.push(temp);
            else {
                if(temp.val > stack.peek().val) {
                    //将所有小于temp.val的结点pop弹出堆栈,并更新结点的val值
                    while(!stack.empty() && temp.val > stack.peek().val) {
                        stack.pop().val = temp.val;
                    }
                }
                //将结点push进入堆栈
                stack.push(temp);
            } 
            temp = temp.next;
        }
        while(!stack.empty()) {
            stack.pop().val = 0;
        } 
        temp = head;
        
        for(int i = 0; i < length; i ++) {
            answer[i] = temp.val;
            temp = temp.next;
        }
        return answer;
    }
}
