public class Radix{
  public static void radixsort(int[]data){
    MyLinkedList<Integer> bucket = new MyLinkedList<Integer>();
    for (int i = 0; i < 10; i ++){
      bucket.add(i);
    }
    System.out.println(bucket);
  }
  
  public static void main(String[] args){
    int[] data = {1,6,8,2,4,6,38,234,543};
    radixsort(data);
  }
}
