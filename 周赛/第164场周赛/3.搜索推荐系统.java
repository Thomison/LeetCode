class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans=new ArrayList<>();
        List<String> products_list=Arrays.asList(products);
        Collections.sort(products_list);
        String tmp="";
        List<String> tmp_list=new ArrayList<>();
        for(int i=0; i<searchWord.length(); i++) {
            tmp+=searchWord.charAt(i);
            for(int j=0; j<products_list.size(); j++) {
                if(products_list.get(j).startsWith(tmp)) {
                    tmp_list.add(products_list.get(j));
                }
            }
            List<String> ans_tmp=new ArrayList<>(3);
            int cnt=0;
            while(cnt<3 && cnt<tmp_list.size()) {ans_tmp.add(tmp_list.get(cnt));cnt++;}
            ans.add(ans_tmp);
            
            products_list=tmp_list;
            tmp_list=new ArrayList<>();
        }
        return ans;
    }
}
