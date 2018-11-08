package ocp.inaction;

import ocp.inaction.model.Trader;
import ocp.inaction.model.Transaction;

import java.util.*;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Chapter5StreamsDemo {

	public static void main(String[] args) {
		testPythagorianTripples();
	}

	public static void testSumOfRange() {
		int start = 1;
		int end = 10;
		IntPredicate evenNumbers = i -> i%2 == 0;
		System.out.println("Sum of even numbers from " + start + " - " + end + " = " + sumOfRange(start, end, evenNumbers));
	}
	
	public static void testPythagorianTripples() {
		int start = 1;
		int end = 100;
		printPythagorianTripples(start,end);
	}
	
	public static int sumOfRange(int start, int end, IntPredicate filterCondition) {
//		return IntStream.rangeClosed(start, end).filter(filterCondition).sum();
//		return IntStream.rangeClosed(start, end).filter(filterCondition).reduce(0, Math::addExact);
		return IntStream.rangeClosed(start, end).filter(filterCondition).reduce(0, (a, b) -> a + b);
	}
	
	public static void printPythagorianTripples(int start , int end) {
		Stream<int[]> pythagorianTripples = IntStream.rangeClosed(start, end)
				 .boxed()
				 .flatMap(
					a -> IntStream.rangeClosed(a, end)
						          .filter(b -> Math.sqrt(a*a + b*b) %1 == 0)
						          .mapToObj(b -> new int[] {a, b, (int) Math.sqrt(a*a + b*b)})
				);
		pythagorianTripples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2] + ", "));
					
	}

	public static void transactionSolutions() {
		List<Trader> traders = getTraders();
		List<Transaction> transactions = getTransactions(traders);
		
		System.out.println("# All transactions:");
		transactions.forEach(System.out::println);
		System.out.println();
		
		// 1. find all transactions in the year 2011 and sort them by value (small to high)
		List<Transaction> solution1 = transactions.stream()
												  .filter(t -> t.getYear() == 2011)
												  .sorted(Comparator.comparingInt(Transaction::getValue))
												  .collect(Collectors.toList());
		
		System.out.println("#1 find all transactions in the year 2011 and sort them by value (small to high)");
		solution1.forEach(System.out::println);
		System.out.println();
		
		// 2. What are all the unique cities where the traders work?
		List<String> uniqueCities = traders.stream()
										   .map(Trader::getCity)
										   .distinct()
										   .collect(Collectors.toList());
		System.out.println("#2 What are all the unique cities where the traders work?");
		uniqueCities.forEach(System.out::println);
		System.out.println();
		
		// 2. What are all the unique cities where the traders work?
		Set<String> setOfUniqueCities = traders.stream()
										   .map(Trader::getCity)
										   .collect(Collectors.toSet());
		System.out.println("#2 What are all the unique cities where the traders work? Using Set");
		setOfUniqueCities.forEach(System.out::println);
		System.out.println();
		
		// 3. Find all traders from Pune and sort them by name
		List<Trader> puneTraders = traders.stream()
				                            .filter(t->"Pune".equals(t.getCity()))
				                            .sorted(Comparator.comparing(Trader::getName))
				                            .collect(Collectors.toList());
		System.out.println("#3 Find all traders from Pune and sort them by name");
		puneTraders.forEach(System.out::println);
		System.out.println();
		
		String puneTradersString = transactions.stream()
											   .map(transaction -> transaction.getTrader().getName())
											   .distinct()
											   .sorted()
											   .collect(Collectors.joining(","));
											   
		System.out.println("#3 Find all traders from Pune and sort them by name. Using Joining");
		System.out.println(puneTradersString);
		System.out.println();
		
		// 4. Return a string of all traders names sorted alphabetically
		List<String> sortedTraderNames = traders.stream()
											    .map(Trader::getName)
											    .sorted()
											    .collect(Collectors.toList());
											    
		System.out.println("#4 Return a string of all traders names sorted alphabetically");
		sortedTraderNames.forEach(System.out::println);
		System.out.println();
		
		// 5. Are any traders based in Satara?
		final String myCity = "Satara";
		boolean isAnyoneFromSatara = traders.stream().anyMatch(t -> myCity.equals(t.getCity()));
		System.out.println("#5 Are any traders based in " + myCity +": " + isAnyoneFromSatara );
		System.out.println();
		
		// 6. Print all transactions' values from the traders living in Mumbai
		int transactionValuesFromMumbai[] = transactions.stream()
																.filter(t -> "Mumbai".equals(t.getTrader().getCity()))
																.mapToInt(Transaction::getValue)
																.toArray();
		System.out.println("#6 Print all transactions' values from the traders living in Mumbai");
		Arrays.stream(transactionValuesFromMumbai).forEach(System.out::println);
		System.out.println();
		
		System.out.println("#7 What's the highest value of all the transactions");
		OptionalInt max = transactions.stream().mapToInt(Transaction::getValue).max();
		System.out.println("Transaction with max value: " + max.getAsInt());
		System.out.println();
		
		System.out.println("#8 Find the transaction with the smallest value");
		OptionalInt min = transactions.stream().mapToInt(Transaction::getValue).min();
		System.out.println("Transaction with min value: " + min.getAsInt());
		System.out.println();
	} 
	
	public static List<Trader> getTraders(){
		List<Trader> list = new ArrayList<>();
		Trader viraj = new Trader("Viraj", "Mumbai");
		Trader rahul = new Trader("Rahul", "Mumbai");
		Trader prakash = new Trader("Prakash", "Pune");
		Trader harshit = new Trader("Harshit", "Pune");
		Trader divesh = new Trader("Divesh", "Satara");
		list.add(viraj);
		list.add(rahul);
		list.add(prakash);
		list.add(harshit);
		list.add(divesh);
		return list;
	}
	
	public static List<Transaction> getTransactions(List<Trader> traders) {
		List<Transaction> transactions = new ArrayList<>();
		Random random = new Random();
		
		for(Trader trader:traders) {
			transactions.add(new Transaction(trader, 2010 + random.nextInt(2), random.nextInt(100000)));
			transactions.add(new Transaction(trader, 2010 + random.nextInt(2), random.nextInt(100000)));
		}
		
		return transactions;
	}

	
}
