import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class AminoAcidLLTester {
  // This test case is for the method createFromRNASequence.
  // This test case is important because it makes sure that this method is able to correctly create a linked list.
  // Since the rest of the method depend on this one, it is important to make sure that it works.
  // This test passed a string of "AAGUACCCGAAGAAA". This specific string was chosen because it has a repeating amino acid, K, as well as a repeating codon, AAG.
  // This was done in order to make sure that the codons are added properly despite repetitions, so multiple checks are being done within one test.
  // This test passed at first, so the method is able to create a linked list successfully.
  @Test
  public void testOne(){
    AminoAcidLL testOne = AminoAcidLL.createFromRNASequence("AAGUACCCGAAGAAA");

    assertEquals('K',testOne.getAminoAcid());
    assertEquals('T',testOne.getNext().getAminoAcid());
    assertEquals('P',testOne.getNext().getNext().getAminoAcid());

  }

  // This test case is for the method createFromRNASequence.
  // This test case checks that the method only creates a linked list when the string parameter is in groups of 3.
  // The string "AAGCAAUGGCC" was sent as an argument in this test case, and it was chosen because the final two characters are missing a letter.
  // This test case is important because in the even that there is a typo in the string, it makes sure that no linked list is created.
  // This ensures that other methods depending on this method do not have to handle additional errors that could be created.
  // This test case was expected to return null, and it was successful in doing so.
  @Test
  public void testTwo(){
    AminoAcidLL testTwo = AminoAcidLL.createFromRNASequence("AAGCAAUGGCC");
    assertNull(testTwo);
  }

  // This test case is for the method isSorted.
  // This test case makes sure that the method can correctly identify an unsorted linked list.
  // The string "CUAAUGGUGUCU", which is not sorted, was used to create a linked list, which was then used as the argument sent to the method.
  // This test case was done before the test case that sends a sorted linked list because receiving a value of false is more useful than a value of true.
  // This is because the value of true is the default return value in the method.
  // By including an unsorted array, this test case makes sure that the single conditional that checks if it is sorted in the method is being correctly executed.
  // This test case passed, so the method can correctly identify when a linked list is unsorted.
  @Test
  public void testThree(){
    AminoAcidLL testThree = AminoAcidLL.createFromRNASequence("CUAAUGGUGUCU");
    assertFalse(testThree.isSorted());
  }

  // This test case is for the method isSorted.
  // This test case makes sure that the method can correctly identify a sorted linked list.
  // The string "GCUGAGCAC", which is sorted, was used to create a linked list, which was then used as the argument sent to the method.
  // This test case is important because it checks whether the method can iterate through the whole list and make sure that it is sorted.
  // This test case passed, so the method can correctly make sure that the linked list is sorted.
  @Test
  public void testFour(){
    AminoAcidLL testFour = AminoAcidLL.createFromRNASequence("GCUGAGCAC");
    assertTrue(testFour.isSorted());
  }

  // This test case is for the method sort.
  // This test case checks that the method can sort an array that was previously unsorted.
  // The string "ACGAUGCUGCCGCGAUCCGUUGCG", which is unsorted, was used to create a linked list, which was then used as the argument sent to the method.
  // This test case is important because it ensures that the method can correctly sort a linked list by moving around the nodes and not the data itself.
  // It was important to use an unsorted array to create the list because this ensures that nodes must be moved around when sorting it.
  // There is a test assertion before the intended test in order to ensure that the beginning linked list is not sorted.
  // This test case did not pass on the first try. There was a lot of work to be done in this method, which first began by copying the nodes to a separate linked list.
  // Although this worked, it did not seem to be what the method's intended purpose was meant to be, so I changed it to modify the original linked list.
  // When sorting, I had to make sure to keep track of the previous node in order to be able to swap the nodes, which was not successful at first since
  // the node would remain the same even after swapping. I fixed this by creating an additional iterator at the end of the while loop to find the previous node.
  // After this addition, the test was successful and the method could sort unsorted linked lists.
  @Test
  public void testFive(){
    AminoAcidLL testFiveA = AminoAcidLL.createFromRNASequence("ACGAUGCUGCCGCGAUCCGUUGCG");
    assertFalse(testFiveA.isSorted());

    AminoAcidLL testFiveB = AminoAcidLL.sort(testFiveA);
    assertTrue(testFiveB.isSorted());
  }

  // This test case is for the method sort.
  // This test case checks that the method can sort an array that is already sorted.
  // This test case is important because nodes should not be moved in a linked list that is already sorted, so this test ensures that.
  // In this test, the string "UCGUAC", which is sorted, was used to create a linked list, which was then used as the argument sent to the method.
  // This test case passed without any additional modification to the method, so the method can correctly sort linked lists that were already sorted.
  @Test
  public void testSix(){
    AminoAcidLL testSixA = AminoAcidLL.createFromRNASequence("UCGUAC");
    assertTrue(testSixA.isSorted());

    AminoAcidLL testSixB = AminoAcidLL.sort(testSixA);
    assertTrue(testSixB.isSorted());
  }

  // This test case is for the method aminoAcidList.
  // This test case checks that the method can return a character array containing the character of the amino acid.
  // This test uses the string "UCG" in order to create the linked list.
  // This string refers to only one amino acid, S, so the expected array returned is {'S'}.
  // This test case is important because it tests the base case of the recursive method.
  // By making sure the base case works, the recursive steps can then successfully rely on it.
  // This test case was successful on the first try, which is expected since the base case is the first line executed.
  // However, this does not mean that the method works on linked lists with more than one node.
  @Test
  public void testSeven(){
    AminoAcidLL testSeven = AminoAcidLL.createFromRNASequence("UCG");
    char[] expected = new char[]{'S'};

    assertArrayEquals(expected,testSeven.aminoAcidList());
  }

  // This test case is for the method aminoAcidList.
  // This test case checks that the method can return a character array containing each amino acid character of a linked list.
  // This test uses the string "GGGGAGACG" in order to create the linked list.
  // The expected array to be returned from this linked list is {'G', 'E', 'T'}.
  // This test case is important because it tests whether the recursive steps of the method behave as expected.
  // This test case was not successful at first.
  // The method was unsuccessful at first because the array of characters was not being built upon through each recursive call.
  // This was fixed by initializing and declaring an array equal to the result of the recursive calls.
  // However, the test still did not pass since it was not taken into consideration that this method is recursive and the recursive calls begin at the next node, so the first character is not included.
  // This was fixed by including a for-loop to prepend the first character into the array.
  // After these additions, the method worked, so it is successful in returning a character array of the amino acid characters.
  @Test
  public void testEight(){
    AminoAcidLL testEight = AminoAcidLL.createFromRNASequence("GGGGAGACG");
    char[] expected = new char[]{'G', 'E', 'T'};

    assertArrayEquals(expected,testEight.aminoAcidList());
  }

  // This test case is for the method aminoAcidCounts.
  // This test case checks that the method can return an integer array of the sum of each amino acid instance.
  // This test uses the string "AGGCGCCGUCGGAGGCGACGCCGG" in order to create the linked list.
  // This test included many codons; however, they only refer to one amino acid.
  // This was done to make sure that the createRNASequence in addition to the addCodon method were working properly.
  // Since there are 8 codons, the expected array is {'8'}.
  // This test case is important because it tests the base case of the recursive method.
  // This test passed on the first try, so the method can correctly return an integer array when the linked list only has one node.
  @Test
  public void testNine(){
    AminoAcidLL testNine = AminoAcidLL.createFromRNASequence("AGGCGCCGUCGGAGGCGACGCCGG");
    int[] expected = new int[]{8};

    assertArrayEquals(expected,testNine.aminoAcidCounts());
  }

  // This test case is for the method aminoAcidCounts.
  // This test case checks that the method can return an integer array of the sum of each amino acid instance in a linked list.
  // This test case uses the string "GGGGAGGGAACAACUGGU" in order to create the linked list.
  // This string includes 3 different amino acid, G, E, and T, which appear in that same order.
  // The codons for each amino acid are not placed right next to each other in order to test the addCodon method additionally.
  // The expected array based on the number of times each amino acid appears in this string is {3, 1, 2}.
  // This test is important because it checks the recursive step of the method and makes sure that there are steps progressing it to the base case.
  // This test passed on the first try due to previous work in the aminoAcidList method.
  // Since these methods are so similar, work done on the aminoAcidList method was copied and tweaked in the aminoAcidCounts method.
  @Test
  public void testTen(){
    AminoAcidLL testNine = AminoAcidLL.createFromRNASequence("GGGGAGGGAACAACUGGU");
    int[] expected = new int[]{3, 1, 2};

    assertArrayEquals(expected,testNine.aminoAcidCounts());
  }

  // This test case is for the method aminoAcidCompare.
  // This test checks that the method can return the difference in amino acids between two linked lists.
  // This test case uses the sting of "GCAGCAGCU" as the string to create the first instance of the linked list.
  // This test case also uses the string of "GCGGCC" to create a linked list as the argument to send to the method.
  // These strings were used because they are referring to a single amino acid, A.
  // This way, this will test the base case of the recursive method.
  // The first string is longer and denotes the amino acid 3 times while the second string denotes the amino acid twice.
  // This was done in order to make sure that the values are being subtracted since a returned value of 0 would otherwise not be very telling.
  // This test case also tests the case when both of the linked lists have the same amount of nodes.
  // In other words, the next value of both of these are null, which is another base case.
  // This test did not pass several times.
  // In order to fix this, I opted to place a recursive call after each base case.
  // I chose to do it this way because whether or not a case is a base case depends on the current and next values.
  // Since they vary, the recursive call for each case will vary as well.
  // This was also done in order to avoid returning an empty list for the parameter value.
  // After several tries, this test was successful and returned the expected value.
  @Test
  public void testEleven(){
    AminoAcidLL firstLinkedList = AminoAcidLL.sort(AminoAcidLL.createFromRNASequence("GCAGCAGCU"));
    AminoAcidLL secondLinkedList = AminoAcidLL.sort(AminoAcidLL.createFromRNASequence("GCGGCC"));

    assertEquals(1, firstLinkedList.aminoAcidCompare(secondLinkedList));
  }

  // This test case is for the method aminoAcidCompare.
  // This test case checks that the method can return the difference in amino acid counts between two linked lists.
  // This test case uses the string "AAGCCGAGGAGCACG" in order to create the linked list for the instance of the class.
  // This test case also uses "CCUAGA" in order to create the linked list to send as an argument to the method.
  // The first string denotes the amino acids of KPRST, and the second string denotes the amino acids of PR.
  // These strings were chosen because they test the method when the first values do not equal to each other.
  // In addition, it also tests if the method can handle when the string sent as an argument ends before the string that created the instance.
  // This test case is important because the method should be able to sum up the amino acid counts in the string that is not at the end.
  // This test case failed due to an error in the case where the two amino acid values are equal to each other since it was only iterating the instance linked list and not the parameter linked list.
  // After fixing this error, the test was able to pass.
  // This means that the method is able to compute the difference in number of amino acids when the parameter linked list is shorter.
  @Test
  public void testTwelve(){
    AminoAcidLL firstLinkedList = AminoAcidLL.sort(AminoAcidLL.createFromRNASequence("AAGCCGAGGAGCACG"));
    AminoAcidLL secondLinkedList = AminoAcidLL.sort(AminoAcidLL.createFromRNASequence("CCUAGA"));

    assertEquals(3, firstLinkedList.aminoAcidCompare(secondLinkedList));
  }

  // This test case is for the method aminoAcidCompare.
  // This test case checks that the method can return the difference in amino acid counts between two linked lists.
  // This test case uses the string "CCUAGA" in order to create the linked list for the instance of the class.
  // This test case also uses "AAGCCGAGGAGCACG" in order to create the linked list to send as an argument to the method.
  // As seen, the strings used are the same as the previous test except that they are flipped.
  // This is to test that the method can handle cases in which the linked list of the instance is shorter than the linked list sent as a parameter.
  // This test passed on the first try, so the method is able to compute the difference in number of amino acids when the parameter linked list is longer.
  @Test
  public void testThirteen(){
    AminoAcidLL firstLinkedList = AminoAcidLL.sort(AminoAcidLL.createFromRNASequence("CCUAGA"));
    AminoAcidLL secondLinkedList = AminoAcidLL.sort(AminoAcidLL.createFromRNASequence("AAGCCGAGGAGCACG"));

    assertEquals(3, firstLinkedList.aminoAcidCompare(secondLinkedList));
  }

  // This test case is for the method codonCompare.
  // This test case tests if the method can return the value of the differences in codons between two linked lists.
  // This test uses the string of "GGAGGA" in order to create the linked list for the instance of the class.
  // This test case also uses "GGAGGC" in order to create the linked list to send as an argument to the method.
  // These strings were chosen because they refer to the same amino acid.
  // If the method aminoAcidCompare were done with these two strings, it would yield a result of 0.
  // However, if codonCompare were done with these two strings, it would yield a result of 2.
  // This shows the importance of this test since the specific codons themselves matter.
  // This test passed on the first try due to previous work in the aminoAcidCompare method.
  // Since these methods are so similar, work done on the aminoAcidCompare method was copied and tweaked in the codonCompare method.
  // The only difference being that the helped method codonDiff was used in place of totalDiff.
  // Since the test passed, the method can successfully compute the difference in codon between two linked lists.
  @Test
  public void testFourteen(){
    AminoAcidLL firstList = AminoAcidLL.sort(AminoAcidLL.createFromRNASequence("GGAGGA"));
    AminoAcidLL secondList = AminoAcidLL.sort(AminoAcidLL.createFromRNASequence("GGAGGC"));

    assertEquals(2, firstList.codonCompare(secondList));
  }

  // This test case is for the method codonCompare.
  // This test case tests if the method can return the value of the differences in codons between two linked lists.
  // This test uses the string of "GCAGCAAAGAAAAAA" in order to create the linked list for the instance of the class.
  // This test case also uses "GCGGCCAAA" in order to create the linked list to send as an argument to the method.
  // These strings were chosen because they include the same amino acid of A with different codons in addition to the same amino acid, K, with the same codons and an additional codon for amino acid N in the first string.
  // The use of the same amino acid (A) yet different codons tests that the values will not be subtracted, but added.
  // The use of the same amino acid (K) and codons tests that the value for each codon will actually be subtracted.
  // The use of an additional amino acid (N) in the first string tests that the method will work if the linked lists from the instance is shorter.
  // This test passed on the first try, so the method can successfully compute the difference in codons between two linked lists.
  @Test
  public void testFifteen(){
    AminoAcidLL firstList =AminoAcidLL.sort( AminoAcidLL.createFromRNASequence("GCAGCAAAAAAAAAU"));
    AminoAcidLL secondList = AminoAcidLL.sort(AminoAcidLL.createFromRNASequence("GCGGCCAAA"));

    assertEquals(6, firstList.codonCompare(secondList));
  }
}
