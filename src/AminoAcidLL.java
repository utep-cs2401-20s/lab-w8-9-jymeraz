import jdk.dynalink.linker.LinkerServices;

class AminoAcidLL{
  private char aminoAcid;
  private String[] codons;
  private int[] counts;
  private AminoAcidLL next;

  AminoAcidLL(){}


  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  AminoAcidLL(String inCodon){
    // Convert the codon to its respective amino acid.
    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    // Retrieve the rest of the possible codons for the amino acid.
    codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    counts = new int[codons.length];
    // Increment the codon counter.
    for(int i = 0; i < codons.length; i++){
      if(codons[i].equals(inCodon)){
        counts[i]++;
      }
    }
    next = null;
  }

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  // ****CHANGE BACK TO PRIVATE*****
  private void addCodon(String inCodon){
    // Check if the current amino acid is in the linked list.
    if (aminoAcid == AminoAcidResources.getAminoAcidFromCodon(inCodon)) {
      for (int i = 0; i < codons.length; i++) {
        if (codons[i].equals(inCodon)) {
          counts[i]++;
          return;
        }
      }
    }
    if(next != null){
      // Check the next node.
      next.addCodon(inCodon);
    } else {
      // Create a new node if the amino acid is not in the linked list.
      next = new AminoAcidLL(inCodon);
    }
  }


  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){
    int sum = 0;
    for(int i = 0; i < counts.length; i++){
      sum += counts[i];
    }
    return sum;
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){
    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    int diff = 0;
    for(int i = 0; i < codons.length; i++){
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts. 
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList){
    return 0;
  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){
    return 0;
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    // Place the current amino acid in the array if next is null.
    if(next == null){
      return new char[]{aminoAcid};
    }

    // Create an array with each amino acid through recursive calls.
    char[] arr = next.aminoAcidList();
    // Declare an array one size larger than the previous array.
    char[] finalArray = new char[arr.length + 1];
    // Place the current amino acid in the beginning of the array.
    finalArray[0] = aminoAcid;

    // Manually copy the array.
    for(int i = 1; i < finalArray.length; i++){
      finalArray[i] = arr[i - 1];
    }
    return finalArray;
  }

  public void printNode() {
    System.out.println("Node contains aminoAcid of: " + aminoAcid);
  }

  public AminoAcidLL getNext() {
    return next;
  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    // Place the current count of the amino acid in the array if next is null.
    if(next == null){
      return new int[]{totalCount()};
    }

    // Create an array with each count of the amino acid through recursive calls.
    int[] arr = next.aminoAcidCounts();
    // Declare an array one size larger than the previous array.
    int[] finalArray = new int[arr.length + 1];
    // Place the current count of the amino acid in the beginning of the array.
    finalArray[0] = totalCount();

    // Manually copy the array.
    for(int i = 1; i < finalArray.length; i++){
      finalArray[i] = arr[i - 1];
    }
    return finalArray;
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    // Iterate through the array as long as there is a next value.
    if(next != null){
      // If the current amino acid is greater than the next amino acid, return false.
      if(aminoAcid > next.aminoAcid){
        return false;
      }
      // Call the method again with the next node.
      return next.isSorted();
    }
    // Return true if the linked list is sorted.
    return true;
  }

  /** ***************************************************************************************** */
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence) {
    // Check that the codons are in groups of three.
    if (inSequence.length() % 3 == 0) {
      // Save the starting node.
      AminoAcidLL newNode = new AminoAcidLL(inSequence.substring(0, 3));

      // Add the rest of the codons.
      int i = 3;
      while (i < inSequence.length()) {
        newNode.addCodon(inSequence.substring(i, i + 3));
        i = i + 3;
      }
      // Return the starting node.
      return newNode;
    }
    // Return null if the codons were not in groups of three.
    return null;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList) {
    // Initialize and declare the new starting node.
    AminoAcidLL newStartingNode = inList;
    // Keep track of the current node and the previous node.
    AminoAcidLL iterator = inList.next;
    AminoAcidLL beforeIterator = inList;

    while(iterator != null){
      AminoAcidLL nextNode = iterator.next;
      // Keep track of the current node and the previous node.
      AminoAcidLL sortedIterator = newStartingNode.next;
      AminoAcidLL beforeSortedIterator = newStartingNode;

      // Check if the value is smaller than the starting node.
      // If the value is smaller, set the starting node to it.
      if(iterator.aminoAcid < newStartingNode.aminoAcid){
        beforeIterator.next = iterator.next;
        AminoAcidLL temp = newStartingNode;
        newStartingNode = iterator;
        newStartingNode.next = temp;
      } else {
        // Iterate through the linked list up until the sorted region.
        while (sortedIterator != iterator){
          AminoAcidLL sortedIteratorReference = sortedIterator.next;

          // Check where to insert the selected value.
          if(iterator.aminoAcid < sortedIterator.aminoAcid){
            beforeSortedIterator.next = iterator;
            AminoAcidLL temp = iterator.next;
            iterator.next = sortedIterator;
            sortedIterator.next = temp;
          }
          // Find the previous node.
          AminoAcidLL findNodeBefore = newStartingNode;
          while (findNodeBefore.next != sortedIteratorReference) {
            findNodeBefore = findNodeBefore.next;
          }
          // Update the current and previous nodes.
          beforeSortedIterator = findNodeBefore;
          sortedIterator = sortedIteratorReference;
        }
      }
      // Find the previous node.
      AminoAcidLL findNodeBefore = newStartingNode;
      while (findNodeBefore.next != nextNode) {
        findNodeBefore = findNodeBefore.next;
      }
      // Update the current and previous nodes.
      beforeIterator = findNodeBefore;
      iterator = nextNode;
    }
    return newStartingNode;
  }
}