package coinpurse.strategy;

import java.util.List;

import coinpurse.Valuable;

/**
 * 
 * 
 * @author Dacharat Pankong
 *
 */
public interface WithdrawStrategy {

	/**
	 * Find and return items from a collection whose total value equals the
	 * requested amount.
	 * 
	 * @param amount is the amount of money to withdraw, with currency.
	 * @param valuables
	 * @return
	 */
	public List<Valuable> withdraw(Valuable amount, List<Valuable> valuables);

}
