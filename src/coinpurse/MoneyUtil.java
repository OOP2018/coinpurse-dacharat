package coinpurse;

import java.util.ArrayList;
import java.util.Collection;
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
	 * Check money in purse that they have this currency or not if some coin is not remove it.
	 * 
	 * @param coins in purse
	 * @param currency of money
	 * @return coin with all same currency
	 */
	public static List<Valuable> filterByCurrency(List<Valuable> coins, String currency) {
		List<Valuable> c = new ArrayList<Valuable>(); 
		for(Valuable coin : coins) {
			if(coin.getCurrency().equals(currency))
				c.add(coin);
		}
		return c;
	}
	
	/**
	 * print coin in purse
	 * 
	 * @param coins
	 */
	public static void printList(List<Valuable> coins) {
		for(Valuable c: coins) {
			System.out.println(c);
		}
	}
	
	/**
	 * Sort coin that minimum value will come first
	 * 
	 * @param coins
	 */
	public static void sortCoins(List<Valuable> coins) {
		Collections.sort(coins, new Comparator<Valuable>() {

			@Override
			public int compare(Valuable o1, Valuable o2) {
				if(o1.getValue() < o2.getValue())
					return -1;
				else if(o1.getValue() > o2.getValue())
					return 1;
				return 0;
			}
		});
		printList(coins);
	}
	
	public static void main(String[] args) {
		Purse p = new Purse(5);
//		p.insert(new Coin(10,"Baht"));
//		p.insert(new Coin(5, "b"));
//		p.insert(new Coin(1, "a"));
//		p.insert(new Coin(5, "Baht"));
		filterByCurrency(p.getMoney(), "Baht");
		sortCoins(filterByCurrency(p.getMoney(), "Baht"));
	}
}
