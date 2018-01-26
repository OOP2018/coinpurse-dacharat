package coinpurse;

public class BankNote implements Valuable {

	private static long nextSerialNumber = 1000000;
	private double value;
	private String currency;
	private long serialNumber;

	public BankNote(double value, String currency) {

		this.value = value;
		this.currency = currency;
		this.serialNumber = nextSerialNumber;
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public String getCurrency() {
		return currency;
	}

	public long getSerial() {
		return serialNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		BankNote other = (BankNote) obj;
		return this.value == other.value && this.currency == other.currency;
	}

	@Override
	public String toString() {
		return value + "-" + currency + " note [" + serialNumber + "]";
	}

}
