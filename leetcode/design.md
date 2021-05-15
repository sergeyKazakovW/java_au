# Design

+ [Flatten Nested List Iterator](#flatten-nested-list-iterator)
+ [LRU Cache](#lru-cache)
+ [Min Stack](#min-stack)
+ [Implement Stack using Queues](#implement-stack-using-queues)
+ [Implement Queue using Stacks](#implement-queue-using-stacks)
+ [Binary Search Tree Iterator](#binary-search-tree-iterator)


## Flatten Nested List Iterator

https://leetcode.com/problems/flatten-nested-list-iterator/

```java
public class NestedIterator implements Iterator<Integer> {

    boolean isEmptyIterator;

    boolean isSingleIntegerIterator;
    int integer;
    boolean alreadyReturned;

    ArrayList<NestedInteger> localList;
    Iterator<NestedInteger> localListPosition;
    NestedIterator curIterator;

    public NestedIterator(NestedInteger nestedInteger){
        isEmptyIterator = false;
        if(nestedInteger.isInteger()){
            isSingleIntegerIterator = true;
            alreadyReturned = false;
            integer = nestedInteger.getInteger();
        }
        else{
            isSingleIntegerIterator = false;
            localList = new ArrayList<NestedInteger>();
            for(NestedInteger subInt: nestedInteger.getList()){
                localList.add(subInt);
            }
            if(localList.size() == 0){
                isEmptyIterator = true;
                return;
            }
            localListPosition = localList.iterator();
            curIterator = new NestedIterator(localListPosition.next());
        }
    }

    public NestedIterator(List<NestedInteger> nestedList) {
        if(!nestedList.iterator().hasNext()){
            isEmptyIterator = true;
            return;
        }
        isEmptyIterator = false;;
        isSingleIntegerIterator = false;
        localList = new ArrayList<NestedInteger>();
        for(NestedInteger subInt: nestedList){
            localList.add(subInt);
        }
        localListPosition = localList.iterator();
        curIterator = new NestedIterator(localListPosition.next());
    }

    @Override
    public Integer next() {
        if(isSingleIntegerIterator){
            alreadyReturned = true;
            return integer;
        }
        if(curIterator.hasNext()){
            return curIterator.next();
        }
        curIterator = new NestedIterator(localListPosition.next());
        return next();
    }

    @Override
    public boolean hasNext() {
        if(isEmptyIterator)
            return false;
        if(isSingleIntegerIterator){
            return !alreadyReturned;
        }

        if(curIterator.hasNext())
            return true;

        if(!localListPosition.hasNext())
            return false;
        curIterator = new NestedIterator(localListPosition.next());
        return hasNext();
    }
}
```

## LRU Cache

https://leetcode.com/problems/lru-cache/

```java
class LRUCache extends LinkedHashMap<Integer, Integer>{
    int mcapacity;

    public LRUCache(int capacity) {
        super();
        mcapacity = capacity;
    }

    public int get(int key) {
        Integer res = super.get(key);
        if(res == null)
            return -1;
        remove(key);
        super.put(key, res);
        return res;
    }

    public void put(int key, int value) {
        remove(key);
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry entry){
        return size() > mcapacity;
    }
}
```

## Min Stack

https://leetcode.com/problems/min-stack/

```java
class MinStackNode{
    public int val;
    public int min;
    public MinStackNode prev;
    public MinStackNode(int _val){
        val = _val;
    }
}

class MinStack {

    MinStackNode top;

    public MinStack() {

    }

    public void push(int x) {
        if(top == null){
            top = new MinStackNode(x);
            top.min = x;
        }
        else{
            MinStackNode prev = top;
            top = new MinStackNode(x);
            top.prev = prev;
            top.min = Math.min(top.val, prev.min);
        }
    }

    public void pop() {
        top = top.prev;
    }

    public int top() {
        return top.val;
    }

    public int getMin() {
        return top.min;
    }
}
```

## Implement Stack using Queues

https://leetcode.com/problems/implement-stack-using-queues/

```java
class MyStack {

    Queue<Integer> q;

    public MyStack() {
        q = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        q.offer(x);
    }

    public int pop() {
        Queue q2 = new ArrayDeque<Integer>();
        while(q.size() > 1){
            q2.offer(q.poll());
        }
        int res = q.poll();
        q = q2;
        return res;
    }

    public int top() {
        Queue q2 = new ArrayDeque<Integer>();
        while(q.size() > 1){
            q2.offer(q.poll());
        }
        int res = q.poll();
        q2.offer(res);
        q = q2;
        return res;
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
```

## Implement Queue using Stacks

https://leetcode.com/problems/implement-queue-using-stacks/

```java
class MyQueue {
    Stack<Integer> s;

    public MyQueue() {
        s = new Stack<Integer>();
    }

    public void push(int x) {
        s.push(x);
    }

    public int pop() {
        Stack<Integer> s2 = new Stack<Integer>();
        while(s.size() > 1){
            s2.push(s.pop());
        }
        int res = s.pop();
        while(s2.size() > 0){
            s.push(s2.pop());
        }
        return res;
    }

    public int peek() {
        Stack<Integer> s2 = new Stack<Integer>();
        while(s.size() > 1){
            s2.push(s.pop());
        }
        int res = s.pop();
        s2.push(res);
        while(s2.size() > 0){
            s.push(s2.pop());
        }
        return res;
    }

    public boolean empty() {
        return s.size() == 0;
    }
}
```

## Binary Search Tree Iterator

https://leetcode.com/problems/binary-search-tree-iterator/

```java
class BSTIterator {

    BSTIterator curIterator;
    boolean isEmptyIterator;
    boolean isIteratingLeft;
    TreeNode m_root;

    public BSTIterator(TreeNode root) {
        if(root == null){
            isEmptyIterator = true;
            return;
        }
        isEmptyIterator = false;
        isIteratingLeft = true;
        curIterator = new BSTIterator(root.left);
        m_root = root;
    }

    public int next() {
        if(curIterator.hasNext())
            return curIterator.next();
        curIterator = new BSTIterator(m_root.right);
        isIteratingLeft = false;
        return m_root.val;
    }

    public boolean hasNext() {
        if(isEmptyIterator)
            return false;
        if(curIterator.hasNext())
            return true;
        return isIteratingLeft;
    }
}
```
