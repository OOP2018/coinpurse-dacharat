package coinpurse;

import java.util.ArrayList;

/**
 * Coin represent coinage (money) with a fixed value and currency.
 * 
 * @author Dacharat Pankong
 *
 */
public class Coin extends Money {

	private String outputCurrency;

	/**
	 * Create a coin with value
	 * 
	 * @param value
	 *            is value of money
	 */
	public Coin(double value) {

		super(value, "Baht");
		this.outputCurrency = "Baht";
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
		this.outputCurrency = currency;
	}

	/**
	 * Create a coin with value, currency and if you want to make output currency in
	 * other currency but in same country, you can add outputCurrency for make it
	 * output the currency that you want.(Ex. In thai have "Satang" currency for
	 * money that have value < 1 baht)
	 * 
	 * @param value
	 * @param currency
	 * @param outputCurrency
	 */
	public Coin(double value, String currency, String outputCurrency) {
		super(value, currency);
		this.outputCurrency = outputCurrency;
	}

	/**
	 * toString returns a string description of coin.
	 */
	@Override
	public String toString() {
		if (currency.equalsIgnoreCase(outputCurrency))
			return String.format("%.2f - %s coin", value, currency);
		return String.format("%.2f - %s coin", value * 100, outputCurrency);
	}

}
