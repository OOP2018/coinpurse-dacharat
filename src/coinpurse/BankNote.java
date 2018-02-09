package coinpurse;

/**
 * BankNote will use when money insert more than or equal 20.
 * 
 * @author Dacharat Pankong
 *
 */
public class BankNote extends Money  {

	private static long nextSerialNumber = 1000000;
	private long serialNumber;
	
	/**
	 * Create banknote for collect money.
	 * 
	 * @param value
	 * @param currency
	 */
	public BankNote(double value, String currency) {

		super(value, currency);
		this.serialNumber = nextSerialNumber++;
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
	 * toString returns a string description of banknote. 
	 */
	@Override
	public String toString() {
		return value + "-" + currency + " note [" + serialNumber + "]";
	}

}
