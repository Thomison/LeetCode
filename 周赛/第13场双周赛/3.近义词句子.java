class Solution {
    List<List<String>> synonyms;
    Set<String> set;       //存储近义词字典的集合
    String text;
    String[] words;         //以空格划分句子，得到字符串数组
    Map<String, Set<String>> map;       //近义词映射近义词集合
    List<String> ans;

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        initial(synonyms, text);    //初始化set和map

        makeSynonyms();     //生成map

        dfs(0, words.length-1, ""); //对words进行dfs

        Collections.sort(ans);  //字典序排序

        return ans;
    }
    //初始化
    void initial(List<List<String>> synonyms, String text) {
        this.synonyms = synonyms;
        set = new HashSet<>();
        for(int i=0; i<synonyms.size(); i++) {
            for(int j=0; j<synonyms.get(i).size(); j++) {
                set.add(synonyms.get(i).get(j));
            }
        }
        this.text = text;
        words = text.split(" ");
        map = new HashMap<>();
        ans = new ArrayList<>();
    }
    //合并近义词集合
    void makeSynonyms() {
        for(List<String> synonym : synonyms) {  //取出一对近义词
            Set<String> set1 = map.getOrDefault(synonym.get(0), new HashSet<>());
            Set<String> set2 = map.getOrDefault(synonym.get(1), new HashSet<>());

            if(set1 != set2) {  //合并两个集合
                Set<String> ret = new HashSet<>(set1);  //排除重复元素
                ret.addAll(set2);

                ret.add(synonym.get(0));        //添加这这一对近义词到集合中
                ret.add(synonym.get(1));

                for(String s : ret) {
                    map.put(s, ret);        //更新近义词集合中所有字符串的映射对
                }
            }
        }
    }
    //dfs生成所有的句子
    void dfs(int curr, int end, String tmp) {
        if(curr > end) {
            tmp = tmp.substring(0, tmp.length()-1); //删除结尾处的空格
            ans.add(tmp);
            return;
        }
        if(set.contains(words[curr])) {
            Set<String> set = map.get(words[curr]);
            for(String s : set) {       //对所有的近义词进行dfs
                dfs(curr+1, end, tmp + s + " ");
            }
        }else {
            dfs(curr+1, end, tmp + words[curr] + " ");
        }
    }
}
