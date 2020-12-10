# Linked list

+ [Reverse Linked List](#reverse-linked-list)
+ [Middle of the Linked List](#middle-of-the-linked-list)
+ [Palindrome Linked List](#palindrome-linked-list)
+ [Merge Two Sorted Lists](#merge-two-sorted-lists)
+ [Intersection of Two Linked Lists](#intersection-of-two-linked-lists)
+ [Sort List](#sort-list)
+ [Reorder List](#reorder-list)


## Reverse Linked List

https://leetcode.com/problems/reverse-linked-list/

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode curNode = head;
        ListNode prevNode = null;
        while(curNode != null){
            ListNode nextNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }
        return prevNode;
    }
}
```

## Middle of the Linked List

https://leetcode.com/problems/middle-of-the-linked-list/

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode midPtr = head;
        ListNode tailPtr = head;
        while(tailPtr.next != null && tailPtr.next.next != null){
            midPtr = midPtr.next;
            tailPtr = tailPtr.next.next;
        }
        if(tailPtr.next != null){
            midPtr = midPtr.next;
        }
        return midPtr;
    }
}
```

## Palindrome Linked List

https://leetcode.com/problems/palindrome-linked-list/

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null)
            return true;
        ListNode midPtr = head;
        ListNode tailPtr = head;
        while(tailPtr.next != null && tailPtr.next.next != null){
            midPtr = midPtr.next;
            tailPtr = tailPtr.next.next;
        }
        boolean isEven = false;
        if(tailPtr.next != null){
            isEven = true;
            midPtr = midPtr.next;
        }

        ListNode curNode = head;
        ListNode prevNode = null;
        while(curNode != midPtr){
            ListNode nextNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }
        ListNode p1 = prevNode;
        ListNode p2 = midPtr;
        if(!isEven){
            p2 = p2.next;
        }
        while(p1 != null){
            if(p1.val != p2.val)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
}
```

## Merge Two Sorted Lists

https://leetcode.com/problems/merge-two-sorted-lists/

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        ListNode tail = null;
        if(l1.val < l2.val){
            tail = l1;
            l1 = l1.next;
        }
        else{
            tail = l2;
            l2 = l2.next;
        }
        ListNode head = tail;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                tail.next = l1;
                l1 = l1.next;
            }
            else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        while(l1 != null){
            tail.next = l1;
            l1 = l1.next;
            tail = tail.next;
        }
        while(l2 != null){
            tail.next = l2;
            l2 = l2.next;
            tail = tail.next;
        }
        tail.next = null;
        return head;
    }
}
```

## Intersection of Two Linked Lists

https://leetcode.com/problems/intersection-of-two-linked-lists/

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;

        int lengthA = 0;
        int lengthB = 0;
        ListNode curNode = headA;
        while(curNode != null){
            lengthA++;
            curNode = curNode.next;
        }
        curNode = headB;
        while(curNode != null){
            lengthB++;
            curNode = curNode.next;
        }
        if(lengthA > lengthB){
            for(int i=0; i<lengthA - lengthB; i++){
                headA = headA.next;
            }
        }
        else{
            for(int i=0; i<lengthB - lengthA; i++){
                headB = headB.next;
            }
        }

        while(headA != null){
            if(headA == headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
}
```

## Sort List

https://leetcode.com/problems/sort-list/

```java
class Solution {

    ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        ListNode tail = null;
        if(l1.val < l2.val){
            tail = l1;
            l1 = l1.next;
        }
        else{
            tail = l2;
            l2 = l2.next;
        }
        ListNode head = tail;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                tail.next = l1;
                l1 = l1.next;
            }
            else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        while(l1 != null){
            tail.next = l1;
            l1 = l1.next;
            tail = tail.next;
        }
        while(l2 != null){
            tail.next = l2;
            l2 = l2.next;
            tail = tail.next;
        }
        tail.next = null;
        return head;
    }

    public ListNode sortList(ListNode head) {
        if(head == null)
            return null;

        if(head.next == null)
            return head;

        ListNode midPtr = head;
        ListNode tailPtr = head;
        ListNode midPrev = null;
        while(tailPtr.next != null && tailPtr.next.next != null){
            midPrev = midPtr;
            midPtr = midPtr.next;
            tailPtr = tailPtr.next.next;
        }
        if(tailPtr.next != null){
            midPrev = midPtr;
            midPtr = midPtr.next;
        }

        midPrev.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(midPtr);

        return mergeTwoLists(l1, l2);
    }
}
```

## Reorder List

https://leetcode.com/problems/reorder-list/

```java
class Solution {
    ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode curNode = head;
        ListNode prevNode = null;
        while(curNode != null){
            ListNode nextNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }
        return prevNode;
    }

    public void reorderList(ListNode head) {
        if(head == null)
            return;
        if(head.next == null)
            return;

        ListNode midPtr = head;
        ListNode tailPtr = head;
        ListNode midPrev;
        while(tailPtr.next != null && tailPtr.next.next != null){
            midPrev = midPtr;
            midPtr = midPtr.next;
            tailPtr = tailPtr.next.next;
        }
        midPrev = midPtr;
        midPtr = midPtr.next;

        midPrev.next = null;
        ListNode l1 = head;
        ListNode l2 = reverseList(midPtr);

        while(l2 != null){
            ListNode l1Next = l1.next;
            ListNode l2Next = l2.next;
            l1.next = l2;
            l2.next = l1Next;

            l1 = l1Next;
            l2 = l2Next;
        }
    }
}
```
