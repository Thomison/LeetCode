class Solution {
    public int minimumSwap(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return -1;
        }
        
        int xToy = 0;
        int yTox = 0;
        for(int i=0; i<s1.length(); i++) {
            if(s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            if(s1.charAt(i) == 'x' && s2.charAt(i) == 'y') {
                xToy++;
            }else if(s1.charAt(i) == 'y' && s2.charAt(i) == 'x') {
                yTox++;
            }
        }
        
        if(xToy % 2 == 1 && yTox % 2 == 1) {
            return xToy / 2 + yTox / 2 + 2;
        }else if(xToy % 2 == 0 && yTox % 2 == 0){
            return xToy / 2 + yTox / 2;
        }else {
            return -1;
        }
    }
}
