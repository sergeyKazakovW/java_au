# Arrays

+ [Squares of a Sorted Array](#squares-of-a-sorted-array)


## Squares of a Sorted Array

https://leetcode.com/problems/squares-of-a-sorted-array/

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int lb = -1;
        int rb = nums.length;
        for(int i=0; i<nums.length; i++){
            if(nums[i] <= 0)
                lb = i;
            if(nums[i] > 0 && rb > i)
                rb = i;
            nums[i] = nums[i] * nums[i];
        }
        int[] res = new int[nums.length];
        int cp = 0;
        while(lb >= 0 && rb < nums.length){
            if(nums[lb] < nums[rb]){
                res[cp] = nums[lb];
                cp++;
                lb--;
            }
            else{
                res[cp] = nums[rb];
                cp++;
                rb++;
            }
        }
        while(lb >= 0){
            res[cp] = nums[lb];
            cp++;
            lb--;
        }
        while(rb < nums.length){
            res[cp] = nums[rb];
            cp++;
            rb++;
        }
        return res;
    }
}
```
