package ocp.inaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


public class Chapter6CollectorsDemo {

	public static void main(String args[]) {
		List<Transaction> transactions = getTransactions();
		transactionsStats(transactions);
	}
	
	public static void groupTransactionsByCurrency(List<Transaction> transactions){
		Map<Currency, List<Transaction>> transactionsByCurrency = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
		transactionsByCurrency.keySet().stream().forEach(key -> System.out.println(transactionsByCurrency.get(key)));
	}
	
	public static void sumTransactionValuesByCurrency(List<Transaction> transactions) {
		Map<Currency, Double> totalTransactionValuesPerCurrency = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency, Collectors.summingDouble(Transaction::getValue)));
		totalTransactionValuesPerCurrency.forEach((k,v) -> System.out.println("Total transaction value for " + k + " is " + v));
	}
	
	public static void transactionsStats(List<Transaction> transactions) {
//		Long howManyTransactions = transactions.stream().collect(Collectors.counting());
		System.out.println("All transactions...");
		transactions.forEach(System.out::println);
		System.out.println();
		
//		Long howManyTransactions = transactions.stream().count();
		Long howManyTransactions = transactions.stream().collect(Collectors.reducing(0L, e -> 1L, Long::sum));
		System.out.println("Total transactions : " + howManyTransactions);
		
//		Double sumOfAll = transactions.stream().collect(Collectors.summingDouble(Transaction::getValue));
		double sumOfAll = transactions.stream().collect(Collectors.reducing(0.0, Transaction::getValue, Double::sum));
		System.out.println("Sum of all transaction values: " + sumOfAll);
		
		Double averageOfAll = transactions.stream().collect(Collectors.averagingDouble(Transaction::getValue));
		System.out.println("Average of all transaction values: " + averageOfAll);
		
		Comparator<Transaction> valueComparator = Comparator.comparing(Transaction::getValue);
//		Optional<Transaction> highestTransactionValue = transactions.stream().collect(Collectors.maxBy(valueComparator));
		Optional<Transaction> highestTransactionValue = transactions.stream().collect(Collectors.reducing((Transaction t1, Transaction t2) -> t1.getValue()>t2.getValue()?t1:t2));
//		System.out.println("Highest Transaction Value: " + highestTransactionValue);
		
		Optional<Transaction> lowestTransactionValue = transactions.stream().collect(Collectors.minBy(valueComparator));
		System.out.println("Lowest Transaction Value: " + lowestTransactionValue);
		
		DoubleSummaryStatistics stats = transactions.stream().collect(Collectors.summarizingDouble(Transaction::getValue));
		System.out.println("Transaction values statistics : " + stats);
	}
	
	public static List<Transaction> getTransactions() {
		List<Transaction> transactions = new ArrayList<>();
		Random random = new Random();
		Currency[] currencies = Currency.values();
		for(int i = 0; i < currencies.length; i++) {
			transactions.add(new Transaction(currencies[i], random.nextInt(100000)));
			transactions.add(new Transaction(currencies[i], random.nextInt(100000)));
		}
		
		return transactions;
	}
	
	public static class Transaction {
        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }

}
