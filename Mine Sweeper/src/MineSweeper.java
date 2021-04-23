import java.util.*;

public class MineSweeper {
	int Size;
	Node First;

	private class Node {
		Node Down, Up, Right, Left;
		int NeighbourMine; //# of mines beside the revealed node
		boolean Mine, Flagged, Revealed;
		
		private Node (boolean mine) {
			this.NeighbourMine = 0;
			this.Up = null;
			this.Down = null;
			this.Left = null;
			this.Right = null;
			this.Mine = false;
		}

		public String toString() {
			return String.valueOf(this.NeighbourMine);
		}

		private void setNeighbourMine (int value) {
			this.NeighbourMine = value;
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

		private int getNeighbourMine() {
			return this.NeighbourMine;
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

		public void updateNodes() {
			if (this.Mine == false) {
				return;
			}

			this.Mine = false;
			for (Node node : this.getNeighbours()) {
				if (node != null) {
					node.updateNodes();
				}
			}
		}

		public void applyMove(int move, int initial) {
			if(this.Revealed == true) {
				return;
			}

			this.Revealed = true;
			
			for (Node node : this.getNeighbours()) {
				if (node != null) {
					node.applyMove(move, initial);
				}
			}
			this.setNeighbourMine(move);
		}
	}

	public MineSweeper(int n) {
		Random generator = new Random();
		this.Size = n;
		Node newNode, temp;
		Node prevNode = null;
		Node rowStart = null;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				newNode = new Node(true);
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
	public void flag() {

	}
	
	public void uncover() {

	}

	public void display() {
		String board;
		Node newNode = this.First;
		Node rowStart = this.First;
		while (rowStart != null) {
			board = "";
			while (newNode != null) {
				board += newNode;
				newNode = newNode.getRight();
			}
			System.out.println(board);
			newNode = rowStart.getDown();
			rowStart = newNode;
		}
	}

	public void updateNodes() {

	}

	public boolean checkWin() {
		Node node = this.First;
		Node rowStart = this.First;

		while (rowStart != null) {
			node = rowStart;
			while (node != null) {
				if (node.getNeighbours() != this.First.getNeighbours()) {
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
		int row, column, number;
		Scanner input = new Scanner(System.in);
		System.out.print("Please pick the size of the board: ");
		int size = input.nextInt();
		MineSweeper gameBoard = new MineSweeper(size);
		
		while(!gameBoard.checkWin()) {
			gameBoard.display();
			System.out.print("Enter 1 to uncover or 2 to flag: ");
			number = input.nextInt();
			switch(number) {
			case 1:
				gameBoard.uncover();
				break;
			case 2:
				gameBoard.flag();
				break;
			}
			System.out.print("Enter your which Row: ");
			row = input.nextInt();
			System.out.print("Enter your which Column: ");
			column = input.nextInt();
			gameBoard.First.applyMove(row,column);
			gameBoard.updateNodes();
			}
		input.close();
	}
}
