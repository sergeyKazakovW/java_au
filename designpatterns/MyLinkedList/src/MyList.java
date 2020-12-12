import java.util.Iterator;
import java.util.NoSuchElementException;

class MyListNode<T> {
    public MyListNode prev, next;
    public T val;
    public MyListNode(T parVal){
        val = parVal;
        prev = null;
        next = null;
    }
}

class MyListIterator<T> implements Iterator<T> {
    MyListNode<T> curNode;
    MyListNode<T> stopNode;
    boolean isEmpty;
    boolean alreadyMoved;
    public MyListIterator(MyListNode<T> startNode){
        if(startNode == null){
            isEmpty = true;
            return;
        }
        isEmpty = false;
        alreadyMoved = false;
        curNode = startNode;
        stopNode = startNode.next;
    }

    public T next(){
        T res = curNode.val;
        curNode = curNode.next;
        alreadyMoved = true;
        return res;
    }

    public boolean hasNext(){
        if(isEmpty)
            return false;
        if(!alreadyMoved)
            return true;
        return curNode.next != stopNode;
    }
}

public class MyList<T> implements Iterable<T>{
    MyListNode<T> head, tail;

    public MyList(){

    }

    public T get(int index) throws NoSuchElementException{
        if(head == null)
            throw new NoSuchElementException("The list is empty");

        MyListNode<T> curNode = head;
        for(int i=0; i<index; i++){
            curNode = curNode.next;
            if(curNode == head)
                throw new NoSuchElementException("Invalid index");
        }

        return curNode.val;
    }

    MyListNode<T> addAfter(MyListNode<T> node, T newVal){
        MyListNode<T> newNode = new MyListNode<T>(newVal);
        newNode.prev = node;
        newNode.next = node.next;
        newNode.next.prev = newNode;
        newNode.prev.next = newNode;
        return newNode;
    }

    void addFirstElement(T newVal){
        head = new MyListNode<T>(newVal);
        head.next = head;
        head.prev = head;
        tail = head;
    }

    public void addAtHead(T newVal){
        if(head == null)
            addFirstElement(newVal);
        else
            head = addAfter(tail, newVal);
    }

    public void addAtTail(T newVal){
        if(head == null)
            addFirstElement(newVal);
        else
            tail = addAfter(tail, newVal);
    }

    public void addAtIndex(int index, T newVal){
        if(head == null){
            addFirstElement(newVal);
            return;
        }
        if(index == 0){
            addAtHead(newVal);
            return;
        }
        MyListNode<T> curNode = head;
        for(int i=0; i<index; i++){
            curNode = curNode.next;

            if(curNode == head){
                if(i != index - 1) {
                    return;
                }
            }
        }

        if(curNode == head)
            addAtTail(newVal);
        else
            addAfter(curNode.prev, newVal);
    }

    public void deleteAtIndex(int index){
        if(head == null)
            return;

        if(head.next == head && index == 0){
            head = null;
            tail = null;
            return;
        }

        MyListNode<T> curNode = head;
        for(int i=0; i<index; i++){
            curNode = curNode.next;
            if(curNode == head)
                return;
        }

        curNode.next.prev = curNode.prev;
        curNode.prev.next = curNode.next;

        if(curNode == head){
            head = curNode.next;
        }

        if(curNode == tail){
            tail = curNode.prev;
        }
    }

    public Iterator<T> iterator(){
        return new MyListIterator<T>(tail);
    }
}
