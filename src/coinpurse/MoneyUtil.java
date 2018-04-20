package coinpurse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Some Money utility methods for practice using Lists and Comparator.
 * 
 * @author Dacharat Pankong
 *
 */
public class MoneyUtil {

	/**
	 * Check money in purse that they have this currency or not if some money is not
	 * remove it.
	 * 
	 * @param money
	 *            in purse
	 * @param currency
	 *            of money
	 * @return coin with all same currency
	 */
	public static <E extends Valuable> List<E> filterByCurrency(List<E> money, String currency) {
		List<E> c = new ArrayList<E>();
		for (E coin : money) {
			if (coin.getCurrency().equals(currency))
				c.add(coin);
		}
		return c;
	}

	/**
	 * print money in purse
	 * 
	 * @param money
	 */
	public static void printList(List<? extends Valuable> money) {
		for (Valuable m : money) {
			System.out.println(m);
		}
	}

	/**
	 * Sort money that minimum value will come first
	 * 
	 * @param money
	 */
	public static void sortMoney(List<? extends Valuable> money) {
		Comparator<Valuable> com = new ValueComparator();
		Collections.sort(money, com);
		printList(money);
	}

	/**
	 * Return the larger argument, based on sort order, using the objects' own
	 * compareTo method for comparing.
	 * 
	 * @param args
	 *            one or more Comparable objects to compare.
	 * @return the argument that would be last if sorted the elements.
	 * @throws IllegalArgumentException
	 *             if no arguments given.
	 */
	public static <E extends Comparable<? super E>> E max(E... args) throws IllegalArgumentException {

		E max = args[0];
		for (int i = 0; i < args.length; i++) {
			if (args[i].compareTo(max) > 0)
				max = args[i];
		}

		return max;
	}

	public static void main(String[] args) {
		// Purse p = new Purse(5);
		// p.insert(new Coin(10,"Baht"));
		// p.insert(new Coin(5, "b"));
		// p.insert(new Coin(1, "a"));
		// p.insert(new Coin(5, "Baht"));
		// filterByCurrency(p.getMoney(), "Baht");
		// sortCoins(filterByCurrency(p.getMoney(), "Baht"));
		String max = MoneyUtil.max("dog", "aebra", "cat");
		System.out.println(max);

		System.out.println("------------------------------------------");
		Money m1 = new BankNote(100, "Baht");
		Money m2 = new BankNote(500, "Baht");
		Money m3 = new Coin(20, "Baht");
		Money maxMoney = MoneyUtil.max(m1, m2, m3);
		System.out.println(maxMoney);

		System.out.println("------------------------------------------");
		List<BankNote> list = new ArrayList<BankNote>();
		list.add(new BankNote(10, "USD"));
		list.add(new BankNote(500, "Baht"));
		MoneyUtil.sortMoney(list);

		System.out.println("------------------------------------------");
		List<Coin> coins = Arrays.asList(new Coin(5, "Baht"), new Coin(0.1, "Ringgit"), new Coin(5, "Cent"));
		List<Coin> result = MoneyUtil.filterByCurrency(coins, "Baht");
		printList(result);
	}
}
