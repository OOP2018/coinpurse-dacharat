package coinpurse.strategy;

import java.util.ArrayList;
import java.util.List;

import coinpurse.Money;
import coinpurse.Valuable;

public class RecursiveWithdraw implements WithdrawStrategy {

	@Override
	public List<Valuable> withdraw(Valuable amount, List<Valuable> valuables) {

		if (amount.getValue() == 0)
			return new ArrayList<Valuable>();
		if (valuables.size() <= 0)
			return null;

		Valuable first = valuables.get(0);
		if (first.getCurrency().equals(amount.getCurrency())) {
			Valuable remaining = new Money(amount.getValue() - first.getValue(), amount.getCurrency());
			List<Valuable> result = withdraw(remaining, valuables.subList(1, valuables.size()));
			// Add
			if (result != null) {
				result.add(first);
				return result;
			}
			// Skip
			result = withdraw(amount, valuables.subList(1, valuables.size()));
			if (result != null) {
				return result;
			}
		}
		return null;
	}

}
