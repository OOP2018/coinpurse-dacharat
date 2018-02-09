package coinpurse;

import java.util.ArrayList;

/**
 * Coin represent coinage (money) with a fixed value and currency.
 * 
 * @author Dacharat Pankong
 *
 */
public class Coin extends Money {

	/**
	 * Create a coin with value
	 * 
	 * @param value
	 *            is value of money
	 */
	public Coin(double value) {

		super(value, "Baht");
	}

	/**
	 * Create a coin with value and currency
	 * 
	 * @param value
	 *            is value of money
	 * @param currency
	 *            is currency of money
	 */
	public Coin(double value, String currency) {

		super(value, currency);
	}

	/**
	 * toString returns a string description of coin.
	 */
	@Override
	public String toString() {
		if (currency.equals("Ringgit"))
			return String.format("%.2f - %s coin", value * 100, "Sen");
		return String.format("%.2f - %s coin", value, currency);
	}

}
