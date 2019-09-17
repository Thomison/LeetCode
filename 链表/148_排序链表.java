/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//引言：排序分两种类型：比较(遍历)排序，分治(递归)排序，前者时间复杂度为O(n^2)，后者为O(n*logn)
//     排序其实可以不用将结点参与排序，改变结点的位置
//      而是将结点中的值参与排序，改变值所处的结点位置

/*
思路：采用归并排序merge
     终止条件：链表只有一个结点
     分解：找到链表的中点，从此处将链表断开
     合并：将两个有序链表，合成一个有序链表
     返回值：合并好的有序子链表
     设计：递归分解链表，返回调用合并方法后生成的链表
*/

//直接在链表上进行归并排序，
//分解的关键在于找到链表中点
//合并的关键在于不用创建额外空间的前提下，用递归合并有序子链表
/*
考查知识点：
    1)归并排序整体的思想(整体时间复杂度O(n*logn))
        先从中间分解，递归上述过程，从最底层开始合并有序的两部分，返回给上一级
    2)找到链表中间结点的方法(时间O(n/2) 空间O(1))
        利用快慢指针，快指针到达链表末尾的时候，慢指针的位置即为中间结点，注意需要从中间结点断开，得到两个子链表
    3)将两个有序链表合并成一个有序链表(时间O(m+n) 空间O(1))
        迭代或者递归
*/
class Solution {
    
    public ListNode sortList(ListNode head) {
        return head == null ? head : helper(head);
    }
    
    public ListNode helper(ListNode head) {
        if(head.next == null)    return head;
        //找到链表中点，分解链表成两个子链表(快慢指针)
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode left = head, right = slow.next;
        slow.next = null;
        //递归的按照中点分解链表
        ListNode l1 = helper(left);
        ListNode l2 = helper(right);
        //合并两个有序子链表
        return merge(l1, l2);
    }
    
    public ListNode merge(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
         
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while(l1 != null || l2 != null) {
            if(l1 == null) {
                temp.next = l2;
                break;
            }else if(l2 == null) {
                temp.next = l1;
                break;
            }else {
                if(l1.val <= l2.val){
                    temp.next = l1;
                    l1 = l1.next;
                }else {
                    temp.next = l2;
                    l2 = l2.next;
                }
            }
            temp = temp.next;
        }
        return dummy.next;
    }
}



//原先版本
//先将链表转换为数组，在数组上归并排序，再将数组转换为链表
class Solution {
    public ListNode sortList(ListNode head) {
        //处理肯定不用排序的情况
        if(head == null || head.next == null)
            return head;
        //将链表中的值按位置提取出来到数组列表中
        List<Integer> values = linkedListToList(head);
        //调用数组列表上的归并排序方法
        values = mergeSortInList(values, 0, values.size()-1);
        //将数组列表排好序的值以此填充到链表的结点中
        ListNode temp = head;
        for(int i=0; i<values.size(); i++){
            temp.val = values.get(i);
            temp = temp.next;
        }
        //返回链表头结点
        return head;
    }
    
    //将链表转换为数组列表
    public List<Integer> linkedListToList(ListNode head){
        List<Integer> result = new ArrayList<>();
        ListNode temp = head;
        while(temp != null){
            result.add(temp.val);
            temp = temp.next;
        }
        return result;
    }
    
    //在数组列表上进行归并排序
    public List<Integer> mergeSortInList(List<Integer> list, int from, int to){
        if(from == to){
            List<Integer> single = new ArrayList<>(1);
            single.add(list.get(from));
            return single;
        }
        
        int middle = from + (to - from) / 2;
        //递归来分解为两个子数组列表
        List<Integer> listA = mergeSortInList(list, from, middle);
        List<Integer> listB = mergeSortInList(list, middle+1, to);
        
        //返回合并好的有序数组列表
        return merge(listA, listB);
    }
    
    //合并两个有序数组列表
    public List<Integer> merge(List<Integer> listA, List<Integer> listB) {
        if(listA.size() == 0)
            return listB;
        else if(listB.size() == 0)
            return listA;
        
        List<Integer> result = new ArrayList<>(listA.size() + listB.size());
        int i = 0, j =0;
        while(i != listA.size() || j != listB.size()) {
            if(i == listA.size()) {
                result.add(listB.get(j)); j ++;
            }else if(j == listB.size()) {
                result.add(listA.get(i)); i ++;
            }else {
                if(listA.get(i) <= listB.get(j)) {
                    result.add(listA.get(i)); i ++;
                }else {
                    result.add(listB.get(j)); j ++;
                }
            }    
        }
        return result;
    }
}
