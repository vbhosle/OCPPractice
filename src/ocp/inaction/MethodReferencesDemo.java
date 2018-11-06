package ocp.inaction;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import ocp.inaction.model.Apple;

public class MethodReferencesDemo {

	public static void main(String args[]) {
		testMapIntegersToApples();
	}

	public static void testSortIgnoreCase() {
		List<String> list = Arrays.asList("a", "b", "A", "B");
		System.out.println("Before sorting: " + list);
		sortIgnoreCase(list);
		System.out.println("After sorting: " + list);
	}
	
	public static void testSortDesc() {
		List<Integer> list = Arrays.asList(5,2,1,6,9);
		System.out.println("Before sorting: " + list);
		sortDesc(list);
		System.out.println("After sorting: " + list);
	}
	
	public static void sortIgnoreCase(List<String> list) {
		list.sort(String::compareToIgnoreCase);
	}
	
	public static void sortDesc(List<Integer> list) {
		//this form works like (i1, i2) -> i1.compareTo(i2)
		//Comparator<Integer> asc = Integer::compareTo;
		
		//this form works like (i1, i2) -> Integer.compare(i1,i2)
		Comparator<Integer> asc = Integer::compare;
		list.sort(asc.reversed());
	}
	
	public static void testcreateRandomRedApple() {
		//constructor reference test
		System.out.println("Testing constructor reference with no args");
		Apple apple = createRandomRedApple();
		System.out.println("Created apple : " + apple);
	}
	
	public static Apple createRandomRedApple() {
		Supplier<Apple> randomAppleSupplier = Apple::new;
		return randomAppleSupplier.get();
	}
	
	public static void testCreateRedAppleByWeight(int weight) {
		//constructor reference test
		System.out.println("Testing constructor reference with one args");
		Apple apple = createRedAppleByWeight(weight);
		System.out.println("Created apple : " + apple);
	}
	
	public static Apple createRedAppleByWeight(int weight) {
		Function<Integer,Apple> redAppleWithCustomWeight = Apple::new;
		return redAppleWithCustomWeight.apply(weight);
	}
	
	public static void testMapIntegersToApples() {
		//constructor references
		List<Integer> weights = Arrays.asList(125,150,160,180);
		List<Apple> apples = mapIntegersToApples(weights, Apple::new);
		System.out.println("Created apples with supplied weights: " + apples);
	}
	
	public static List<Apple> mapIntegersToApples(List<Integer> list, Function<Integer, Apple> mapper){
		List<Apple> apples = new ArrayList<>();
		for(Integer i:list) {
			Apple apple = mapper.apply(i);
			apples.add(apple);
		}
		
		return apples;
	}
}
