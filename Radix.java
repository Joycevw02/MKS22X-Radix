public class Radix{
  @SuppressWarnings({"unchecked","rawtypes"})
  public static void radixsort(int[]data){
    //Create bucket
    MyLinkedList<Integer>[] bucket = new MyLinkedList[20];
    for (int i = 0; i < 20; i ++){
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

    //Run as many times as there are digits
    for (int i = 0; i <= num; i ++){
      //Stuff from the board
      for (int i2 = 0; i2 < data.length; i2 ++){
        int number = data[i2];
        //Returns the value that we are looking at
        int val = (int)(number / Math.pow(10, i)) % 10;
        if (number < 0){
          bucket[9 - Math.abs(val)].add(number);
        }
        else{
          bucket[10 + Math.abs(val)].add(number);
        }
      }
      //Join the two sides
      join(bucket);

      //While, there is still things in bucket, set data[i2] to the first value
      //int bucket (or what remains of it)
      for (int i2 = 0; bucket[i].size() != 0; i2 ++){
        data[i2] = bucket[i].remove(0);
      }
    }
  }

  private static void join(MyLinkedList<Integer>[] temp){
    //Loop through the bucket and link up the two parts
    for (int i = temp.length - 2; i >= 0; i --){
      temp[i].extend(temp[i + 1]);
    }
  }

  public static void main(String[] args){
    int[] data = {1,6,8,2,4,1234,38,234,543};
    radixsort(data);
    System.out.println(data);
  }
}
