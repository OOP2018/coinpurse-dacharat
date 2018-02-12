package coinpurse;

/**
 * MalayMoneyFactory class contain value and currency of Malaysia.
 * 
 * @author Dacharat Pankong
 *
 */
public class MalayMoneyFactory extends MoneyFactory {

	/**
	 * Create new money object in the Malaysia currency. If the value is not a valid
	 * currency amount, then throw IllegalArgumentException.
	 */
	@Override
	public Valuable createMoney(double value) {

		Valuable valuable;
		String currency = "Ringgit";
		String outputCurrency = "Sen";
		if (value == 0.05 || value == 0.1 || value == 0.2 || value == 0.5) {
			valuable = new Coin(value, currency, outputCurrency);
		} else if (value == 1 || value == 2 || value == 5 || value == 10 || value == 20 || value == 50 || value == 100)
			valuable = new BankNote(value, currency, nextSerialNumber++);
		else
			throw new IllegalArgumentException();
		return valuable;
	}

}
