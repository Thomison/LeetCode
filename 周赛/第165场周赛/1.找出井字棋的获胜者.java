/*************************************************************************
    > File Name: 1.找出井字棋的获胜者.java
    > Author: 唐以恒
    > Mail: 1021953309tyh@gmail.com 
    > Created Time: 2019年12月02日 星期一 13时45分48秒
 ************************************************************************/
class Solution {
    public String tictactoe(int[][] moves) {
        char[][] flag=new char[3][3];   //默认为'0'
        for(int i=0; i<moves.length; i++) {
            if(i%2==0) flag[moves[i][0]][moves[i][1]]='X';//A
            else flag[moves[i][0]][moves[i][1]]='O'; //B
        }
        String winner="";
        for(int i=0; i<3; i++) {    //检查行
            if(flag[i][0]==flag[i][1] && flag[i][1]==flag[i][2]) {
                if(flag[i][0]=='X') winner="A";
                else if(flag[i][0]=='O') winner="B";
            }
        }
        for(int j=0; j<3; j++) {    //检查列
            if(flag[0][j]==flag[1][j] && flag[1][j]==flag[2][j]) {
                if(flag[0][j]=='X') winner="A";
                else if(flag[0][j]=='O') winner="B";
            }
        }
        if(flag[0][0]==flag[1][1] && flag[1][1]==flag[2][2]) { //检查对角线
            if(flag[1][1]=='X') winner="A";
            else if(flag[1][1]=='O') winner="B";
        }
        if(flag[2][0]==flag[1][1] && flag[1][1]==flag[0][2]) { 
            if(flag[1][1]=='X') winner="A";
            else if(flag[1][1]=='O') winner="B";
        }
        if(moves.length==9 && winner.equals("")) return "Draw";
        else if(moves.length<9 && winner.equals("")) return "Pending";
        else return winner;   
    }
}
