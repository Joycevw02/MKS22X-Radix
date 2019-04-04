import java.io.*;
import java.util.*;
public class Radix{
  @SuppressWarnings({"unchecked","rawtypes"})
  public static void radixsort(int[]data){
    //Create bucket and temp to store data
    MyLinkedList<Integer>[] bucket = new MyLinkedList[20];
    MyLinkedList<Integer> temp = new MyLinkedList<Integer>();

    //Loop through the bucket and make each a linked list
    for (int i = 0; i < 20; i ++){
      bucket[i] = new MyLinkedList<Integer>();
    }

    //Loop through data and add those values to temp
    for (int i = 0; i < data.length; i ++){
      temp.add(data[i]);
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

    //Run the appropriate amount of times (aka max)
    for (int i = 0; i < max; i ++){
      //While temp have values...
      while (temp.size() > 0){
        //Val is the value being added and place is the digit
        int val = temp.removeFront();
        int place = (val / (int)Math.pow(10, i)) % 10;
        //Stuff from board
        if (place >= 0){
          bucket[10 + place].add(val);
        }
        else{
          bucket[9 + place].add(val);
        }
      }

      for (int i2 = 0; i2 < 20; i2 ++){
        //If i2 is 0, then set temp to the values with 0 in that digit
        if (i2 == 0){
          temp = bucket[i2];
        }
        //Else, extend in order
        else {
          temp.extend(bucket[i2]);
        }
      }

      //Clear the bucket
      for (int i2 = 0; i2 < 20; i2 ++){
        bucket[i2] = new MyLinkedList<>();
      }
    }

    //Set data to temp
    for (int i = 0; i < data.length; i ++){
      data[i] = temp.removeFront();
    }
  }

  public static void main(String[] args){
    int[] data = {1,6,8,2,4,1234,38,234,543};
    radixsort(data);
    System.out.println(Arrays.toString(data));
  }
}
