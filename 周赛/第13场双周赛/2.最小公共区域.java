class Solution {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        String ans = "";
        Map<String, String> child_parent = new HashMap<>();
        
        for(int i=0; i<regions.size(); i++) {
            String parent = regions.get(i).get(0);
            if( !child_parent.containsKey(parent) ) {
                child_parent.put(parent, null);
            }
            for(int j=1; j<regions.get(i).size(); j++) {
                child_parent.put(regions.get(i).get(j), parent);
            }
        }
        
        List<String> list1 = new ArrayList<>();
        while(region1 != null) {
            list1.add(region1);
            region1 = child_parent.get(region1);    //to parent
        }
        List<String> list2 = new ArrayList<>();
        while(region2 != null) {
            list2.add(region2);
            region2 = child_parent.get(region2);    //to parent
        }
        for(int i=0; i<list1.size(); i++) {
            if(list2.contains(list1.get(i))) {
                ans = list1.get(i);
                break;
            }
        }
        
        return ans;
    }
}
