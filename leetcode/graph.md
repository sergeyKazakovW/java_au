# Graphs

+ [Number of Islands](#number-of-islands)
+ [Course Schedule II](#course-schedule-ii)
+ [Course Schedule](#course-schedule)


## Number of Islands

https://leetcode.com/problems/number-of-islands/

```java
class Solution {
    boolean[][] was;
    char[][] m;

    void dfs(int x, int y){
        if(was[x][y] || (m[x][y] == '0'))
            return;
        was[x][y] = true;

        if(x > 0)
            dfs(x-1, y);
        if(x < m.length-1)
            dfs(x+1, y);
        if(y > 0)
            dfs(x, y-1);
        if(y < m[0].length-1)
            dfs(x, y+1);
    }

    public int numIslands(char[][] grid) {

        m = grid;
        was = new boolean[grid.length][grid[0].length];

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                was[i][j] = false;
            }
        }

        int parts = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(!was[i][j] && m[i][j] == '1'){
                    parts++;
                    dfs(i, j);
                }
            }
        }
        return parts;
    }
}
```

## Course Schedule II

https://leetcode.com/problems/course-schedule-ii/

```java
class Solution {
    ArrayList<Integer>[] m;
    boolean was[];
    boolean grey[];

    int[] shedule;
    int sp;

    boolean dfs(int v){
        if(grey[v])
            return false;
        if(was[v])
            return true;

        was[v] = true;
        grey[v] = true;

        for(int i=0; i<m[v].size(); i++){
            if(!dfs(m[v].get(i)))
                return false;
        }
        shedule[sp] = v;
        sp++;
        grey[v] = false;
        return true;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        was = new boolean[numCourses];
        grey = new boolean[numCourses];
        m = (ArrayList<Integer>[]) new ArrayList[numCourses];
        for(int i=0; i<m.length; i++){
            m[i] = new ArrayList<Integer>();
        }

        shedule = new int[numCourses];
        sp = 0;

        for(int i=0; i<prerequisites.length; i++){
            m[prerequisites[i][0]].add(prerequisites[i][1]);
        }

        for(int i=0; i<numCourses; i++){
            if(!was[i]){
                if(!dfs(i))
                    return new int[0];
            }
        }
        return shedule;
    }
}
```

## Course Schedule

https://leetcode.com/problems/course-schedule/

```java
class Solution {

    ArrayList<Integer>[] m;
    boolean was[];
    boolean grey[];

    boolean dfs(int v){
        if(grey[v])
            return false;
        if(was[v])
            return true;

        was[v] = true;
        grey[v] = true;

        for(int i=0; i<m[v].size(); i++){
            if(!dfs(m[v].get(i)))
                return false;
        }
        grey[v] = false;
        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        was = new boolean[numCourses];
        grey = new boolean[numCourses];
        m = (ArrayList<Integer>[]) new ArrayList[numCourses];
        for(int i=0; i<m.length; i++){
            m[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<prerequisites.length; i++){
            m[prerequisites[i][0]].add(prerequisites[i][1]);
        }

        for(int i=0; i<numCourses; i++){
            if(!was[i]){
                if(!dfs(i))
                    return false;            }
        }
        return true;
    }
}
```
