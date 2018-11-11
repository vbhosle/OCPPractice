package ocp.inaction;

import ocp.inaction.model.Dish;
import static ocp.inaction.model.Dish.menu;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;

public class Grouping {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String ... args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dish names grouped by type: " + groupDishNamesByType());
//        System.out.println("Dish tags grouped by type: " + groupDishTagsByType());
        System.out.println("Caloric dishes grouped by type: " + groupCaloricDishesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOprionals());
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
       return menu.stream().collect(groupingBy(Dish::getType));
       //by default the downstream collector is toList as second arg of groupingBy
    }

    private static Map<Dish.Type, List<String>> groupDishNamesByType() {
        return menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
    }
    
//      java 9
//    private static Map<Dish.Type, Set<String>> groupDishTagsByType() {
//        return menu.stream().collect(groupingBy(Dish::getType, flatMapping(dish -> dishTags.get( dish.getName() ).stream(), toSet())));
//    }

    private static Map<Dish.Type, List<Dish>> groupCaloricDishesByType() {
        return menu.stream().filter(dish -> dish.getCalories()>500).collect(groupingBy(Dish::getType));
        // java 9
//        return menu.stream().collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
    	return menu.stream().collect(
    			groupingBy(
    					dish -> {
    						if(dish.getCalories()<=400) return CaloricLevel.DIET;
    						if(dish.getCalories()<=700) return CaloricLevel.NORMAL;
    						return CaloricLevel.FAT;
    					}
    			)
    		);
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
    	return menu.stream().collect(
    									groupingBy(Dish::getType, 
    												groupingBy(
    														dish -> {
    								    							if(dish.getCalories()<=400) return CaloricLevel.DIET;
    								    							if(dish.getCalories()<=700) return CaloricLevel.NORMAL;
    								    							return CaloricLevel.FAT;
    								    						}
    														)
    											  )
    								);
    }

    private static Map<Dish.Type, Long> countDishesInGroups() {
    	return menu.stream().collect(groupingBy(Dish::getType, counting()));
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
    	return menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparing(Dish::getCalories))));
    }

    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOprionals() {
    	return menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
    }

    private static Map<Dish.Type, Integer> sumCaloriesByType() {
    	return menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
    	return menu.stream().collect(
    			                      groupingBy(
    			                    		  	Dish::getType,
    			                    		  	mapping(dish -> {
    								    							if(dish.getCalories()<=400) return CaloricLevel.DIET;
    								    							if(dish.getCalories()<=700) return CaloricLevel.NORMAL;
    								    							return CaloricLevel.FAT;
    								    						}, toSet())
    			                    		  )
    			                    );
    }
}
