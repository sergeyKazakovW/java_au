# Intervals

+ [Non-overlapping Intervals](#non-overlapping-intervals)
+ [Merge Intervals](#merge-intervals)
+ [Insert Interval](#insert-interval)


## Non-overlapping Intervals

https://leetcode.com/problems/non-overlapping-intervals/

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0){
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int a[], int b[]){
                return b[0] - a[0];
            }
        });
        int got = 1;
        int min_left = intervals[0][0];
        for(int i=1; i<intervals.length; i++){
            if(intervals[i][1] <= min_left){
                got++;
                min_left = intervals[i][0];
            }
        }
        return intervals.length - got;
    }
}
```

## Merge Intervals

https://leetcode.com/problems/merge-intervals/

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        int[][] points = new int[intervals.length * 2][];
        for(int i=0; i<intervals.length; i++){
            points[i*2] =   new int[]{intervals[i][0], 0};
            points[i*2+1] = new int[]{intervals[i][1], 1};
        }
        Arrays.sort(points, new Comparator<int[]>(){
            public int compare(int a[], int b[]){
                if(a[0] == b[0]){
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });

        ArrayList<int[]> res = new ArrayList<int[]>();
        boolean opened = false;
        int left = 0;
        int depth = 0;
        for(int i=0; i<points.length; i++){
            if(points[i][1] == 0){
                depth++;
                if(!opened){
                    opened = true;
                    left = points[i][0];
                }
            }
            else{
                depth--;
                if(depth == 0){
                    opened = false;
                    res.add(new int[]{left, points[i][0]});
                }
            }
        }
        return res.toArray(new int[res.size()][2]);
    }
}
```

## Insert Interval

https://leetcode.com/problems/insert-interval/

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> points = new ArrayList<int[]>();
        boolean newIntervalLeftPlaced = false;
        boolean newIntervalRightPlaced = false;
        for(int i=0; i<intervals.length; i++){
            if(newInterval[0] <= intervals[i][0] && !newIntervalLeftPlaced){
                newIntervalLeftPlaced = true;
                points.add(new int[]{newInterval[0], 0});
            }
            if(newInterval[1] < intervals[i][0] && !newIntervalRightPlaced){
                newIntervalRightPlaced = true;
                points.add(new int[]{newInterval[1], 1});
            }
            points.add(new int[]{intervals[i][0], 0});
            if(newInterval[1] == intervals[i][0] && !newIntervalRightPlaced){
                newIntervalRightPlaced = true;
                points.add(new int[]{newInterval[1], 1});
            }

            if(newInterval[0] <= intervals[i][1] && !newIntervalLeftPlaced){
                newIntervalLeftPlaced = true;
                points.add(new int[]{newInterval[0], 0});
            }
            if(newInterval[1] < intervals[i][1] && !newIntervalRightPlaced){
                newIntervalRightPlaced = true;
                points.add(new int[]{newInterval[1], 1});
            }
            points.add(new int[]{intervals[i][1], 1});
            if(newInterval[1] == intervals[i][1] && !newIntervalRightPlaced){
                newIntervalRightPlaced = true;
                points.add(new int[]{newInterval[1], 1});
            }
        }
        if(!newIntervalLeftPlaced){
            points.add(new int[]{newInterval[0], 0});
        }
        if(!newIntervalRightPlaced){
            points.add(new int[]{newInterval[1], 1});
        }

        ArrayList<int[]> res = new ArrayList<int[]>();
        int depth = 0;
        int left = 0;
        for(int i=0; i<points.size(); i++){
            int[] point = points.get(i);
            //System.out.println(point[0]+" "+point[1]);
            if(point[1] == 0){
                depth++;
                if(depth == 1){
                    left = point[0];
                }
            }
            else{
                depth--;
                if(depth == 0){
                    res.add(new int[]{left, point[0]});
                }
            }
        }
        return res.toArray(new int[res.size()][2]);
    }
}
```
