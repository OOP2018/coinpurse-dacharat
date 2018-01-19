package coinpurse;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MoneyUtil {

	public static List<Coin> filterByCurrency(List<Coin> coins, String currency) {
		for(Coin c : coins) {
			if(c.getCurrency() != currency)
				coins.remove(c);
		}
		return coins;
	}
	
	public static void printList(List<Coin> coins) {
		for(Coin c: coins) {
			System.out.println(c);
		}
	}
	
	public static void sortCoins(List<Coin> coins) {
		Collections.sort(coins);
		printList(coins);
	}
	
	public static void main(String[] args) {
		Purse p = new Purse(5);
		p.insert(new Coin(10));
		p.insert(new Coin(5));
		p.insert(new Coin(1));
		p.insert(new Coin(5));
		sortCoins(p.getMoney());
	}
}
