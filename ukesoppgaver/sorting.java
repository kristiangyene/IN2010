import java.util.*;
public void bucketSort(int[] arr){
  List<Integer> array = new ArrayList<Integer>();
  Map<key, value> bucket = new HashMap<key, value>();
  for(int i: arr){
    array.add(i);

  }
  for(int i: array){
    int x = i;
    bucket[x].add(array.remove(i));
  }
  for(int i = 0; i < arr.length - 1; i++){
    for()
  }
}

//O(n)
public void sort(int[] arr){
  for(int i = 0; i < arr.length; i++){
    int j = i - 1;
    while(j >= 0 && arr[j] > arr[i]){
      arr[j+1] = arr[j];
      j = j -1;
    }
    arr[j+1] = arr[i];
    }
  }
}
