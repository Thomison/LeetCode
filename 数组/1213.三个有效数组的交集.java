/*
由于数组严格递增，同一数组中不存在相同的整数，加上数组中每个整数是有范围限制的，
我们只需要创建一个范围大小的数组，对三个数组分别进行遍历，统计每个整数的出现次数，
若出现次数为3，则进行输出。
*/

Class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int[] helper = new int[2001]; //范围：1-2000
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<arr1.length; i++) helper[arr1[i]] ++;
        for(int i=0; i<arr2.length; i++) helper[arr2[i]] ++;
        for(int i=0; i<arr3.length; i++) helper[arr3[i]] ++;
        for(int i=1; i<helper.length; i++) {
            if(helper[i] == 3) ans.add(i);
        }
        return ans;
    }
}
