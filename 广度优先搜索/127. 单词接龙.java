class Solution {
    int ans = 0;    //搜索到的层数 
    List<List<Integer>> graph;      //邻接线性表表示图
    int start;  //起点的下标
    int end;    //终点的下标
    int size;   //顶点的数量
    int str_len;    //字符串长度
    boolean[] isVisited;    //标记顶点是否被访问
    Queue<Integer> queue;   //辅助bfs的队列

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) 
            return ans;
        else 
            end = wordList.indexOf(endWord);		//记录终点
        //预处理
        str_len = beginWord.length();
        if(!wordList.contains(beginWord)) {		//起点未在字典中，需要先加入
            wordList.add(beginWord);
            start = wordList.size()-1;		//记录起点
        }else start = wordList.indexOf(beginWord);
        size = wordList.size();
        //初始化图
        graph = new ArrayList<>(size);
        for(int i=0; i<size; i++) 
            graph.add(new ArrayList<>());
        for(int i=0; i<size; i++) {
            String str1 = wordList.get(i);
            for(int j=0; j<size; j++) {
                if(i == j) continue;
                String str2 = wordList.get(j);
                int flag = 0;
                for(int k=0; k<str_len; k++) {      //比较两个字符串
                    if(str1.charAt(k) != str2.charAt(k)) flag++;
                }
                if(flag == 1)   graph.get(i).add(j);
            }
        }
        //广度优先搜索
        isVisited = new boolean[size];
        queue = new ArrayDeque<>();
        queue.offer(start);     //加入起点
        while(!queue.isEmpty()) {
            int len = queue.size(); //该层顶点数
            ans ++;     //层数加一
            while(len != 0) {
                int tmp = queue.poll();
                isVisited[tmp] = true;
                if(tmp == end) {        //搜索到终点 终止
                    return ans;
                }
                List<Integer> tmp_list = graph.get(tmp);
                for(int i=0; i<tmp_list.size(); i++) {  //加入刚出队列的顶点的未被访问的邻居
                    if(!isVisited[tmp_list.get(i)]) {
                        queue.offer(tmp_list.get(i));
                    }
                }
                len--;      //该层顶点数自减
            }
        }
        return 0;
    }
}


//---------------------------------------------------------------


class Solution {
    int ans = 0;    //搜索到的层数 
    int strLen = 0;
    Map<String, Set<String>> patternMap;        //模式所对应的单词表
    Queue<String> q1;
    Queue<String> q2;
    Queue<String> queue;
    Set<String> v1;
    Set<String> v2;
    Set<String> visited;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return ans;
        if(!wordList.contains(beginWord))   wordList.add(beginWord);

        //初始化
        strLen = beginWord.length();
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
        v1 = new HashSet<>();
        v2 = new HashSet<>();
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

        //双端广度优先搜索
        q1.add(beginWord);
        q2.add(endWord);
        while(!q1.isEmpty() || !q2.isEmpty()) {
            if(q1.size() == 0)  queue = q2;
            else if(q2.size() == 0) queue = q1;
            else queue = (q1.size() < q2.size()) ? q1 : q2;
            visited = (queue == q1) ? v1 : v2;

            int size = queue.size();
            ans++;

            while(size != 0) {
                String word = queue.poll();
                visited.add(word);          //访问

                if(v1.contains(word) && v2.contains(word)) {    //终止搜索
                    return ans - 1;
                }

                for(int i=0; i<strLen; i++) {       //通过它的所有模式来找邻居 加入队列
                    char[] tmp = word.toCharArray();
                    tmp[i] = '*';
                    String key = String.valueOf(tmp);
                    Set<String> tmpSet = patternMap.get(key);
                    for(String next : tmpSet) {
                        if(!visited.contains(next)) {
                            queue.add(next);
                        }
                    }
                }
                size--;
            }

        }
        //返回结果
        return 0;
    }
}
