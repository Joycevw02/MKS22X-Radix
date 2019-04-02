public class Radix{
  public static void radixsort(int[]data){
    //Create bucket
    MyLinkedList<Integer>[] bucket = new MyLinkedList[10];
    for (int i = 0; i < 10; i ++){
      bucket[i] = new MyLinkedList();
    }

    //Loop through data, and find the max absolute number
    //(by proxy, the most digits in the whole data set)
    int max = 0;
    for (int i = 0; i < data.length; i ++){
      if (Math.abs(data[i]) > max){
        max = Math.abs(data[i]);
      }
    }
    //Counts the digits in the max number
    int num = 0;
    while (max != 0){
      num ++;
      max /= 10;
    }
  }

  public static void main(String[] args){
    int[] data = {1,6,8,2,4,1234,38,234,543};
    radixsort(data);
  }
}
