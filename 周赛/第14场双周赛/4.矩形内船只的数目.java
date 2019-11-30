class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if(!sea.hasShips(topRight, bottomLeft)) return 0;
        
        int ret=0;
        if(bottomLeft[0]<topRight[0]) {
            int m=(bottomLeft[0]+topRight[0])/2;
            ret+=countShips(sea, new int[]{m, topRight[1]}, bottomLeft);
            ret+=countShips(sea, topRight, new int[]{m+1, bottomLeft[1]});
        }else if(bottomLeft[1]<topRight[1]) {
            int m=(bottomLeft[1]+topRight[1])/2;
            ret+=countShips(sea, topRight, new int[]{bottomLeft[0], m+1});
            ret+=countShips(sea, new int[]{topRight[0], m}, bottomLeft);
        }else {
            ret=1;
        }
        return ret;
    }
}
