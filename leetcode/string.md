# String

+ [Group Anagrams](#group-anagrams)
+ [Valid Palindrome](#valid-palindrome)
+ [Longest Palindromic Substring](#longest-palindromic-substring)
+ [Palindromic Substrings](#palindromic-substrings)

## Group Anagrams

https://leetcode.com/problems/group-anagrams/

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> groups = new HashMap<String, ArrayList<String>>();
        for(int i=0; i<strs.length; i++){
            char charArray[] = strs[i].toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);
            if(!groups.containsKey(sorted)){
                ArrayList<String> newGroup = new ArrayList<String>();
                newGroup.add(strs[i]);
                groups.put(sorted, newGroup);
            } else {
                ArrayList group = groups.get(sorted);
                group.add(strs[i]);
                groups.replace(sorted, group);
            }
        }
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        for(Map.Entry<String, ArrayList<String>> entry : groups.entrySet()){
            ArrayList<String> palindroms = entry.getValue();
            res.add(palindroms);
        }

        ArrayList<List<String>> abstractRes = new ArrayList<List<String>>();
        for(ArrayList<String> list : res){
            abstractRes.add(list);
        }
        return (List<List<String>>) abstractRes;
    }
}
```

## Valid Palindrome

https://leetcode.com/problems/valid-palindrome/

```java
class Solution {
    static char toLower(char c){
        if(c >= 'A' && c <= 'Z')
            c += 'a' - 'A';
        return c;
    }

    static boolean isAlphanumeric(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    static int stepForward(String s, int r){
        while(r < s.length() && !isAlphanumeric(s.charAt(r))){
            r++;
        }
        return r;
    }

    static int stepBackward(String s, int l){
        while(l >= 0 && !isAlphanumeric(s.charAt(l))){
            l--;
        }
        return l;
    }

    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        l = stepForward(s, l);
        r = stepBackward(s, r);
        while(l <= r){
            char first = toLower(s.charAt(l));
            char second = toLower(s.charAt(r));
            if(first != second)
                return false;
            l++;
            r--;
            l = stepForward(s, l);
            r = stepBackward(s, r);
        }
        return true;
    }
}
```

## Longest Palindromic Substring

https://leetcode.com/problems/longest-palindromic-substring/

```java
class Segment{
    public int l;
    public int r;
    public Segment(int pl, int pr){
        l = pl;
        r = pr;
    }
    public boolean eq(int pl, int pr){
        return l == pl && r == pr;
    }
}

class Solution {
    public String longestPalindrome(String s) {
        Segment longestPalindrome[][] = new Segment[s.length()][s.length()+1];
        for(int i=0; i<s.length(); i++){
            longestPalindrome[i][i+1] = new Segment(i, i+1);
        }
        for(int i=0; i<s.length()-1; i++){
            if(s.charAt(i) == s.charAt(i+1))
                longestPalindrome[i][i+2] = new Segment(i, i+2);
            else
                longestPalindrome[i][i+2] = new Segment(i, i+1);
        }
        for(int length=3; length<=s.length(); length++){
            for(int l=0; l<s.length()+1-length; l++){
                if(longestPalindrome[l+1][l+length-1].eq(l+1, l+length-1) &&
                  s.charAt(l) == s.charAt(l+length-1)){
                    longestPalindrome[l][l+length] = new Segment(l, l+length);
                }
                else{
                    Segment leftLongest = longestPalindrome[l][l+length-1];
                    Segment rightLongest = longestPalindrome[l+1][l+length];
                    if(leftLongest.r - leftLongest.l > rightLongest.r - rightLongest.l)
                        longestPalindrome[l][l+length] = leftLongest;
                    else
                        longestPalindrome[l][l+length] = rightLongest;
                }
            }
        }
        Segment longest = longestPalindrome[0][s.length()];
        return s.substring(longest.l, longest.r);
    }
}
```

## Palindromic Substrings

https://leetcode.com/problems/palindromic-substrings/

```java
class Solution {
    public int countSubstrings(String s) {
        boolean isPalindrome[][] = new boolean[s.length()][s.length()];

        for(int left=0; left<s.length(); left++){
            isPalindrome[left][left] = true;
        }
        int palNum = s.length();
        for(int left=0; left<s.length()-1; left++){
            if(s.charAt(left) == s.charAt(left+1)){
                isPalindrome[left][left+1] = true;
                palNum++;
            }
            else
                isPalindrome[left][left+1] = false;
        }
        for(int length=3; length<=s.length(); length++){
            for(int left=0; left+length-1<s.length(); left++){
                if(isPalindrome[left+1][left+length-2] && s.charAt(left) == s.charAt(left+length-1)){
                    isPalindrome[left][left+length-1] = true;
                    palNum++;
                }
                else
                    isPalindrome[left][left+length-1] = false;
            }
        }
        return palNum;
    }
}
```
