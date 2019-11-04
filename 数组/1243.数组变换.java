class Solution {
    public List<Integer> transformArray(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        if(arr.length <= 2) {
            for(int i=0; i<arr.length; i++) ans.add(arr[i]);
            return ans;
        }
        
        int transform = 0;
        do{
            transform = 0;  //handle
            ans.clear();
            
            ans.add(arr[0]);
            for(int i=1; i<arr.length-1; i++) {     //transform
                if(arr[i] > arr[i-1] && arr[i] > arr[i+1]) {
                    ans.add(arr[i]-1);  transform++;
                }else if(arr[i] < arr[i-1] && arr[i] < arr[i+1]) {
                    ans.add(arr[i]+1);  transform++;
                }else {
                    ans.add(arr[i]);
                }
            }
            ans.add(arr[arr.length-1]);
            
            for(int i=1; i<arr.length-1; i++)       //to arr
                arr[i] = ans.get(i);
        }while(transform != 0);
        
        return ans;
    }
}
