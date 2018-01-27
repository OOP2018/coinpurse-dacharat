package coinpurse;

/**
 * BankNote will use when money insert more than or equal 20.
 * 
 * @author Dacharat Pankong
 *
 */
public class BankNote implements Valuable {

	private static long nextSerialNumber = 1000000;
	private double value;
	private String currency;
	private long serialNumber;
	
	/**
	 * Create banknote for collect money.
	 * 
	 * @param value
	 * @param currency
	 */
	public BankNote(double value, String currency) {

		this.value = value;
		this.currency = currency;
		this.serialNumber = nextSerialNumber++;
	}

	/**
	 * Return value of banknote.
	 */
	@Override
	public double getValue() {
		return value;
	}

	/**
	 * Return currency of banknote.
	 */
	@Override
	public String getCurrency() {
		return currency;
	}

	/**
	 * Return serial number of banknote.
	 * 
	 * @return serial number of banknote.
	 */
	public long getSerial() {
		return serialNumber;
	}

	/**
	 * Check banknote that they are equal or not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		BankNote other = (BankNote) obj;
		return this.value == other.value && this.currency == other.currency;
	}

	/**
	 * toString returns a string description of banknote. 
	 */
	@Override
	public String toString() {
		return value + "-" + currency + " note [" + serialNumber + "]";
	}

}
