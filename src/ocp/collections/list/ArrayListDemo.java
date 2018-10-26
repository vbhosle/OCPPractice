package ocp.collections.list;

import java.util.*;
public class ArrayListDemo {

	public static void main(String args[]) {
//		System.out.println(args.length); // args is not null by default
		toArrayTest();
	}
	
	public static void toArrayTest() {
		List<Integer> list = new ArrayList<>();
		list.addAll(Arrays.asList(1,2,3,4,5));
		
//		java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
//		Integer[] array = (Integer[]) list.toArray(); 
		
		//puts null after the list has been copied 
		Integer[] array = list.toArray(new Integer[] {101,102,103,104,105,106,107,108,109,110});
		
		array[0] = 100;
		list.add(200);
		System.out.println("Array:" + Arrays.toString(array));
		System.out.println("List:" + list);
	}

	public static void emptyArrayDefaultValues() {
		int[] emptyIntArray = new int[2];
		System.out.println("Empty int Array: " + Arrays.toString(emptyIntArray));
		
		Integer[] emptyIntegerArray = new Integer[2];
		System.out.println("Empty Integer Array: " + Arrays.toString(emptyIntegerArray));
		
		boolean[] emptybooleanArray = new boolean[2];
		System.out.println("Empty boolean Array: " + Arrays.toString(emptybooleanArray));
		
		Boolean[] emptyBooleanArray = new Boolean[2];
		System.out.println("Empty Boolean Array: " + Arrays.toString(emptyBooleanArray));
	}
	
	public static void asListTest() {
		List<Integer> list = Arrays.asList(1,2,3,4,5); //returns fixed size list backed by the array
		list.set(0, 100);
//		list.add(200); //java.lang.UnsupportedOperationException
//		list.remove(0); //java.lang.UnsupportedOperationException
		System.out.println("List: " + list);
	}
	
	
}
