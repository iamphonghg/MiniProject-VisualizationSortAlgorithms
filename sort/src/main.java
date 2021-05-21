import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class main {
    public static void main(String[] argv){
        int[] array={21,512,7,83,1123,345,6,92,0,4234};
        Sort.radixSort(array);
        for (int i=0;i< array.length;i++){
            System.out.print(array[i]+",");
        }
        System.out.println();
    }
}

