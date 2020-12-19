public class Main {
    public static void main(String[] args){
        Integer[][][] arr = new Integer[4][3][3];

        int c = 0;
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    arr[i][j][k] = c;
                    c++;
                }
            }
        }

        for(int i: new Array3D<Integer>(arr)){
            System.out.print(i + " ");
        }
    }
}
