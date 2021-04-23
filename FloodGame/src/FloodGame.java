import java.util.Random;
import java.util.Scanner;
public class FloodGame {

	Node First;
	int Size;

	private class Node {
		Node Down, Up, Right, Left;
		int Value;
		boolean Visited;

		private Node (int value) {
			this.Value = value;
			this.Up = null;
			this.Down = null;
			this.Left = null;
			this.Right = null;
			this.Visited = false;
		}

		public String toString() {
			return String.valueOf(this.Value);
		}

		private void setValue (int value) {
			this.Value = value;
		}

		private void setUp(Node node) {
			this.Up = node;
		}

		private void setDown(Node node) {
			this.Down = node;
		}

		private void setRight(Node node) {
			this.Right = node;
		}

		private void setLeft(Node node) {
			this.Left = node;
		}

		private int getValue() {
			return this.Value;
		}

		private Node getUp() {
			return this.Up;
		}

		private Node getDown() {
			return this.Down;
		}

		private Node getLeft() {
			return this.Left;
		}

		private Node getRight() {
			return this.Right;
		}

		private Node[] getNeighbours() {
			Node[] array = {this.Up,this.Down,this.Right,this.Left};
			return array;
		}

		public void resetNodes() {
			if (this.Visited == false) {
				return;
			}

			this.Visited = false;
			for (Node node : this.getNeighbours()) {
				if (node != null) {
					node.resetNodes();
				}
			}
		}

		public void applyMove(int move, int initial) {
			if(this.Value != initial || this.Visited == true) {
				return;
			}

			this.Visited = true;
			
			for (Node node : this.getNeighbours()) {
				if (node != null) {
					node.applyMove(move, initial);
				}
			}
			this.setValue(move);
		}
	}
	
	public FloodGame (int size) {
		Random generator = new Random();
		this.Size = size;
		Node newNode, temp;
		Node prevNode = null;
		Node rowStart = null;

		for (int i = 0; i <= size; i++) {
			for (int j = 0; j <= size*2; j++) {
				newNode = new Node(generator.nextInt(10));
				if (i == 0 && j == 0) {
					this.First = newNode;
					rowStart = newNode;
				} else if (i == 0) {
					prevNode.setRight(newNode);
					newNode.setLeft(prevNode);
				} else if (j == 0) {
					rowStart.setDown(newNode);
					newNode.setUp(rowStart);
					rowStart = newNode;
				} else {
					prevNode.setRight(newNode);
					newNode.setLeft(prevNode);
					temp = prevNode.getUp().getRight();
					temp.setDown(newNode);
					newNode.setUp(temp);
				}
				prevNode = newNode;
			}
		}
	}

	public void displayBoard() {
		String line;
		Node node = this.First;
		Node rowStart = this.First;
		while (rowStart != null) {
			line = " ";
			while (node != null) {
				line += node;
				node = node.getRight();
			} 
			System.out.println(line);
			rowStart = rowStart.getDown();
			node = rowStart;
		}

	}

	public boolean checkWin() {
		Node node = this.First;
		Node rowStart = this.First;

		while (rowStart != null) {
			node = rowStart;
			while (node != null) {
				if (node.getValue() != this.First.getValue()) {
					System.out.println(node);
					return false;
				}
				node = node.getRight();
			}
			rowStart = rowStart.getDown();
		}
		return true;
	}

	public static void main(String[] args) {
		int move;
		Scanner input = new Scanner(System.in);
		FloodGame gameBoard = new FloodGame(5);
		while(!gameBoard.checkWin()) {
			gameBoard.displayBoard();
			System.out.println("Enter your move: ");
			move = input.nextInt();
			gameBoard.First.applyMove(move,gameBoard.First.getValue());
			gameBoard.First.resetNodes();
		}
		gameBoard.displayBoard();
		System.out.println("You Win!");
		input.close();
	}

}
