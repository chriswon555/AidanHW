public class LinkedList {
	private Node First, Last;
	private int Length;
	
	public LinkedList (int num) { //Constructor
		
	}
	
	public LinkedList (int[] numList) { //Constructor
		
	}
	
	public String toString() {
		return "0";
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
	
	
	public static void main(String[] args) {

	}
	
	private class Node {
		private int Value;
		private Node Next, Prev; //Pointers
		
		//Constructor
		public Node (int value, Node next, Node prev) { 
			this.Value = value;
			this.Next = next;
			this.Prev = prev;
		}
		//Bottom are getter and setter functions...
		public int getValue() {
			return this.Value;
		}
		
		private void setValue (int value) {
			this.Value = value;
		}

		public Node getNext() {
			return this.Next;
		}
		
		private void setNext (Node next) {
			this.Next = next;
		}
		
		public Node getPrev() {
			return this.Prev;
		}
		
		private void setPrev (Node prev) {
			this.Prev = prev;
		}
		
		//Returns node's Value as string
		public String toString() {
			return String.format("[%d]", this.Value);
		}
	}
}
