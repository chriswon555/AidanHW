import java.util.Iterator;

public class LinkedList implements Iterable<Integer> {

	public Iterator<Integer> iterator() {
		return new LinkedListIterator(this);
	}
	private class LinkedListIterator implements Iterator<Integer> {
		Node Cur;

		private LinkedListIterator(LinkedList list) {
			this.Cur = list.First;
		}

		public boolean hasNext() {
			return (this.Cur != null);
		}

		public Integer next() {
			int value = Cur.getValue();
			Cur = Cur.getNext();
			return value;
		}
	}

	private Node First, Last;
	private int Length;

	public LinkedList () {
		this.Length = 0;
		this.First = null;
		this.Last = null;
	}
	public LinkedList (int num) { //Constructor for number
		this.Length = 1;
		this.First = new Node(num,null,null);
		this.Last = this.First;
	}

	public LinkedList (int[] numList) { //Constructor for array
		Node current = null;
		Node next = null;
		this.Length = numList.length;
		this.First = new Node(numList[0],null,null); //Makes the first node and sets value
		current = this.First;

		for(int i = 1; i < this.Length; i++) { 
			next = new Node(numList[i],current,null); //After a new node is made...
			current.setNext(next); //Sets previous node to point at the one that is made
			current = next; //Sets the new node made into the current
		}	
		this.Last = current; 
	}

	public String toString() {
		String line = "[";
		Node currentNode = this.First;

		for(int i = 0; i < this.Length - 1; i++) {
			//Comma and get the next value after the first to put in front of comma
			line += currentNode + ",";
			currentNode = currentNode.getNext();
		}
		return line += currentNode + "]"; //To avoid printing extra comma
	}

	public int get (int i) { //Gets the number from element i from list
		Node currentNode = this.First;
		for(int j = 0; j < this.Length; j++) {
			if(i == j) {
				return currentNode.getValue(); //Returns the value at element i of list
			} else {
				currentNode = currentNode.getNext();
			}
		}
		return -1; //If an error occurs
	}

	public void set (int i, int num) { //Sets element i in list to the number written 
		Node currentNode = this.First;
		for(int j = 0; j < this.Length; j++) {
			if(i == j) { //Sets element i in the list to the number given
				currentNode.setValue(num);
				break; //No need to go through list after it's set
			}
			currentNode = currentNode.getNext(); //sets current node to next value of list...
		}
	}

	public int length () { //Returns current length of the list
		return this.Length;
	}

	public int find (int num) {
		Node currentNode = this.First;
		for (int i = 0; i < this.Length; i++) {
			if(currentNode.getValue() == num) { //When number inside list is the same as target 
				return i;
			} else {
				currentNode = currentNode.getNext();
			}
		}
		return -1; //If the number isn't in the list
	}

	public int pop () { //Removes the end value of the list
		Node currentNode = this.Last;
		this.Last = currentNode.getPrev(); //Switches pointer, setting last node
		currentNode.setPrev(null);  //The 'old' node now points to nothing from both sides
		this.Last.setNext(null); //Since the new node is the last, it points to null to the right
		this.Length -= 1; //Ensures length is correct after removing value 
		return currentNode.getValue();
	}

	public int pop (int i) { //Removes the value in element i of the list
		int value = 0;
		Node currentNode = this.First;
		Node prevNode = this.First;
		Node nextNode = this.First;

		if(i == 0) { //If first element is getting popped
			value = this.First.getValue();
			this.First = currentNode.getNext();
			currentNode.setNext(null);
			if (this.First != null ) { //If List is empty
				this.First.setPrev(null);
			}
			this.Length -= 1;
			return value;
		}

		if(i == this.Length - 1) { //If last element is getting popped
			return pop();
		}

		for (int j = 0; j < this.Length; j++) { //Everything but the first and last elements
			if(i == j) { //When number inside list is the same as target 
				value = currentNode.getValue(); //Saves the value inside the element of this list
				nextNode = currentNode.getNext(); //Gets both previous and next nodes 
				prevNode = currentNode.getPrev(); 
				currentNode.setNext(null); //The node now points to 'null' on both sides
				currentNode.setPrev(null);
				nextNode.setPrev(prevNode); //Connects the pointers
				prevNode.setNext(nextNode);
				this.Length -= 1; //Reduces length
				return value;
			} else { 
				currentNode = currentNode.getNext();
			}
		}
		return -1; //If an error occurs 
	}

	public void append (int num) {
		Node newNode = new Node(num, this.Last, null);
		if(this.Length != 0) {
			this.Last.setNext(newNode);
			this.Last = newNode;
			this.Length += 1;
		} else {
			this.First = newNode;
			this.Last = this.First;
			this.Length = 1;
			return;
		}
	}

	public void append (int[] array) {
		LinkedList temp = new LinkedList(array);
		append(temp);
	}

	public void append (LinkedList list) {
		this.Last.setNext(list.First);
		list.First.setPrev(this.Last);
		this.Length += list.Length;
	}

	private void swap (int i, int j) { 
		Node nodeI = this.First;
		Node nodeJ = this.First;
		Node leftNode = null;
		Node rightNode = null;
		boolean foundI = false;
		boolean foundJ = false;
		for(int counter = 0; counter < this.Length; counter++) {
			if(counter == i) { //Once element i is found in the list
				foundI = true;
			}
			if(counter == j) { //Once element j is found in the list
				foundJ = true;
			} 
			if(foundI == true && foundJ == true) {
				//When both elements are found... This does the swapping
				leftNode = nodeJ.getPrev();
				rightNode = nodeJ.getNext();
				if(leftNode != null) leftNode.setNext(nodeI);
				else this.First = nodeI;

				if (rightNode != null) rightNode.setPrev(nodeI);
				else this.Last = nodeI;
				
				nodeJ.setPrev(nodeI.getPrev());
				nodeJ.setNext(nodeI.getNext());
				nodeI.setPrev(leftNode);
				nodeI.setNext(rightNode);
				
				if (nodeJ.getPrev() != null) nodeJ.getPrev().setNext(nodeJ);
				else this.First = nodeJ;
				
				if (nodeJ.getNext() != null) nodeJ.getNext().setPrev(nodeJ);
				else this.Last = nodeJ;
				return; //Once both is found, we no longer need to continue searching
			}
			if(foundI == false) { //As long as element i is not found, it will keep searching
				nodeI = nodeI.getNext();
			}
			if(foundJ == false){ //As long as element j is not found, it will keep searching
				nodeJ = nodeJ.getNext();
			}
		}
	}

	private void sort() {
		LinkedList sorted = mergeSort(this);
		this.First = sorted.First;
		this.Last = sorted.Last;
		this.Length = sorted.Length;
		return;
	}

	private LinkedList mergeSort (LinkedList list) {
		if(list.Length <= 1) { //Base case
			return list;
		}
		Node midNode = list.First;
		int midIndex = list.Length / 2;
		for(int i = 1; i < midIndex; i++) {
			midNode = midNode.getNext();
		}

		LinkedList tempLeft = new LinkedList();
		tempLeft.First = list.First;
		tempLeft.Last = midNode;
		tempLeft.Length = midIndex;
		LinkedList tempRight = new LinkedList();
		tempRight.First = midNode.getNext();
		tempRight.Last = list.Last;
		tempRight.Length = list.Length - tempLeft.Length;
		tempLeft.Last.setNext(null);
		tempRight.First.setPrev(null);
		tempLeft = mergeSort(tempLeft);
		tempRight = mergeSort(tempRight);
		return merge(tempLeft, tempRight);
	}

	private LinkedList merge(LinkedList left, LinkedList right) {
		LinkedList mergedList = new LinkedList();
		while(left.First != null && right.First != null) {
			if(left.First.getValue() <= right.First.getValue()) {
				mergedList.append(left.pop(0));
			} else {
				mergedList.append(right.pop(0));
			}
		}
		while(left.First != null) {
			mergedList.append(left.pop(0));
		}
		while(right.First != null) {
			mergedList.append(right.pop(0));
		}
		return mergedList;
	}
	private class Node {
		private int Value;
		private Node Prev, Next; //Pointers

		//Constructor
		public Node (int value, Node prev, Node next) { 
			this.Value = value;
			this.Prev = prev;
			this.Next = next;
		}
		//Bottom are getter and setter functions...
		private int getValue() {
			return this.Value;
		}

		private void setValue (int value) {
			this.Value = value;
		}

		private Node getNext() {
			return this.Next;
		}

		private void setNext (Node next) {
			this.Next = next;
		}

		private Node getPrev() {
			return this.Prev;
		}

		private void setPrev (Node prev) {
			this.Prev = prev;
		}

		//Returns node's Value as string
		public String toString() {
			return String.valueOf(this.Value);
		}
	}	

	public static String arrayString(int[] array) {
		String result = "[";
		for (int i = 0; i < array.length - 1; i++) {
			result += String.valueOf(array[i])+",";
		}
		result += String.valueOf(array[array.length-1]) + "]";
		return result;
	}

	public static void checkLinks(LinkedList list) {
		System.out.println("The following lists of numbers should be in the opposite order");
		Node nodeF = list.First;
		Node nodeL = list.Last;
		String forward = "";
		String backward = "";
		while (nodeF != null && nodeL != null) {
			forward += nodeF + " ";
			backward += nodeL + " ";
			nodeF = nodeF.getNext();
			nodeL = nodeL.getPrev();
		}
		System.out.println(forward);
		System.out.println(backward);
	}

	public static void main(String[] args) {

		System.out.println("Testing Integer Constructor and toString");
		LinkedList single = new LinkedList(4);
		System.out.println("\nThis list should contain only the number 4");
		System.out.println(single);
		
		System.out.println("\nTesting get function");
		System.out.println(String.format("These should be equal: %d = 4", single.get(0)));
		
		System.out.println("\nTesting Integer Array Constructor");
		int[] intArray = {356,7,1243,7,53,52,547,2,4,2,467,24};
		LinkedList arrayList = new LinkedList(intArray);
		System.out.println("\nThe following lists should be the same");
		System.out.println(arrayList);
		System.out.println(arrayString(intArray));
		
		System.out.println("\nTesting set function");
		arrayList.set(4,100);
		System.out.println("\nThe 5th element in the following list should be 100");
		System.out.println(arrayList);
		
		System.out.println("\nTesting pointers");
		checkLinks(arrayList);
		
		System.out.println("\nTesting pop functions");
		int temp = arrayList.pop();
		System.out.println("\nThis list should end with 467");
		System.out.println(arrayList);
		System.out.println(String.format("\nThese should be equal: %d = 24",temp));
		temp = arrayList.pop(2);
		System.out.println("\nThis list should no longer contain the number 1243");
		System.out.println(arrayList);
		System.out.println(String.format("These should be equal: %d = 1243",temp));
		
		System.out.println("\nTesting append functions");
		arrayList.append(42);
		System.out.println("\nThe last element in this list below should be 42");
		System.out.println(arrayList);
		int[] intArray2 = {624,8,24,724,7,248,587943,461,753724};
		int[] intArray2test = {4,624,8,24,724,7,248,587943,461,753724};
		single.append(intArray2);
		System.out.println("\nThese lists should be the same");
		System.out.println(single);
		System.out.println(arrayString(intArray2test));
		System.out.println("\nThe third list below should be the first list followed by the second list");
		System.out.println(arrayList);
		System.out.println(single);
		arrayList.append(single);
		System.out.println(arrayList);
		
		System.out.println("\nTesting find function");
		System.out.println(String.format("These should be equal: %d = 6", arrayList.find(2)));
		System.out.println(String.format("These should be equal: %d = -1", arrayList.find(101)));
		
		System.out.println("\nTesting sort function, the following list should be sorted");
		arrayList.sort();
		System.out.println(arrayList);
		
		System.out.println("\nTesting swap function, the 2nd and 12th elements should have been swapped");
		arrayList.swap(1, 11);
		System.out.println(arrayList);
		
		System.out.println("\nTesting pointers again and checking array length");
		checkLinks(arrayList);
		System.out.println(String.format("\nThe following should be equal: %d = 21", arrayList.length()));
		for (Integer i: arrayList) {
			System.out.print(i + " ");
		}
	}
}


