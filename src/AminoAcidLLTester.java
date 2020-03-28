import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AminoAcidLLTester {
  @Test
  public void test(){
  AminoAcidLL testOne = new AminoAcidLL("GGG");
  testOne.addCodon("GAG");
  testOne.addCodon("ACG");
  char[] arr = testOne.aminoAcidList();

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
