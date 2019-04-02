import java.util.*;
import java.io.*;
public class MyLinkedList<E>{
  private class Node{
    public E data;
    public Node next,prev;
    //Create a node of i, with no previous or next node
    public Node(E i){
      data = i;
    }
    //Return the next node
    public Node next(){
      return next;
    }
    //Return the previous node
    public Node prev(){
      return prev;
    }
    //Set the next node to another one
    public void setNext(Node other){
      next = other;
    }
    //Set the previous node to another one
    public void setPrev(Node other){
      prev = other;
    }
    //Return the data of the node
    public E getData(){
      return data;
    }
    //Set the data to something else
    public E setData(E i){
      data = i;
      return data;
    }
    //To String
    public String toString(){
      return "" + data;
    }
  }
  public int length;
  public Node start,end;
  //Create an empty list with no nodes
  public MyLinkedList(){
    length = 0;
  }
  //Return the length
  public int size(){
    return length;
  }
  public boolean add(E value){
    //Create a temporary node with the new value
    Node temp = new Node(value);
    //If the current size of the list is 0, set both
    //start and end to the temp node and increase length
    if (size() == 0){
      start = temp;
      end = temp;
      length ++;
      return true;
    }
    //Else, make the current end node point to temp as its next
    //and make temp's previous the old end, then set end to temp
    else{
      end.setNext(temp);
      temp.setPrev(end);
      end = temp;
    }
    //Add one to length and return true
    length ++;
    return true;
  }
  //Return the linked list in the format of [...]
  //Temp stores the starting node and you loop through the linked list
  //and add the data at that node, and set temp to the next node
  public String toString(){
    String ans = "[";
    Node temp = start;
    for (int i = 0; i < size(); i ++){
      if (i == size() - 1){
        ans += temp.getData();
      }
      else{
        ans = ans + temp.getData() + ",";
        temp = temp.next();
      }
    }
    ans = ans + "]";
    return ans;
  }
  //Loop through the linked list, setting temp to the next
  //value until it reaches the desired index and return temp
  private Node getNthNode(int i){
    Node temp = start;
    for (int i2 = 0; i2 < i; i2 ++){
      temp = temp.next();
    }
    return temp;
  }
  //Find the node at the given index and return its data
  public E get(int index){
    if (index > length || index < 0){
      throw new IndexOutOfBoundsException();
    }
    Node temp = getNthNode(index);
    return temp.getData();
  }
  //Find the node at the given index, set it to the desired
  //value, and return the data
  public E set(int index, E value){
    if (index > length || index < 0){
      throw new IndexOutOfBoundsException();
    }
    Node temp = getNthNode(index);
    E returnval = temp.getData();
    temp.setData(value);
    return returnval;
  }
  //Loop through the list, looking at each node
  //If any match the given value, return true
  //If the loop terminates without finding a value,
  //return false
  public Boolean contains(E value){
    for (int i = 0; i < length; i ++){
      Node temp = getNthNode(i);
      if (temp.getData() == value){
        return true;
      }
    }
    return false;
  }
  //If the index is out of bounds, throw the Exception
  public void add(int index, E value){
    if (index > length || index < 0){
      throw new IndexOutOfBoundsException();
    }
  //Temp is set to the desired value and the previous and
  //following nodes are stored in local variables.
  //Asked temp to set the previous to previous and next to next
  //And previous to set its following to temp and next to set
  //the previous to temp, affectivly replaceing the old node
  //with the new one. Increase length
    Node temp = new Node(value);
    Node previous = getNthNode(index - 1);
    Node next = getNthNode(index);
    temp.setPrev(previous);
    temp.setNext(next);
    previous.setNext(temp);
    next.setPrev(temp);
    length ++;
  }
  //If index is out of bounds, throw an exception
  public E remove(int index){
    if (index > length || index < 0){
      throw new IndexOutOfBoundsException();
    }
    //Store the node that is going to be removed to be returned
    //Ask previous and next nodes to skip over current and set
    //next and previous (respectively) to each other. Return the
    //removed value and decrease length by 1
    Node current = getNthNode(index);
    Node previous = getNthNode(index - 1);
    Node next = getNthNode(index + 1);
    previous.setNext(next);
    next.setPrev(previous);
    length --;
    return current.getData();
  }
  //Loop through the list until you find a current with
  //the value. If found, return the index. If not, set current
  //to the next node and increase i. If no nodes are found, return -1
  public int indexOf(E value){
    Node current = start;
    int i = 0;
    while (current != null){
      if (current.getData() == value){
        return i;
      }
      i ++;
      current = current.next();
    }
    return -1;
  }
  //If the list contains the given value, set indext to the index of
  //the value and remove using remove by index and return true.
  public Boolean remove(E value){
    if(contains(value)){
      int index = indexOf(value);
      remove(index);
      return true;
    }
    return false;
  }
  //If the length of this is currently 0, set this to other and clear
  //other. Else, set this's end's next to other's start and other's start's
  //previous to this's end. Increase this's length and set other's length to 0
  @SuppressWarnings("unchecked") 
  public void extend(MyLinkedList other){
    if (length == 0){
      Node temp = other.start;
      for (int i = 0; i < other.length; i++){
        add(temp.getData());
        temp = temp.next();
      }
      other.length = 0;
    }
    else{
      end.setNext(other.start);
      other.start.setPrev(end);
      length += other.length;
      other.length = 0;
    }
  }

  public void clear(){
    length = 0;
    start = null;
    end = null;
  }
}
