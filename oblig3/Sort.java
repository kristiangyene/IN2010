import java.lang.*;
import java.util.*;

public class Sort{
  public int[] selection_sort;
  int[] insertion_sort;
  int[] quick_sort;
  int[] bucket_sort;

  public Sort(){
  }

  //worst-case: O(n^2)
  //O(n^2)
  public void selectionAlgorithm(int[] array){
    for(int i = 0; i < array.length - 1; i++){
      //Finner den minste verdien.
      /*for(int f: array){
        System.out.print(f + " ");
      }
      System.out.println();*/
      int small = i;
      for(int j = i + 1; j < array.length; j++){
        if(array[j] < array[small]){
          small = j;
        }
      }
      //Bytter rekkefølge på verdier.
      int temp = array[small];
      array[small] = array[i];
      array[i] = temp;
    }
    this.selection_sort = array;
  }

  //worst-case: O(n^2)
  //O(n)
  public void insertionAlgorithm(int[] array){
    for(int i = 0; i < array.length; i++){
      int numb = array[i];
      int j = i - 1;
      while(j >= 0 && array[j] > numb){
        array[j+1] = array[j];
        j = j - 1;
      }
      array[j+1] = numb;
      /*for(int f: array){
        System.out.print(f + " ");
      }
      System.out.println();*/
    }
    this.insertion_sort = array;
  }
  //worst-case: O(n2)
  //randomized: O(n log n)
  public void quickAlgorithm(int[] array, int low, int high){
    int index;
    if(low < high){
      index = partition(array, low, high);
      quickAlgorithm(array, low, index - 1);
      quickAlgorithm(array, index + 1, high);
  }
}
  public int partition(int[] array, int low, int high){
    int pivot = array[low];
    int numb = low + 1;
    for(int i = numb; i <= high; i++){
      if(array[i] < pivot){
        if(i != numb){
        int temp = array[numb];
        array[numb] = array[i];
        array[i] = temp;
        }
        numb++;
      }
    }
    //Om ikke i <= high:
    array[low] = array[numb - 1];
    array[numb - 1] = pivot;
    /*for(int f: array){
      System.out.print(f + " ");
    }
    System.out.println();*/
    return low;
  }

  //worst-case: O(n^2)
  //O(n + N)
  public void bucketAlgorithm(int[] array){
    int[] bucket = new int[array.length];
    /*for(int f: array){
      System.out.print(f + " ");
    }
    System.out.println();*/
    for(int i: array){
      bucket[i] = 0;
    }
    int count = 0;
    for(int i: array){
      bucket[array[count++]]++;
    }
    int index = 0;
    for(int i = 0; i < bucket.length; i++){
      for(int j = 0; j < bucket[i]; j++){
        array[index++] = i;
      }
      /*for(int f: array){
        System.out.print(f + " ");
      }
      System.out.println();*/
    }
     this.bucket_sort = array;
  }


  public void testTime(Sort sort, int[] list){
    int[] resetList = list.clone();
    long t = System.nanoTime();
    sort.insertionAlgorithm(list);
    double tid = (System.nanoTime()-t)/1000000.0;
    System.out.println("Insertion: " + tid);

    System.out.println();
    list = resetList.clone();
    t = System.nanoTime();
    sort.selectionAlgorithm(list);
    tid = (System.nanoTime()-t)/1000000.0;
    System.out.println("Selection: " + tid);

    System.out.println();
    list = resetList.clone();
    t = System.nanoTime();
    sort.bucketAlgorithm(list);
    tid = (System.nanoTime()-t)/1000000.0;
    System.out.println("Bucket:    " + tid);

    System.out.println();
    //list = resetList.clone();
    t = System.nanoTime();
    sort.quickAlgorithm(list, 0, list.length-1);
    tid = (System.nanoTime()-t)/1000000.0;
    System.out.println("Quick:     " + tid);

    System.out.println();
    list = resetList.clone();
    t = System.nanoTime();
    Arrays.sort(list);
    tid = (System.nanoTime()-t)/1000000.0;
    System.out.println("Array.sort: " + tid);
    System.out.println();

  }

  public void testPrint(Sort sort, int[] list){
    int[] resetList = list.clone();
    sort.insertionAlgorithm(list);
    for(int i = 0; i < list.length; i++){
      System.out.print(list[i] + " ");
    }
    System.out.println();
    list = resetList.clone();
    sort.selectionAlgorithm(list);
    for(int i = 0; i < list.length; i++){
      System.out.print(list[i] + " ");
    }
    System.out.println();
    list = resetList.clone();
    sort.bucketAlgorithm(list);
    for(int i = 0; i < list.length; i++){
      System.out.print(list[i] + " ");
    }
    System.out.println();
    list = resetList.clone();
    sort.quickAlgorithm(list, 0, list.length-1);
    for(int i = 0; i < list.length; i++){
      System.out.print(list[i] + " ");
    }
    System.out.println();
    list = resetList.clone();
    Arrays.sort(list);
    for(int i = 0; i < list.length; i++){
      System.out.print(list[i] + " ");
    }
  }
  public void testPattern(Sort sort, int[] list){
    sort.bucketAlgorithm(list);
  }
}
