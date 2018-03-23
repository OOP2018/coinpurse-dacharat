package coinpurse.strategy;

import java.util.ArrayList;
import java.util.List;

import coinpurse.Valuable;

/**
 * Withdraw money using greedy algorithm.
 * 
 * @author Dacharat Pankong
 *
 */
public class GreedyWithdraw implements WithdrawStrategy {

	/**
	 * Find and return items from a collection whose total value equals the
	 * requested amount.
	 */
	@Override
	public List<Valuable> withdraw(Valuable amount, List<Valuable> valuables) {
		List<Valuable> tempList = new ArrayList<Valuable>();
		double amountNeededToWithdraw = amount.getValue();
		if (amount == null || amountNeededToWithdraw <= 0)
			return null;
		else {
//			Collections.sort(valuables);
//			Collections.reverse(valuables);
			for (Valuable v : valuables) {
				if (amountNeededToWithdraw >= v.getValue() && amount.getCurrency().equals(v.getCurrency())) {
					tempList.add(v);
					amountNeededToWithdraw = amountNeededToWithdraw - v.getValue();
				}
			}
		}
		if (amountNeededToWithdraw == 0)
			return tempList;
		return null;
	}

}
