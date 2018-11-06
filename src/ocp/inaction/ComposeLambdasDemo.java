package ocp.inaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import ocp.inaction.model.Apple;

public class ComposeLambdasDemo {

	public static void main(String[] args) {
		testComparatorChaining();
	}
	
	public static void testComparatorChaining() {
		List<Apple> apples = new ArrayList<>();
		populateApples(apples);
		System.out.println("Apples before sorting: " + apples);
		apples.sort(Comparator.comparing(Apple::getColor).reversed().thenComparing(Apple::getWeight));
		System.out.println("Apples after sorting: " + apples);
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
