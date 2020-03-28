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
  // What to initialize next to?
  AminoAcidLL(String inCodon){
    aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    counts = new int[codons.length];
    // don't worry about not finding codon.
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
  // why should it go to the next node if it could check the rest of the codons array in one node?
  // ****CHANGE BACK TO PRIVATE*****
  public void addCodon(String inCodon){
    if (aminoAcid == AminoAcidResources.getAminoAcidFromCodon(inCodon)) {
      for (int i = 0; i < codons.length; i++) {
        if (codons[i].equals(inCodon)) {
          counts[i]++;
          return;
        }
      }
    }
    if(next != null){
      // how to call the addCodon method from an instance of AminoAcidLL
      // use head in tester to call it
      next.addCodon(inCodon);
    } else {
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
    if(next == null){
      return new char[]{aminoAcid};
    }
    // place the current amino acid in the array
    next.aminoAcidList();
    return next.aminoAcidList();

  }

  public char[] aminoAdd(char[] previousArray, char aminoToAdd){
    char[] newArray = new char[previousArray.length + 1];
    for(int i = 0; i < newArray.length; i++){
      newArray[i] = previousArray[i];
    }
    newArray[newArray.length - 1] = aminoToAdd;
    return newArray;
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
    return new int[]{};
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    if(next != null){
      if(aminoAcid > next.aminoAcid){
        return false;
      }
      return next.isSorted();
    }
    return true;
  }

  /** ***************************************************************************************** */
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence) {
    // translate each 3 into amino acid
    if (inSequence.length() > 2) {
      AminoAcidLL newNode = new AminoAcidLL(inSequence.substring(0, 3));

      int i = 3;
      while (i < inSequence.length()) {
        newNode.addCodon(inSequence.substring(i, i + 3));
        i = i + 3;
      }
      return newNode;
    }
    return null;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList) {
    AminoAcidLL newStartingNode = inList;
    AminoAcidLL iterator = inList.next;
    AminoAcidLL beforeIterator = inList;

    while(iterator != null){
      AminoAcidLL nextNode = iterator.next;
      AminoAcidLL sortedIterator = newStartingNode.next;
      AminoAcidLL beforeSortedIterator = newStartingNode;

      if(iterator.aminoAcid < newStartingNode.aminoAcid){
        beforeIterator.next = iterator.next;
        AminoAcidLL temp = newStartingNode;
        newStartingNode = iterator;
        newStartingNode.next = temp;
      } else {
        while (sortedIterator != iterator){
          AminoAcidLL sortedIteratorReference = sortedIterator.next;
          if(iterator.aminoAcid < sortedIterator.aminoAcid){
            beforeSortedIterator.next = iterator;
            AminoAcidLL temp = iterator.next;
            iterator.next = sortedIterator;
            sortedIterator.next = temp;
          }

          AminoAcidLL findNodeBefore = newStartingNode;
          while (findNodeBefore.next != sortedIteratorReference) {
            findNodeBefore = findNodeBefore.next;
          }

          beforeSortedIterator = findNodeBefore;
          sortedIterator = sortedIteratorReference;
        }
      }

      AminoAcidLL findNodeBefore = newStartingNode;
      while (findNodeBefore.next != nextNode) {
        findNodeBefore = findNodeBefore.next;
      }

      beforeIterator = findNodeBefore;
      iterator = nextNode;
    }

    return newStartingNode;
  }
}