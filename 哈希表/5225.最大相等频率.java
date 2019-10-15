//一个哈希表，O(n^3)

class Solution {
    public int maxEqualFreq(int[] nums) {
        int ans = 1;
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        
        for(int i=0; i<nums.length; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            }else {
                map.put(nums[i], map.get(nums[i])+1);
            }
            //try to remove one number to find an array of equal times
            for(Integer key : map.keySet()) {
                if(map.get(key) == 1 && map.size() > 1) {
                    map.remove(key);
                    ArrayList<Integer> tmp = new ArrayList<>(map.values());
                    if(judgeEqual(tmp))
                        ans = Math.max(ans, i+1);
                    map.put(key, 1);
                }
                else if(map.get(key) > 1) {
                    int value = map.get(key);
                    map.put(key, value-1);
                    ArrayList<Integer> tmp = new ArrayList<>(map.values());
                    if(judgeEqual(tmp))
                        ans = Math.max(ans, i+1);
                    map.put(key, value);
                }
            }
        }
        
        return ans;
    }
    //判断列表中的值是否全部相等
    public boolean judgeEqual(List<Integer> list) {
        int tmp = list.get(0);
        for(int i=0; i<list.size(); i++) {
            if(list.get(i) != tmp) {
                return false;
            }
        }
        return true;
    }
}


/*
两个哈希表，O(n)

map1维护对应数字的出现次数。

map2维护对应数字出现次数的次数。(也表示了数字的种数)

关键在于两点：

1.如何维护map2?

  map1不包含当前元素的键：
    map2不包含次数为1的键： map2.put(1, 1)
    map2包含次数为1的键： map2.put(1, map2.get(1)+1)
  map1包含当前元素的键， ：int num_dict = map1.get(nums[i])
    消除map2键值对 或 对map2键值对中值-1：
      当前元素对应出现次数的次数为1：map2.remove(num_dict)
      否则：map2.put(num_dict, map2.get(num_dict)-1)
    增加map2键值对 或 map2 键值对+1：
      当前元素对应出现次数的次数+1不存在map2的键中 : map2.put(num_dict+1, 1)
      否则：map2.put(num_dict, map2.get(num_dict)-1);
  
2.如何判断当前前缀是否为有效前缀？

  map2.size() == 1：
    对应唯一的键值对的键等于1，表示有很多为出现次数为1的元素，删除其中一种即可。
    对应唯一的减值对的值等于1，表示出现次数为n但是只有一种元素，删除这种元素的一个即可。
  map2.size() == 2：
    对应的两个键值对中，出现次数更大的键=出现次数更小的键+1 && 出现次数更大的次数=1，删除那个出现次数更大对应的数字即可。
    出现次数更小的键值对中，键和值都等于1，删除这个出现次数更小对应的数字即可。

*/

class Solution {
    public int maxEqualFreq(int[] nums) {
        int ans = 1;
        Map<Integer, Integer> map1 = new HashMap<>();   //对应数字的出现次数
        Map<Integer, Integer> map2 = new HashMap<>();   //对应出现次数的数字种数
        
        for(int i=0; i<nums.length; i++) {
            
            //维护两个dict
            if(!map1.containsKey(nums[i])) {
                map1.put(nums[i], 1);
                if(!map2.containsKey(1))    
                    map2.put(1, 1);
                else    
                    map2.put(1, map2.get(1)+1);
            }else {
                int num_dict = map1.get(nums[i]), count_dict = map2.get(num_dict);
                //消除键值对 或 对键值对执行值-1操作
                if(count_dict == 1) {
                    map2.remove(num_dict);
                }else {
                    map2.put(
                        num_dict, map2.get(num_dict)-1);
                }
                //增加键值对 或 对键值对执行值+1操作
                if(!map2.containsKey(num_dict+1)) {
                    map2.put(num_dict+1, 1);
                }else {
                    map2.put(
                        num_dict+1, map2.get(num_dict+1)+1);
                }
                map1.put(nums[i], map1.get(nums[i])+1);
            }
            
            //判断是否为有效前缀
            if(map2.size() == 1) {
                for(Integer key : map2.keySet()) {
                    if(map2.get(key) == 1 || key == 1) {
                        ans = Math.max(ans, i+1);
                    }
                }
            }else if(map2.size() == 2) {
                int[] k = new int[2], v = new int[2];
                int j=0;
                for(Integer key : map2.keySet()) {
                    k[j] = key;
                    v[j] = map2.get(key);
                    j++;
                }
                int max_index = (k[0] > k[1]) ? 0 : 1;  //大频次的下标
                int min_index = (k[0] > k[1]) ? 1 : 0;  //小频次的下标
                if((k[max_index]==k[min_index]+1 && v[max_index]==1) || (k[min_index]==1 && v[min_index]==1)) {
                    ans = Math.max(ans, i+1);
                }
            }
        }
        return ans;
    }
}
