package ocp.inaction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import ocp.inaction.model.Apple;

public class FunctionalInterfacesDemo {

	public static void main(String[] args) {
//		testCustomForEach();
//		testCustomFilter();
		testCustomMap();
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
}
