class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        int left=toBeRemoved[0], right=toBeRemoved[1];
        List<List<Integer>> ret=new ArrayList<>();
        for(int i=0; i<intervals.length; i++) {
            int x=intervals[i][0], y=intervals[i][1];
            
            if(left<=x && y<=right) continue;
            else if(x<left && y>right) {
                List<Integer> tmp=new ArrayList<>();
                List<Integer> tmp2=new ArrayList<>();
                tmp.add(x); tmp.add(left);
                tmp2.add(right); tmp2.add(y);
                ret.add(tmp); ret.add(tmp2);
            }
            else if(x<left && left<=y && y<=right) {
                List<Integer> tmp=new ArrayList<>();
                tmp.add(x); tmp.add(left);
                ret.add(tmp);
            }
            else if(left<=x && x<=right && right<y) {
                List<Integer> tmp=new ArrayList<>();
                tmp.add(right); tmp.add(y);
                ret.add(tmp);
            }else {
                List<Integer> tmp=new ArrayList<>();
                tmp.add(x); tmp.add(y);
                ret.add(tmp);
            }
        }
        return ret;
    }
}
