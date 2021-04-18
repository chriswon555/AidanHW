public class LinkedList {
	
	private Node First, Last;
	private int Length;
	
	public LinkedList (int num) { //Constructor
		this.Length = 1;
		this.First = new Node(num,null,null);
		this.Last = this.First;
	}
	
	public LinkedList (int[] numList) { //Constructor
		Node prev = null;
		Node next = null;
		this.Length = numList.length;
		this.First = new Node(numList[0],prev,next);
		prev = this.First;
		this.Last = new Node(numList[1],prev,next);
		next = this.Last;
		for(int i = 0; i < Length - 1; i++) {
			prev = new Node(numList[i],prev,next);
			next = new Node(numList[i+1],prev,next);
			prev = next;
			this.Last = next;
		}

	}
	
	public String toString() {
		return "This World Shall Know PAIN";
	}
	
	public int getIndex (int i) { //getter
		return 0;
	}
	
	public void setIndex (int i, int num) { //setter
		
	}
	
	public int listLength () {
		return 0;
	}
	
	public int findNum (int num) {
		return 0; //Sequential search is the best out of the 2
	}
	
	public int popEnd () {
		return 0;
	}
	
	public int popIndex (int i) {
		return 0;
	}
	
	public void appendNum (int num) {
		
	}
	
	public void appendArray (int[] array) {
		
	}
	
	public void appendList (LinkedList list) {
		
	}
	
	private void swapPointer (int i, int j) {
		//Does not create or swap elements, only swaps the 'pointers'
	}
	
	private void sortList () {
		//Sorts in ascending order
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
			System.out.println(i);
		}
	}
}


