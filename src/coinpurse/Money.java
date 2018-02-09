package coinpurse;

/**
 * Money class to be a superclass of BankNote and Coin classes.
 * 
 * @author Dacharat Pankong
 *
 */
public class Money implements Valuable {

	protected double value;
	protected String currency;

	/**
	 * Create money that have value and currency of money.
	 * 
	 * @param value of money
	 * @param currency of money
	 */
	public Money(double value, String currency) {
		this.value = value;
		this.currency = currency;
	}

	/**
	 * return value of money
	 *  
	 * @return value of money
	 */
	@Override
	public double getValue() {
		return value;
	}

	/**
	 * return currency of money
	 * 
	 * @return currency of money
	 */
	@Override
	public String getCurrency() {
		return currency;
	}

	/**
	 * Compare value of coin
	 * 
	 */

	@Override
	public int compareTo(Valuable o) {
		if(this.getCurrency().compareToIgnoreCase(o.getCurrency()) == 0) 
			return Double.compare(this.getValue(), o.getValue());
		return this.getCurrency().compareToIgnoreCase(o.getCurrency());
	}

	/**
	 * check coin that they are equal or not
	 * 
	 * @return true if they are same.
	 * 			false if they are not same.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		
		Money m = (Money) obj;
		return this.getValue() == m.getValue() && this.getCurrency().equals(m.getCurrency());
	}
}