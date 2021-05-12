import java.util.*;

public class MineSweeper {
	//Size keeps track of how many cells there are on the board
	//counterRevealed counts how many are cells are revealed on the board
	//counterMines Counts how many mines there are on the board
	int Size, counterRevealed, counterMines;
	Node First;

	private class Node {
		Node Down, Up, Right, Left, TopRight, TopLeft, BottomRight, BottomLeft; //Neighbour pointers
		int NeighbourMine; //# of mines beside the revealed cell 
		boolean Mine, Flagged, Revealed;

		private Node () { //Constructor
			this.NeighbourMine = 0;
			this.Up = null;
			this.Down = null;
			this.Left = null;
			this.Right = null;
			this.Mine = false;
			this.Flagged = false;
			this.Revealed = false;
		}

		public String toString() { 
			if (this.Revealed == false) { 
				//Player can only flag a cell if it is not revealed yet
				if (this.Flagged == true) { 
					return String.valueOf("!");
				}
				//Otherwise it'll be a dot
				return String.valueOf(".");
			}
			if (this.Mine) { 
				return String.valueOf("X");
			} 
			//This displays the # of Mines beside the cell revealed by user
			return String.valueOf(this.NeighbourMine);
		}

		//Setters
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

		private void setTopRight(Node node) {
			this.TopRight = node;
		}

		private void setTopLeft(Node node) {
			this.TopLeft = node;
		}

		private void setBottomRight(Node node) {
			this.BottomRight = node;
		}

		private void setBottomLeft(Node node) {
			this.BottomLeft = node;
		}

		//Getters
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

		private Node getTopRight() {
			return this.TopRight;
		}

		private Node getTopLeft() {
			return this.TopLeft;
		}

		private Node getBottomRight() {
			return this.BottomRight;
		}

		private Node getBottomLeft() {
			return this.BottomLeft;
		}

		//Helper functions
		private Node[] getNeighbours() {
			Node[] array = {this.Up,this.Down,this.Right,this.Left,
					this.TopRight,this.TopLeft,this.BottomLeft,this.BottomRight};
			return array;
		}

		public void countMines() { //Counts the # of mines beside revealed cell
			int mine = 0;
			if (this.Mine == true) {
				return;
			}
			//Grabs each neighbour beside the cell
			for (Node node : this.getNeighbours()) { 
				if (node != null && node.Mine == true) {
					mine++;
				}
			}
			this.setNeighbourMine(mine);
		}
	}

	public MineSweeper(int n) { 
		this.Size = n * n;
		int numberOfMines = (int) (Size * 0.15); //# of Mines will always be 15% of the size of board
		Random generator = new Random();

		Node newNode, temp = null;
		Node prevNode = null;
		Node rowStart = null;
		//Making and connecting each pointer on the board 
		for (int i = 0; i < n; i++) { //Rows
			for (int j = 0; j < n; j++) { //Columns
				newNode = new Node();
				if (i == 0 && j == 0) { //First Node
					this.First = newNode;
					rowStart = newNode;
				} 
				else if (i == 0) { //First Row
					prevNode.setRight(newNode);
					newNode.setLeft(prevNode);
				} 
				else if (j == 0) { //New Columns
					//Up and Downs
					rowStart.setDown(newNode);
					newNode.setUp(rowStart);
					//Diagonals
					temp = rowStart.getRight();
					temp.setBottomLeft(newNode);
					newNode.setTopRight(temp);
					rowStart = newNode;
				} 
				else { //Everything that is not a new column or row
					//Setting pointers for what is Left of the newNode
					prevNode.setRight(newNode);
					newNode.setLeft(prevNode);
					//Setting pointers for what is above newNode
					temp = prevNode.getUp().getRight(); 
					temp.setDown(newNode);
					newNode.setUp(temp);
					//Setting pointers for what is Top Left of newNode
					temp = prevNode.getUp();
					temp.setBottomRight(newNode);
					newNode.setTopLeft(temp);
					//Setting pointers for what is Top Right of newNode
					temp = newNode.getUp().getRight();

					//If it reaches the end of a Row... it'll skip this step
					if(temp != null) { //Since there is nothing to set pointers to...
						temp.setBottomLeft(newNode);
						newNode.setTopRight(temp);
					}
				}
				//This generates mines in somewhat random places while the board is being set
				if(generator.nextInt(Size - j - i*n) < numberOfMines) {
					//Ensure correct amount of mines are being made...
					numberOfMines--;
					this.counterMines++;
					//Once a position is established, this cell now becomes a mine
					newNode.Mine = true;
				}
				prevNode = newNode;
			}
		}

		//Loops through again to get and set the # of Mines beside each of the cells
		temp = this.First;
		rowStart = this.First;
		for (int i = 0; i < n; i++) { //Rows
			for (int j = 0; j < n; j++) { //Columns
				temp.countMines(); //Counts the Mines
				temp = temp.getRight(); //Moves through Columns
			}
			//Moves through Rows
			rowStart = rowStart.getDown();
			temp = rowStart;
		}
	}
	public void applyMove(Node nodeTemp) { //Uncovering the cells
		//Base cases:
		//#1) If its been revealed...
		if (nodeTemp.Revealed) { 
			return;
		}
		
		nodeTemp.Revealed = true; //Must reveal it if its not revealed
		this.counterRevealed++;	//Keeps track of how many cells have been revealed 

		//#2) If the cell is a number that is NOT 0 OR it is a mine...
		if(nodeTemp.Mine == true || nodeTemp.NeighbourMine != 0) {
			return;
		}

		//Reveals everything that is 0 and it recursively get its neighbours
		for (Node node : nodeTemp.getNeighbours()) {
			if (node != null) {
				applyMove(node); 
			}
		}
	}

	private Node location (int x, int y) { //Gets the location of the cell the users input
		//x is column, y is row
		Node currentNode = this.First;
		for (int j = 1; j < x; j++) { //Column
			currentNode = currentNode.getRight();
		}
		for (int i = 1; i < y; i++) { //Row
			currentNode = currentNode.getDown();
		}
		return currentNode;
	}

	public void display() { //Displays the board
		String board;
		Node newNode = this.First;
		Node rowStart = this.First;
		while (rowStart != null) {
			board = "";
			while (newNode != null) {
				board += " " + newNode;
				newNode = newNode.getRight();
			}
			System.out.println(board);
			newNode = rowStart.getDown();
			rowStart = newNode;
		}
	}

	public boolean checkWin(int numberOfMines) { 
		//If all the cells revealed are equal to the number of mines that aren't revealed...
		if(this.counterRevealed == Size - numberOfMines) {
			return true; //Then you win
		}
		return false; //If not, it will keep going until you hit a Mine or win
	}

	public static void main(String[] args) {
		int row = 0;
		int column = 0;
		int number;
		Node tempNode = null;
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to MineSweeper");
		System.out.println("The Number of Mines will be 15% of the size of your board");
		System.out.println("-----------------------------------------------------------");
		System.out.print("Please pick the size of the board: ");
		int size = input.nextInt();
		MineSweeper gameBoard = new MineSweeper(size);

		//Continues until all non-mine cells are revealed...
		while(!gameBoard.checkWin(gameBoard.counterMines)) { 
			gameBoard.display();
			System.out.print("\n\n\nEnter 1 to uncover or 2 to flag: ");
			number = input.nextInt();

			switch(number) {
			case 1: 
				//If they choose the Uncover option
				System.out.print("Enter which Column: ");
				column = input.nextInt();
				System.out.print("Enter which Row: ");
				row = input.nextInt();
				gameBoard.applyMove(gameBoard.location(column, row));
				tempNode = gameBoard.location(column, row);

				if(tempNode.Mine) { //When they reveal a mine, they lose...
					//Displays board once more before game ends
					gameBoard.display(); 
					System.out.println("\n\n\nYou Hit A Mine, Died, and Lost...");
					return;
				}
				break;
			case 2: 
				//If they choose the Flag option
				System.out.print("Enter which Column: ");
				column = input.nextInt();
				System.out.print("Enter which Row: ");
				row = input.nextInt();

				//If player flags a cell that is already flagged, it un-flags it
				tempNode = gameBoard.location(column, row);
				tempNode.Flagged = !tempNode.Flagged; 
				break;
			}
		}
		//Displays board once more before game ends
		gameBoard.display();
		System.out.println("\n\n\nYou Somehow Survived and Won...");
		input.close();
	}
}
