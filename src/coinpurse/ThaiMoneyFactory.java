package coinpurse;

/**
 * ThaiMoneyFactory class contain value and currency of Thailand.
 * 
 * @author Dacharat Pankong
 *
 */
public class ThaiMoneyFactory extends MoneyFactory {

	/**
	 * Create new money object in the Thailand currency. If the value is not a valid
	 * currency amount, then throw IllegalArgumentException.
	 */
	@Override
	public Valuable createMoney(double value) {
		Valuable valuable;
		String currency = "Baht";
		String outputCurrency = "Satang";
		if (value == 0.5 || value == 0.25)
			valuable = new Coin(value, currency, outputCurrency);
		else if (value == 1 || value == 2 || value == 5 || value == 10)
			valuable = new Coin(value, currency);
		else if (value == 20 || value == 50 || value == 100 || value == 500 || value == 1000)
			valuable = new BankNote(value, currency);
		else
			throw new IllegalArgumentException();
		return valuable;
	}

}
