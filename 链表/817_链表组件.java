/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int numComponents(ListNode head, int[] G) {
        int num_components = 0;
        ListNode temp = head;
        
        //整型数组转换为列表
        List<Integer> list = Arrays.stream(G).boxed().collect(Collectors.toList());
        // List<Integer> list = new ArrayList<>(G.length);
        // for(int i=0; i<G.length; i++)   list.add(G[i]);
        
        while(temp != null) {
            if(list.contains(temp.val)) {
                while(temp != null && list.contains(temp.val)) 
                    temp = temp.next;
                num_components ++;
            }
            if(temp != null) 
                temp = temp.next;
        return num_components;
    }
}
//总结：时间复杂度、空间复杂度都太高
//转换列表的时间、contains查询比较的时间、遍历链表的时间


//利用集合的contains()方法
class Solution {
    public int numComponents(ListNode head, int[] G) {
        int num_components = 0;
        ListNode temp = head;
        
        //将数组中的元素存储到集合set中
        Set<Integer> set = new HashSet<>(G.length);
        for(int i = 0; i < G.length; i ++)   set.add(G[i]);
        
        //遍历链表
        while(temp != null) {
            //进入子链表
            if(set.contains(temp.val)) {
                while(temp != null && set.contains(temp.val)) 
                    temp = temp.next;
                //到达子链表的末尾
                num_components ++;
            }
            //继续遍历下一个结点
            if(temp != null) 
                temp = temp.next;
        }
        return num_components;
    }
}
    
    
    
//改进思路：因为链表中的值在一定范围内，可采用空间换时间的做法，时间O(n)、空间O(10000)
class Solution {
    public int numComponents(ListNode head, int[] G) {
        int num_components = 0;
        ListNode temp = head;
        
        boolean[] isContain = new boolean[10000];
        for(int i = 0; i < 10000; i ++)
            isContain[i] = false;
        for(int i = 0; i < G.length; i ++)
            isContain[G[i]] = true;
        
        while(temp != null) {
            if(isContain[temp.val]) {
                while(temp != null && isContain[temp.val]) 
                    temp = temp.next;
                num_components ++;
            }
            if(temp != null) 
                temp = temp.next;
        }
        return num_components;
    }
}
