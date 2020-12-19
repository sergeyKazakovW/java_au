import java.util.Iterator;

class Array3DIterator<T> implements Iterator<T>{
    T[][][] array;
    int posX, posY, posZ;
    public Array3DIterator(T[][][] _array){
        array = _array;
        posX = 0;
        posY = 0;
        posZ = 0;
    }

    public boolean hasNext(){
        return !( posX == array.length-1 && posY ==  array[posX].length-1 && posZ != array[posX][posY].length-1);
    }

    public T next(){
        T res = array[posX][posY][posZ];
        if(posZ != array[posX][posY].length - 1){
            posZ++;
        }
        else if(posY != array[posX].length - 1){
            posY++;
            posZ = 0;
        }
        else{
            posX++;
            posY = 0;
            posZ = 0;
        }
        return res;
    }
}

public class Array3D<T> implements Iterable<T>  {
    T[][][] array;
    public Array3D(T[][][] _array){
        array = _array;
    }

    public Iterator<T> iterator(){
        return new Array3DIterator<T>(array);
    }
}
