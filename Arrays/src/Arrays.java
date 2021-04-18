import java.util.Random;
import java.util.Scanner;
import java.time.Clock;

public class Arrays {
	static int SIZE = 100;

	public static void main(String[] args) {
		Clock Timer = Clock.systemUTC();
		int [] array = new int [SIZE];
		Scanner input = new Scanner(System.in);
		int number = 1;
		int target;

		while(number != 0) {
			System.out.println("---------------------------------------------------------");
			System.out.println("0. Exit the program");
			System.out.println("1. Populate the array sequentially from 1-100");
			System.out.println("2. Populate the array randomly with numbers from 1-100");
			System.out.println("3. Checks to see if the array is sorted");
			System.out.println("4. Displays the array");
			System.out.println("5. Shuffles the array");
			System.out.println("6. Sequential Search");
			System.out.println("7. Binary Search");
			System.out.println("8. Selection Sort");
			System.out.println("9. Bubble Sort");
			System.out.println("10. Quick Sort");
			System.out.println("11. Merge Sort");
			System.out.print("Pick a number: " );
			
			number = input.nextInt();
			long iniTime = Timer.millis();
			
			switch (number) {
			case 1:
				sequentialArray(array);
				break;

			case 2:
				randomArray(array);
				break;

			case 3:
				System.out.println(isArraySorted(array));
				break;

			case 4:
				displayArray(array);
				break;

			case 5:
				shuffle(array);
				break;

			case 6:
				System.out.print("Please enter a number you would like to find: ");
				target = input.nextInt();
				seqSearch(array,target);
				break;

			case 7:
				System.out.print("Please enter a number you would like to find: ");
				target = input.nextInt();
				binarySearch(array,target);
				break;

			case 8:
				selSort(array);
				break;

			case 9:
				bubSort(array);
				break;

			case 10:
				quickSort(array,0,SIZE-1);
				break;

			case 11:
				mergeSort(array,0,SIZE-1);
				break;

			}
			System.out.println(String.format("\nCompleted in %d milliseconds", (Timer.millis() - iniTime) ));
			System.out.println("\n");
		}
	}

	public static void sequentialArray (int[] array) {//if they pick number 1
		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}	
	}

	public static void randomArray (int[] array) { //if they pick number 2
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) 
			array[i] = rand.nextInt(100) + 1; //random # 1-100
	}

	public static boolean isArraySorted (int[] array) { //if they pick number 3
		for(int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				return false;
			}	
		}
		return true; //Check if it is ascending. If it is, return true. If not, return false
	}


	public static void displayArray (int[] array) {//if they pick number 4
		String line;
		int rLen = (int) Math.sqrt(array.length);
		int cLen = array.length / rLen;
		System.out.println("\n------------------------------------");
		for (int i = 0; i < cLen; i++) {
			line = "";
			for (int j = 0; j < rLen; j++) {
				if (array[i*rLen+j] < 10) {
					line += " ";
				}
				line += array[i*rLen+j]+ " ";
			}
			System.out.println(line);
		}
	}

	public static void shuffle(int[] array) { //if they pick number 5
		int shuffle = 0;
		int temp = 0;

		for(int x = 0; x < array.length; x++)
		{
			shuffle = (int)(Math.random()*array.length); //Shuffles a number into a different place in the index
			temp = array[x];
			array[x] = array[shuffle];
			array[shuffle] = temp;
		}
	}

	public static int seqSearch (int[] array, int target) { //If they pick number 6
		int index = 0;
		do {
			if(array[index] == target) { //When number inside array is the same as target
				System.out.println("The number is in index: " + index); 
				return index;
			} else {
				index++; //Moves to the next number in the array...
			}
		}while(index < array.length);

		System.out.println(-1);
		return -1;//If number is not found, it returns "-1"
	}

	public static int binarySearch (int[] array, int target) { //If they pick number 7
		int left = 0;
		int right = array.length - 1; 
		
		if (isArraySorted(array)) { //Checks if Array is sorted before searching
			
			do {
				int mid = (left + right) / 2; //Finds the middle of array 

				if(array[mid] == target) { //If they are the same, value is returned
					System.out.println("The number is in index: " + mid);
					return mid;	
				}

				if (array[mid] < target) { //Chooses which part of the array to search next
					left = mid + 1; 
				} else {  
					right = mid - 1; 
				}
			} while (left <= right); //Continues until the array is of size one 
			
			//When array is sorted but number is not found, it returns '-1'
			System.out.println(-1); 
			return -1;
		} else { //If array is not sorted and number is not found, it returns '-2'
			System.out.println(-2); 
			return -2;
		}
	}

	public static int[] selSort (int[] array) { //If they pick number 8
		//Right and left scope of array
		int right = array.length - 1; 
		int left = 0; 

		for(int x = left; x < right + 1; x++) { //Shifts Array after a number is set
			//Starts off with same numbers and same indices
			int indexSmall = left;
			int indexBig = left;
			int smallest = array[x];
			int biggest = array[x];
			for(int i = left ; i < right + 1; i++) { //Find and sets smallest and biggest number in Array
				if (smallest > array[i]) { 
					smallest = array[i]; //Number in Array
					indexSmall = i; //Index of the number
				} else if (biggest < array[i]) {
					biggest = array[i]; //Number in Array
					indexBig = i; //Index of the number
				} 
			}
			if (left != right) { //Ensure that there is no double swap
				array[indexSmall] = array[left]; //Swaps the numbers through their indices
				array[left] = smallest;
				left++; //When lowest number is set, it does not check it again
			} else {
				array[indexBig] = array[right]; //Swaps the numbers through their indices
				array[right] = biggest; 
				right--; //When highest number is set, it does not check it again
			}
		}
		return array;
	}

	public static int[] bubSort (int[] array) { //If they pick number 9
		int right = array.length; //Right side scope of Array
		
		while(true) { //Keeps the sort algorithm going
			
			int bigger; //Place holder variable
			boolean sorted = true;
			for(int i = 0 ; i < right - 1; i++) {
				if (array[i] > array[i + 1]) { //Swaps numbers until largest # is at the end.
					bigger = array[i]; 
					array[i] = array[i + 1]; 
					array[i + 1] = bigger;
					sorted = false; //If a swap is achieved, array is not sorted
				} 
			}
			if (sorted == true) { //When array is sorted...
				break; //Helps break out loop so it doesn't have to repeat a sorted array
			}
			right--; //After the loop, last number MUST be the biggest number...
			//therefore the last spot does not need to be swapped again cause it's set
		}
		return array;
	}

	public static int[] quickSort (int[] array, int left, int right) { //If they pick number 10
		if (left == right) { //Base case
			return array;	
		}
		
		if(left < right) {
			int part = partition(array,left-1,right+1);//Adds and Subs so that it starts searching at index 0
			array = quickSort(array, left, part);
			array = quickSort(array, part + 1, right);
		}
		return array;
	}

	private static int partition (int array[], int left, int right) {
		int indexL = left;
		int indexR = right;
		int pivot = array[(left + right)/2]; //Picks middle of array as pivot (Rounds down if even)
		while(true){ //To keep the code running
			do { 
				indexL++;
			} while (array[indexL] < pivot);
			do{
				indexR--;
			} while (array[indexR] > pivot);

			if(indexL >= indexR) { //Once the left side of array is bigger than right
				return indexR; //It will know that the side is sorted
			} else {
				//Swapping
				int temp = array[indexL];
				array[indexL] = array[indexR];
				array[indexR] = temp;
			}
		}
	}

	public static int[] mergeSort (int[] array, int left, int right) { //If they pick number 11
		if(left == right) { //Base case
			return array;
		}

		if (left < right) { 
			int pivot = (left + right)/2;
			array = mergeSort(array,left,pivot);
			array = mergeSort(array,pivot + 1,right);
			merge(array, left, pivot, right);
		}
		return array;
	}

	private static void merge (int[] array, int left, int middle, int right) {
		int pivot = middle;
		//Copys Array into a temp Array
		int [] tempArrayL = new int [pivot - left + 1];
		int [] tempArrayR = new int [right - pivot];

		for(int indexL = 0; indexL < tempArrayL.length; indexL++) {
			tempArrayL[indexL] = array[left + indexL];
		}
		for(int indexR = 0; indexR < tempArrayR.length; indexR++) {
			tempArrayR[indexR] = array[pivot + 1 + indexR];
		}
		//Indices of split array and initial array
		int tempLeft = 0; 
		int tempRight = 0;
		int arrayI = left;

		while(tempLeft < tempArrayL.length && tempRight < tempArrayR.length) {
			if(tempArrayL[tempLeft] <= tempArrayR[tempRight]) {
				array[arrayI] = tempArrayL[tempLeft];
				tempLeft++;
			} else {
				array[arrayI] = tempArrayR[tempRight];
				tempRight++;
			}		
			arrayI++;
		}
		//If temp arrays have nothing to compare, they will add whatever values left to the end
		while(tempLeft < tempArrayL.length) {
			array[arrayI] = tempArrayL[tempLeft];
			tempLeft++;
			arrayI++; 
		}

		while(tempRight < tempArrayR.length) {
			array[arrayI] = tempArrayR[tempRight];
			tempRight++;
			arrayI++;
		}
	}
}
