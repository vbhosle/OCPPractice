package ocp.inaction.lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ocp.inaction.Apple;

public class SortingDemo {

	public static void main(String[] args) {
//		filterWithPredicates();
//		sortWithComparing();
//		testCustomFilter();
		testCustomMap();
	}

	public static void populateApples(List<Apple> list) {
		list.add(new Apple("green", 100));
		list.add(new Apple("red", 180));
		list.add(new Apple("red", 130));
		list.add(new Apple("green", 170));
		list.add(new Apple("green", 160));
		list.add(new Apple("red", 120));
		list.add(new Apple("yellow", 110));
		list.add(new Apple("yellow", 160));
		list.add(new Apple("yellow", 120));
	}

	public static void filterWithPredicates() {
		List<Apple> list = new ArrayList<>();
		populateApples(list);

		System.out.println("All apples: " + list);

		// List<Apple> redApples = list.stream().filter((Apple a) ->
		// "red".equals(a.getColor())).collect(Collectors.toList());
		Predicate<Apple> redApples = a -> "red".equals(a.getColor());
		Predicate<Apple> yellowApples = a -> "yellow".equals(a.getColor());
		Predicate<Apple> lightApples = a -> a.getWeight() < 150;

		List<Apple> redApplesList = list.stream().filter(redApples).collect(Collectors.toList());
		System.out.println("Red apples: " + redApplesList);

		List<Apple> lightRedApplesList = list.stream().filter(redApples.and(lightApples)).collect(Collectors.toList());
		System.out.println("Light-weight Red apples: " + lightRedApplesList);

		List<Apple> nonRedApplesList = list.stream().filter(redApples.negate()).collect(Collectors.toList());
		System.out.println("Non red apples: " + nonRedApplesList);

		// light weight yellow or heavy weight red
		Predicate<Apple> lightYellow = yellowApples.and(lightApples);
		Predicate<Apple> heavyRed = redApples.and(lightApples.negate());

		List<Apple> lightYellowOrHeavyRedList = list.stream().filter(lightYellow.or(heavyRed))
				.collect(Collectors.toList());
		System.out.println("light weight yellow or heavy weight red: " + lightYellowOrHeavyRedList);
	}

	public static void sortWithComparing() {
		List<Apple> list = new ArrayList<>();
		populateApples(list);

		System.out.println("All apples: " + list);

		// List<Apple> sortedByColor = list.stream().sorted((a1,a2) ->
		// a1.getColor().compareTo(a2.getColor())).collect(Collectors.toList());
		List<Apple> sortedByColor = list.stream()
				.sorted(Comparator.comparing(Apple::getColor).thenComparingInt(Apple::getWeight))
				.collect(Collectors.toList());
		System.out.println("Sorted by color :" + sortedByColor);
	}
	
	
	public static <T> void foreach(List<T> list, Consumer<T> c) {
		for(T item:list) {
			c.accept(item);
		}
	}
	
	public static void testCustomForEach() {
		List<Apple> list = new ArrayList<>();
		populateApples(list);
		
		foreach(list, System.out::println);
		foreach(list, a -> System.out.println(a.getColor()));
		foreach(list, a -> System.out.println(a.getWeight()>150?"heavy":"light"));
	}
	
	public static <T> List<T> filter(List<T> list, Predicate<T> criteria){
		List<T> filteredList = new ArrayList<>();
		
		for(T item:list) {
			if(criteria.test(item)) {
				filteredList.add(item);
			}
		}
		
		return filteredList;
	}
	
	public static void testCustomFilter() {
		List<Apple> list = new ArrayList<>();
		populateApples(list);
		
		foreach(filter(list, a -> "red".equals(a.getColor())), System.out::println);
	}
	
	public static <T,R> List<R> map(List<T> list, Function<T, R> mapper) {
		List<R> mappedList = new ArrayList<>();
		
		for(T item: list) {
			mappedList.add(mapper.apply(item));
		}
		
		return mappedList;
	}
	
	
	public static void testCustomMap() {
		List<Apple> list = new ArrayList<>();
		populateApples(list);
		
		foreach(map(list, Apple::getColor), System.out::println);
	} 
}
