package sort;

public class SortAlgorithmsCode {
    public static void main(String[] args) {
        double[] a = {442, 224,26, 316, 116, 28, 276, 462, 47, 422, 313, 138};
        radixSortDouble(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        System.out.println(2 + 0 == 0 ? true : false);


    }

    public static void test(int[] a) {
        int[] temp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i];
        }
        temp[1] = 100;
        a[1] = 100;
    }

    public static void swap(int[] array, int i, int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }


    //Quick Sort
    void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            quickSort(array, low, pivot - 1);
            quickSort(array, pivot + 1, high);
        }
    }

    int partition(int[] array, int low, int high){
        int i = low;
        for (int j = low; j < high; j++) {
            if (array[j] < array[high]) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, high);
        return i;
    }

    //Merge Sort
    void mergeSort(int[] array, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(array, l, m);
            mergeSort(array, m + 1, r);
            merge(array, l, m, r);
        }
    }

    void merge(int[] array, int l, int m, int r) {
        int i, j, k;
        int a1 = m - l + 1;
        int a2 = r - m;
        int[] L = new int[a1];
        int[] R = new int[a2];
        for(i = 0; i < a1; i++)
            L[i] = array[l + i];
        for(j = 0; j < a2; j++)
            R[j] = array[m + j + 1];
        i = 0;
        j = 0;
        k = l;
        while (i < a1 && j < a2) {
            if(L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < a1) {
            array[k++] = L[i++];
        }
        while (j < a2) {
            array[k++] = R[j++];
        }
    }

    //Heap Sort
    public static void heapify(int[] array,int n,int i){
        int largest=i;
        int l=2*i+1;
        int r=2*i+2;

        if(l<n && array[l]>array[largest])
            largest=l;
        if(r<n && array[r]>array[largest])
            largest=r;
        if(largest!=i){
            swap(array,i,largest);
            heapify(array,n,largest);
        }
    }

    public static void heapSort(int[] array){
        int i;
        for(i=array.length/2-1;i>=0;i--){
            heapify(array,array.length,i);
        }
        for(i=array.length-1;i>=0;i--){
            swap(array,0,i);
            heapify(array,i,0);
        }
    }

    public static void radixSortDouble(double[] array)
    {
        double[] b=new double[array.length];
        int m = (int) array[0], exp = 1;

        for (int i = 0; i < array.length; i++)
            if (array[i] > m)
                m = (int) array[i];

        while (m / exp > 0)
        {
            int[] bucket={0,0,0,0,0,0,0,0,0,0};
            for (int i = 0; i < array.length; i++)
                bucket[((int) array[i]) / exp % 10]++;
            for (int i = 1; i < 10; i++)
                bucket[i] += bucket[i - 1];
            for (int i = array.length - 1; i >= 0; i--)
                b[--bucket[((int) array[i]) / exp % 10]] = array[i];
            for (int i = 0; i < array.length; i++)
                array[i] = b[i];
            exp *= 10;
        }
    }

    //Radix Sort (chi dung de sap xep so nguyen duong)
    void radixSort(int[] array) {
        int[] b = new int[array.length];
        int m = array[0], exp = 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > m) {
                m = array[i];
            }
        }
        while (m / exp > 0) {
            int[] bucket={0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (int i = 0; i < array.length; i++)
                bucket[array[i] / exp % 10]++;
            for (int i = 1; i < 10; i++)
                bucket[i] += bucket[i - 1];
            for (int i = array.length - 1; i >= 0; i--)
                b[--bucket[array[i] / exp % 10]] = array[i];
            for (int i = 0; i < array.length; i++)
                array[i] = b[i];
            exp *= 10;
        }
    }

    //Bucket Sort ( chi dung sap xep so thuc co phan nguyen = 0)
    int[] bucketSort (int[] array) {
        int[] sortedSequence = new int[array.length];
        int maxValue = 0;
        for (int i = 0; i < array.length; i++)
            if (array[i] > maxValue)
                maxValue = array[i];
        int[] bucket = new int[maxValue + 1];
        for (int i = 0; i < array.length; i++)
            bucket[array[i]]++;
        int outPos = 0;
        for (int i = 0; i < bucket.length; i++)
            for (int j = 0; j < bucket[i]; j++)
                sortedSequence[outPos++] = i;
        return sortedSequence;
    }


}
