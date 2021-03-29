import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;

public class arraysExample {

	public static void main(String[] args) {
		int [] array1 = {1,2,3,4,5,6,7,8,9};
		int [] array2 = new int [10];
		
		for (int i = 0; i < array1.length; i++) {
			System.out.println(array1[i]);
		}
		
		for (int value : array2) {
			System.out.println(value);
		}
		array1[2] = 4;
		System.out.println(array1); //Prints out memory address (Don't print it out this way)
		
		ArrayList<Integer> exampleList = new ArrayList <Integer> (); //2D is ArrayList<ArrayList>
		exampleList.add(1);
		exampleList.add(0, 10);
		System.out.println(exampleList);
		
		LinkedList<Integer> LinkedExample = new LinkedList<Integer>();
		LinkedExample.add(1);
		LinkedExample.addAll(exampleList);
		LinkedExample.get(0); //Can't use [] to access the array, [] only for basic arrays
		System.out.println(LinkedExample);
		
		HashMap<String,String> Dict = new HashMap<String,String>();
		Dict.put("Hello", "Goodbye"); //put is same as add but for Hashmap
		System.out.println(Dict);
		
		HashSet<Integer> Set = new HashSet<Integer> (); //Any time to remove duplicates
		Set.addAll(LinkedExample);
		System.out.println(Set);
		
	}

}
