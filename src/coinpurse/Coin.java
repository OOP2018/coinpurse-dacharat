package coinpurse;

import java.util.ArrayList;

/**
 * Coin represent coinage (money) with a fixed value and currency.
 * 
 * @author Dacharat Pankong
 *
 */
public class Coin implements Comparable<Coin> {

	private double value;
	private String currency;

	/**
	 * Create a coin with value
	 * 
	 * @param value is value of money
	 */
	public Coin(double value) {

		if (value >= 0) {
			this.value = value;
		} else {
			this.value = 0;
		}
	}

	/**
	 * Create a coin with value and currency
	 * 
	 * @param value is value of money
	 * @param currency is currency of money
	 */
	public Coin(double value, String currency) {

		if (value >= 0) {
			this.value = value;
		} else {
			this.value = 0;
		}
		this.currency = currency;
	}

	/**
	 * return value of money
	 *  
	 * @return value of money
	 */
	public double getValue() {
		return value;
	}

	/**
	 * return currency of money
	 * 
	 * @return currency of money
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * check coin that they are equal or not
	 * 
	 * @return true if they are same.
	 * 			false if they are not same.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		Coin other = (Coin) obj;
		return other.getValue() == this.getValue() && other.getCurrency().equals(this.getCurrency());

	}

	/**
	 * Compare value of coin
	 * 
	 */
	@Override
	public int compareTo(Coin coin) {

		return (int) Math.signum(this.getValue() - coin.getValue());
	}

	/**
	 * toString returns a string description of coin.
	 */
	@Override
	public String toString() {
		return String.format("%f - %s", value, currency);
	}

}
