package Demo;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class ArrayDistance {

  public static void main(String[] args) {
    int[] a = {2,1,5,2,3,1,11,5};
    Set<Integer> tree = new TreeSet<>();
    for(int i: a){
      tree.add(i);
    }


    int B[]=new int[tree.size()];
    int index=0;
    for(Integer i:tree){
      B[index++]=i;
    }
    for(int i : B){
      System.out.println(i);
    }

  }
}
