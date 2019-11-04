class Leaderboard {
    private Map<Integer, Integer> map = new HashMap<>();
    private Comparator<Integer> scoreComparator = new Comparator<Integer>(){
        @Override
        public int compare(Integer id1, Integer id2) {
            if(map.get(id1) < map.get(id2)) return 1;
            else if(map.get(id1) > map.get(id2)) return -1;
            else return 0;
        }
    };
    private Queue<Integer> queue = new PriorityQueue<>(scoreComparator);    
    
    public Leaderboard() {
    }
    
    //增加选手的得分，若选手不存在，创建该选手初始化得分。
    public void addScore(int playerId, int score) { 
        if(!map.containsKey(playerId)) {
            map.put(playerId, score);
        }else {
            map.put(playerId, map.get(playerId)+score);
        }
        queue.remove(playerId); //得分更改后，需要重新加入该选手的ID到优先队列中
        queue.add(playerId);
    }
    //计算前K个选手的得分总和
    public int top(int K) { 
        int ret = 0;
        List<Integer> tmp = new ArrayList<>();
        for(int i=0; i<K; i++) { //得到前K个选手的ID
            int id = queue.poll();
            ret += map.get(id);
            tmp.add(id);
        }

        for(Integer i: tmp) { //恢复
            queue.add(i);
        }
        return ret;
    }
    //将选手的成绩清零
    public void reset(int playerId) { 
        queue.remove(playerId);
        map.put(playerId, 0);
        queue.add(playerId);
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */
