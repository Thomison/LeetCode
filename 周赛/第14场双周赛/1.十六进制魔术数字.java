class Solution {
    public String toHexspeak(String num) {
        String hex16=Long.toHexString(Long.valueOf(num)).toUpperCase();
        String ret="";
        StringBuilder builder=new StringBuilder(hex16);
        for(int i=0; i<builder.length(); i++) {
            if(builder.charAt(i)=='0') builder.replace(i, i+1, "O");
            else if(builder.charAt(i)=='1') builder.replace(i, i+1, "I");
            else if(builder.charAt(i)>='2' && builder.charAt(i)<='9') return "ERROR";
        }
        return builder.toString();
    }
}
