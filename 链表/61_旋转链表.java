// 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。


/*
​ 问题本质上就是返回：以倒数第（k%n）个结点为头结点，连接链表尾部和头部，以倒数第（k%n + 1）个结点为尾结点，并且尾结点的引用需要指向null

​ 等价于：先成环，找准断开的位置后再断开，返回断开处的头结点即可。
*/

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
      	//1,特殊情况：链表为空或只有一个结点时，直接返回链表。
        if(head == null)    return head;
        else if(head.next == null)  return head;
        //2,计算链表长度length
        int length = 0;
        ListNode temp = head;
        while(temp != null){
            length ++;
            temp = temp.next;
        }
      	//3,若k%n=0,则链表旋转后等价于原链表，直接返回
        if(k % length == 0) return head;
        //4,处理k,k=k%length
        k = k % length;
        int startIndex = length - k +1;
        int endIndex = length - k;
      	//5,向右移动k个位置（旋转过程）
        ListNode temp2 = head;
        ListNode startNode = null, endNode = null;
        int i = 0;
        while(true){
            i ++;
          	//得到开头结点和结尾结点
            if(i == endIndex){
                endNode = temp2;
            }else if(i == startIndex){
                startNode = temp2;
            }
            temp2 = temp2.next;
            if(startNode != null && endNode != null) break;
        }
        //6,将链表连接成环
        ListNode temp3 = head;
        while(temp3 != null){
            temp3 = temp3.next;
            if(temp3.next == null){
                temp3.next = head;
                break;
            }
        }
        //7,将结尾结点的指针域指向null，实现断开环
        endNode.next = null;
      	//8,返回处理好的链表的头结点
        return startNode;
    }
}
