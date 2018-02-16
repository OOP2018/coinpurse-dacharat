package coinpurse;

/**
 * MoneyFactory class for create country money.
 * 
 * @author Dacharat Pankong
 *
 */
public abstract class MoneyFactory {

	private static MoneyFactory factory = null;
	protected static long nextSerialNumber = 1000000;

	/**
	 * Constructor for MoneyFactory.
	 */
	protected MoneyFactory() {

	}

	/**
	 * Get an instance of MoneyFactory. This method returns an object of a subclass
	 * (such as ThaiMoneyFactor). The instance is a Singleton -- it always returns
	 * the same object.
	 * 
	 * @return object of a subclass
	 */
	public static MoneyFactory getInstance() {
		// if (factory == null) {
		// factory = new ThaiMoneyFactory();
		// }
		return factory;
	}

	/**
	 * Create new money object in the local currency. If the value is not a valid
	 * currency amount, then throw IllegalArgumentException.
	 * 
	 * @param value
	 *            of money
	 * @return money
	 */
	public abstract Valuable createMoney(double value);

	/**
	 * Accepts money value as a String, e.g. createMoney("10"). This method is for
	 * convenience of the U.I. If value is not a valid number, then throw
	 * IllegalArgumentException.
	 * 
	 * @param value
	 *            of money
	 * @return money
	 */
	public Valuable createMoney(String value) {
		double values = 0;
		try {
			values = Double.parseDouble(value);
		} catch (IllegalArgumentException e) {
			System.out.println("Sorry, " + value + " is not a valid amount.");
		}
		return this.createMoney(values);
	}

	/**
	 * Set type of money (What country).
	 * 
	 * @param mf
	 *            type of money
	 */
	public static void setMoneyFactory(MoneyFactory mf) {
		factory = mf;
	}
}
