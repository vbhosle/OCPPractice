package ocp.inaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ocp.inaction.model.Apple;

public class FilterSortDemo {

	public static void main(String[] args) {
//		filterWithPredicates();
//		sortWithComparing();
//		testPrimitivePredicates();
//		printLogBase10(1000);
		System.out.println("Minimum of 50 & 10 is "+ minInt(50, 10));
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
	
	public static void testPrimitivePredicates() {
		int x = 10;
		System.out.println(x + " is even? :" + isEvenInteger(x));
		
		long l = 2147483648L;
		System.out.println(l + " is even? :" + isEvenLong(l));
	}
	
	public static boolean isEvenInteger(int nbr) {
		IntPredicate isEven = x -> x%2 == 0;
		return isEven.test(nbr);
	}
	
	public static boolean isEvenLong(long nbr) {
		LongPredicate isEven = x -> x%2 == 0;
		return isEven.test(nbr);
	}
	
	public static void printLogBase10(double number) {
		DoubleConsumer log10 = x -> System.out.println("log10 of "+ number + " is " + Math.log10(x));
		log10.accept(number);
	}
	
	public static int minInt(int a, int b) {
		IntBinaryOperator min = Math::min;
		return min.applyAsInt(a, b);
	}
	
//	public static int tripleTheInt(int a) {
////		IntFunction tripler = 
//	}
}
