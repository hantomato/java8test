package java8.stream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test1 {
	
	public static void main(String... args) {
//		Path path = Paths.get("");
//		try ( Stream<String> lines = Files.lines(path)) {
//			
//		}

		List<Transaction> transactions = new ArrayList<>();			// input
		List<Transaction> groceryTransactions = new ArrayList<>();
		for (Transaction t : transactions) {
			groceryTransactions.add(t);
		}
		
		
		Collections.sort(groceryTransactions, new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
//				public int compare(Transaction t1, Transaction t2) {
				return 0;
			}
		});
		
		// 2. sort
		
		List<Integer> transacitonIds = new ArrayList<>();			// output
		for (Transaction t : groceryTransactions) {
			transacitonIds.add(t.getId());
		}
		
		
		// SELECT id FROM transactions
		// WHERE type == GROCERY
		// ORDER BY value

		
		List<Integer> transactionIds =
				transactions.stream()									// from
				.filter(t -> t.getType() == Transaction.GROCERY)		// where
//				.sorted(comparing(Transaction::getValue).reserved())	// order by
				.sorted()
				.map(Transaction::getId)								// select it
				.collect(Collectors.toList());

		
		
	}
	
	public static void test() {
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		List<Integer> twoEvenSquares = 
				numbers.stream()
				.filter(n -> true)
				.collect(Collectors.toList());
	}
	
	public static class Transaction {
		public static int GROCERY = 0;

		public int getType() 	{	return 0; 	}
		public int getValue() 	{ 	return 0;	}
		public int getId() 		{	return 0; 	}
	}

}
