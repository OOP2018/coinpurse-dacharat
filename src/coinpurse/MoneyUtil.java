package coinpurse;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MoneyUtil {

	/**
	 * Check money in purse that they have this currency or not if some coin is not remove it.
	 * 
	 * @param coins in purse
	 * @param currency of money
	 * @return coin with all same currency
	 */
	public static List<Coin> filterByCurrency(List<Coin> coins, String currency) {
		for(Coin c : coins) {
			if(c.getCurrency() != currency)
				coins.remove(c);
		}
		return coins;
	}
	
	/**
	 * print coin in purse
	 * 
	 * @param coins
	 */
	public static void printList(List<Coin> coins) {
		for(Coin c: coins) {
			System.out.println(c);
		}
	}
	
	/**
	 * Sort coin that minimum value will come first
	 * 
	 * @param coins
	 */
	public static void sortCoins(List<Coin> coins) {
		Collections.sort(coins);
		printList(coins);
	}
	
	public static void main(String[] args) {
		Purse p = new Purse(5);
		p.insert(new Coin(10,"Baht"));
		p.insert(new Coin(5, "Baht"));
		p.insert(new Coin(1, "a"));
		p.insert(new Coin(5, "Baht"));
		filterByCurrency(p.getMoney(), "Baht");
		sortCoins(p.getMoney());
	}
}
