/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    int ans = 0;
    Queue<Employee> queue = new ArrayDeque<>();
    List<Employee> employees;
    Map<Integer, Employee> map = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {
        this.employees = employees;
      
        for(Employee e : employees) {       
            map.put(e.id, e);
        } 

        //bfs        
        Employee start = map.get(id);
        if(start == null)   return ans;
        else    queue.offer(start);

        while(!queue.isEmpty()) {
            Employee employee =  queue.poll();
            ans += employee.importance;

            for(int i=0; i<employee.subordinates.size(); i++) {
                Employee next = map.get(employee.subordinates.get(i));
                if(next != null) {
                    queue.offer(next);
                }
            }
        }

        return ans;
    }
}
/*
    //根据id找到员工
    Employee getEmployeeByID(int id) {
        for(Employee employee : employees) {
            if(employee.id == id) {
                return employee;
            }
        }
        return null;
    }
*/
