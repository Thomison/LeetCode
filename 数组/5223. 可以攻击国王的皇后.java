/*
思路：从king的坐标出发，有八个前进方向，在这八个前进方向找到第一个遇到的queen，或者碰壁。

简单来说，就是在数组上进行的操作，需要注意的是我将queens二维数组转化为了二维列表，方便使用contains方法来查询king当前走到的坐标是否存在有queen 。
*/

class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> ans = new ArrayList<>();
        //queens二维数组转化为二维列表
        List<List<Integer>> queens_list = new ArrayList<>();
        for(int i=0; i<queens.length; i++) {
            queens_list.add(Arrays.asList(queens[i][0], queens[i][1]));
        }
        //定义king前进的方向
        int[][] dire = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        int startX = king[0], startY = king[1];
        //在每一个方向上尝试是否能够碰到queen
        for(int i=0; i<dire.length; i++) {
            int tmpX = startX, tmpY = startY;
            while(tmpX >= 0 && tmpX <= 7 && tmpY >=0 && tmpY <= 7) {    //不碰壁的情况下
                tmpX += dire[i][0];
                tmpY += dire[i][1];
                List<Integer> tmp = Arrays.asList(tmpX, tmpY);
                if(queens_list.contains(tmp)) {ans.add(tmp); break;}    //添加遇到的第一个queen
            }
        }
        return ans;
    }
}
