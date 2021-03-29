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
		System.out.print("Pick a number: ");

		while(number != 0) {
			number = input.nextInt();
			long iniTime = Timer.millis();

			if(number == 1) //Populates array randomly
			{
				sequentialArray(array);
			}
			if(number == 2) //Populate the array sequentially from 1 to 100
			{
				randomArray(array);
			}
			if(number == 3) //Check if Array is sorted
			{
				System.out.println(checkArray(array));
			}
			if(number == 4)//Displays Array
			{
				displayArray(array);	
			}
			if(number == 5) //Shuffles Array
			{
				shuffle(array);
			}
			if(number == 6) //Sequentially Searches the Array for a number input by user
			{
				System.out.print("Please enter a number you would like to find: ");
				target = input.nextInt();
				seqSearch(array,target);
			}
			if(number == 7) //Binary searches the Array for a number input by user
			{
				System.out.print("Please enter a number you would like to find: ");
				target = input.nextInt();
				binarySearch(array,target);
			}
			if(number == 8) //Selection Sort
			{
				selSort(array);
			}
			if(number == 9) //Bubble Sort
			{
				bubSort(array);
			}
			if(number == 10) //Quick Sort
			{
				quickSort(array,0,SIZE-1);
			}
			if(number == 11) //Merge Sort
			{
				mergeSort(array,0,SIZE-1);
			}
			System.out.println(String.format("Completed in %d milliseconds", (Timer.millis() - iniTime) ));		
		}
		input.close();
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

	public static boolean checkArray (int[] array) { //if they pick number 3
		for(int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				return false;
			}	
		}
		return true; //Check if it is ascending. If it is, return true. If not, return false
	}


	public static void displayArray (int[] array) {//if they pick number 4
		//		String display = "[";
		//
		//		for (int i = 0; i < array.length - 1; i++) {
		//			display += array[i] + ", ";
		//		}
		//
		//		display += array[array.length - 1] + "]";
		//		System.out.println(display);
		//Mr.Clark Printing
		String line;
		int rLen = (int) Math.sqrt(array.length);
		int cLen = array.length / rLen;
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

		do {//Works 100% of the time only when array is fully sorted 
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

		if (checkArray(array)) {
			System.out.println(-1);
			return -1; //If array is sorted but number is not found, it returns "-1"
		} else {
			System.out.println(-2); //If array is not sorted is not found, it returns "-2"
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
			}//Must recheck after, I feel like there is a problem...
		}
		return array;
	}

	public static int[] bubSort (int[] array) { //If they pick number 9
		int right = array.length; //Right side scope of Array
		do {
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
		}while(right > 1);
		return array;
	}

	public static int[] quickSort (int[] array, int left, int right) { //If they pick number 10
		if(left < right) {
			int p = partition(array,left-1,right+1);//Adds and Subs so that it starts searching at index 0
			quickSort(array, left, p);
			quickSort(array, p + 1, right);
		}
		return array;
	}

	private static int partition (int array[], int left, int right) {
		int index = left;
		int j = right;
		int pivot = array[(left + right)/2]; //Picks middle of array as pivot (Rounds down if even)
		while(true){
			do {
				index++;
			} while (array[index] < pivot);
			do{
				j--;
			} while (array[j] > pivot);

			if(index >= j) { 
				return j;
			} else {
				int temp = array[index];
				array[index] = array[j];
				array[j] = temp;
			}
		}
	}

	public static void mergeSort (int[] array, int left, int right) { //If they pick number 11
		if (left < right) {
			int pivot = left + (right - 1)/2;
			mergeSort(array,left,pivot);
			mergeSort(array,pivot+1,right);
			merge(array, left, pivot, right);
		}
		//return array;
	}

	private static void merge (int[] array, int left, int middle, int right) {
		int pivot = (left + right)/2;
		//Copys Array into a temp Array
		int [] tempArrayL = new int [pivot];
		int [] tempArrayR = new int [right - pivot];

		for(int indexL = 0; indexL < pivot; indexL++) {
			tempArrayL[indexL] = array[left + indexL];
		}
		for(int indexR = 0; indexR < right - pivot; indexR++) {
			tempArrayR[indexR] = array[pivot + 1 + indexR];
		}
		//Indices of split array and initial array
		int l = 0; 
		int r= 0;
		int arrayI = 0;

		while(l < pivot && r < right - pivot) {
			if(tempArrayL[l] <= tempArrayR[r]) {
				array[arrayI] = tempArrayL[l];
				l++;
			} else {
				array[arrayI] = tempArrayR[r];
				r++;
			}		
			arrayI++;
		}
		//If temp arrays have nothing to compare, they will add whatever values left to the end
		while(l < pivot) {
			array[arrayI] = tempArrayL[l];
			l++;
			arrayI++;
		}
		
		while(r < right - pivot) {
			array[arrayI] = tempArrayR[r];
			r++;
			arrayI++;
		}
	}
}
