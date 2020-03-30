import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AminoAcidLLTester {
  @Test
  public void testList(){
    // G E T
  AminoAcidLL testOne = AminoAcidLL.createFromRNASequence("GGGGAGACG");
  char[] arr = testOne.aminoAcidList();

    System.out.println("amino acid list method");
    for(int i = 0; i < arr.length; i++){
      System.out.print(arr[i] + " ");
    }
  }

  @Test
  public void testList2(){
    // G 3 E 1 T 3
    AminoAcidLL testOne = AminoAcidLL.createFromRNASequence("GGGGGAGGCGAGACGACUUAC");
    int[] arr = testOne.aminoAcidCounts();

    System.out.println("amino acid list method");
    for(int i = 0; i < arr.length; i++){
      System.out.print(arr[i] + " ");
    }
  }

  @Test
  public void testRNASequence(){
    //"ACGAUGCUGUUG"
    //"GCUACGGAGCUUGCA"
    AminoAcidLL testOne = AminoAcidLL.createFromRNASequence("GCUACGGAGCUUGCA");

    int count = 1;
    System.out.print(count + ": ");
    testOne.printNode();
    AminoAcidLL iterator = testOne.getNext();
    while (iterator != null) {
      count++;
      System.out.print(count + ": ");
      iterator.printNode();
      iterator = iterator.getNext();
    }
      System.out.println();
    }

  @Test
  public void testIsSorted(){
    AminoAcidLL testOne = AminoAcidLL.createFromRNASequence("GCUGAGCAC");
    System.out.println(testOne.isSorted());

    int count = 1;
    System.out.print(count + ": ");
    testOne.printNode();
    AminoAcidLL iterator = testOne.getNext();
    while (iterator != null) {
      count++;
      System.out.print(count + ": ");
      iterator.printNode();
      iterator = iterator.getNext();
    }
    System.out.println();
  }

  @Test
  public void testSort(){
    //"ACGCCCAUGCUGCCG"
    // T P M L
//"ACGAUGCUGCCGAGC"
    AminoAcidLL testOne = AminoAcidLL.createFromRNASequence("ACGAUGCUGCCGCGAUCCGUUGCG");
    System.out.println("Before sorting: " + testOne.isSorted());

    int count = 1;
    System.out.print(count + ": ");
    testOne.printNode();
    AminoAcidLL iterator = testOne.getNext();
    while (iterator != null) {
      count++;
      System.out.print(count + ": ");
      iterator.printNode();
      iterator = iterator.getNext();
    }
    System.out.println();



    AminoAcidLL testTwo = AminoAcidLL.sort(testOne);
    System.out.println("After sorting: " + testTwo.isSorted());
    System.out.println();

    count = 1;
    System.out.print(count + ": ");
    testTwo.printNode();
    iterator = testTwo.getNext();
    while (iterator != null) {
      count++;
      System.out.print(count + ": ");
      iterator.printNode();
      iterator = iterator.getNext();
    }
    System.out.println();
  }
}
