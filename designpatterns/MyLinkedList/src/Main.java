public class Main {
    public static void main(String[] args){
        MyList<Integer> list = new MyList<Integer>();
        list.addAtTail(1);
        list.addAtTail(2);
        list.addAtTail(3);
        for(Integer i : list){
            System.out.println(i);
        }
    }
}
