//先通过双端bfs找到最短路径数目 再利用dfs的回溯性质搜索对应长度且能到达终点的路径 加入到ans中

class Solution {
    List<List<String>> ans;
    Map<String, Set<String>> patternMap;        //模式-单词表：表示图
    Map<String, Integer> distances;         //每个结点的最短路径
    int pathLen;            //起点到终点的最短路径


    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        ans = new ArrayList<>();
        distances = new HashMap<>();
        if(!wordList.contains(endWord))  return ans;
        
        //bfs
        pathLen = bfs(beginWord, endWord, wordList);

        if(pathLen == 0)    return ans;
        else    //dfs
            dfs(beginWord, endWord, new ArrayList<String>());
        
        return ans;
    }

    private void dfs(String s, String e, List<String> tmpList) {   
        tmpList.add(s);     //加入s到路径列表
        if(distances.get(s) == pathLen && s.equals(e)) {
            ans.add(new ArrayList<>(tmpList));      //拷贝
        }
        for(int i=0; i<s.length(); i++) {           //对s的邻居进行dfs
            char[] tmpCharArray = s.toCharArray();
            tmpCharArray[i] = '*';
            String key = String.valueOf(tmpCharArray);

            Set<String> words = patternMap.get(key);        
            for(String word : words) {
                if(distances.get(word) == distances.get(s) + 1) {      //沿着最短路径的前进方向         
                    dfs(word, e, tmpList);         
                }
            }
        }
        tmpList.remove(s);  //移除s在路径列表
    }

    private int bfs(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        if(!wordList.contains(beginWord))   wordList.add(beginWord);
        //初始化
        int cnt = 0;    //搜索到的层数 
        int ret = 0;
        int strLen = beginWord.length();
        Queue<String> queue = new ArrayDeque<>(); 
        Set<String> visited = new HashSet<>();
        patternMap = new HashMap<>();
        //预处理
        for(String word : wordList) {
            Set<String> keys = new HashSet<>();     //该单词可能的所有模式
            for(int i=0; i<strLen; i++) {       
                char[] tmp = word.toCharArray();
                tmp[i] = '*';
                keys.add(String.valueOf(tmp));
            }
            for(String key : keys) {        //在map的可能所有模式下，增加该单词
                if(!patternMap.containsKey(key)) patternMap.put(key, new HashSet<>());
                patternMap.get(key).add(word);
            }
        }
        //广度优先搜索
        queue.add(beginWord);
        visited.add(beginWord); //入队即访问
        while(!queue.isEmpty()) {
            int size = queue.size();
            cnt++;			//增加层数

            while(size != 0) {
                String word = queue.poll();
                distances.put(word, cnt);
                if(word.equals(endWord)) {    //搜索到终点
                    ret = cnt;
                }
                for(int i=0; i<strLen; i++) {       //通过它的所有模式来找邻居 加入队列
                    char[] tmp = word.toCharArray();
                    tmp[i] = '*';
                    String key = String.valueOf(tmp);
                    Set<String> tmpSet = patternMap.get(key);
                    for(String next : tmpSet) {
                        if(!visited.contains(next)) {
                            queue.add(next);
                            visited.add(next);
                        }
                    }
                }
                size--;
            }
        }
        //返回结果
        return ret;
    }
}
