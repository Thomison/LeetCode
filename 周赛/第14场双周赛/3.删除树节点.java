class Solution {
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        //保存每个结点对应的子树所有节点值之和
        int[] tree_value=new int[value.length];
        int ret=nodes;
        for(int i=parent.length-1; i>=0; i--) {
            int j=i;
            while(j!=-1) {
                tree_value[j]+=value[i];
                j=parent[j];
            }
        }
        //断开子树和-1的连接
        for(int i=0; i<parent.length; i++) {
            if(tree_value[i]==0) {
                parent[i]=-2;
            }
        }
        //若结点沿着路径不能到达-1 则说明被删除
        for(int i=parent.length-1; i>=0; i--) {
            int j=i;
            while(j>0) {
                j=parent[j];
            }
            if(j==-2) ret--;
        }
        return ret;
    }
}
