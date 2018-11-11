package ocp.inaction;

import ocp.inaction.model.Dish;
import static ocp.inaction.model.Dish.menu;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;

public class Summarizing {

    public static void main(String ... args) {
        System.out.println("Nr. of dishes: " + howManyDishes());
        System.out.println("The most caloric dish is: " + findMostCaloricDish());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator());
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Average calories in menu: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        System.out.println("Short menu: " + getShortMenu());
        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
    }


    private static long howManyDishes() {
    	  return menu.stream().collect(counting());
//        return menu.stream().count();
//    	  return menu.stream().collect(reducing(0, d -> 1, Math::addExact));
//    	  return menu.stream().mapToInt(d -> 1).sum();
    }

    private static Dish findMostCaloricDish() {
        return menu.stream().collect(maxBy(Comparator.comparing(Dish::getCalories))).get();
//    	  return menu.stream().collect(reducing((Dish d1, Dish d2) -> d1.getCalories()>d2.getCalories()?d1:d2)).get();
//    	  return menu.stream().reduce((d1, d2) -> d1.getCalories()>d2.getCalories()?d1:d2).get();
    }

    private static Dish findMostCaloricDishUsingComparator() {
    	Comparator<Dish> caloryComparator = Comparator.comparing(Dish::getCalories);
    	BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(caloryComparator);
        return menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(summingInt(Dish::getCalories));
//    	  return menu.stream().mapToInt(Dish::getCalories).sum();
//        return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
//    	  return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    private static Double calculateAverageCalories() {
        return menu.stream().collect(averagingInt(Dish::getCalories));
    }

    private static IntSummaryStatistics calculateMenuStatistics() {
        return menu.stream().collect(summarizingInt(Dish::getCalories));
    }

    private static String getShortMenu() {
        return menu.stream().map(Dish::getName).collect(joining(" ", "Dishes: ", " Enjoy!"));
//    	  return menu.stream().collect(reducing("", Dish::getName, (s1, s2)-> s1+ " " +s2));
//    	  return menu.stream().collect(reducing(new StringBuffer(), (Dish d) -> new StringBuffer(d.getName()), (s1,s2)-> s1.append(" ").append(s2))).toString();
//    	  return menu.stream().map(Dish::getName).collect(reducing((s1,s2)->s1+ " " + s2)).get();
    }

    private static String getShortMenuCommaSeparated() {
        return menu.stream().map(Dish::getName).collect(joining(","));
    }
}
