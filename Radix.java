public class Radix{
  @SuppressWarnings({"unchecked","rawtypes"})
  public static void radixsort(int[]data){
    //Create bucket
    MyLinkedList<Integer>[] bucket = new MyLinkedList[10];
    for (int i = 0; i < 10; i ++){
      bucket[i] = new MyLinkedList<Integer>();
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

    for (int i = 0; i <= num; i ++){
      for (int i2 = 0; i2 < data.length; i2 ++){
        int number = data[i2];
        int val = (int)(number / Math.pow(10, i)) % 10;
        if (number < 0){
          bucket[9 - Math.abs(val)].add(number);
        }
        else{
          bucket[10 + Math.abs(val)].add(number);
        }
      }

    }
  }

  private static void join(MyLinkedList<Integer>[] other){
    
  }

  public static void main(String[] args){
    int[] data = {1,6,8,2,4,1234,38,234,543};
    radixsort(data);
  }
}
