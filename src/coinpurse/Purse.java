package coinpurse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// You will use Collections.sort() to sort the coins

/**
 * A coin purse contains coins. You can insert coins, withdraw money, check the
 * balance, and check if the purse is full.
 * 
 * @author your name
 */
public class Purse  {
	/** Collection of objects in the purse. */
	private List<Valuable> money;

	/**
	 * Capacity is maximum number of items the purse can hold. Capacity is set
	 * when the purse is created and cannot be changed.
	 */
	private final int capacity;

	/**
	 * Create a purse with a specified capacity.
	 * 
	 * @param capacity
	 *            is maximum number of coins you can put in purse.
	 */
	public Purse(int capacity) {
		this.capacity = capacity;
		money = new ArrayList<Valuable>();
	}

	/**
	 * Count and return the number of coins in the purse. This is the number of
	 * coins, not their value.
	 * 
	 * @return the number of coins in the purse
	 */
	public int count() {
		return money.size();
	}

	/**
	 * Get the total value of all items in the purse.
	 * 
	 * @return the total value of items in the purse.
	 */
	public double getBalance() {
		double balance = 0;
		for (Valuable c : money) {
			balance += c.getValue();
		}
		return balance;
	}

	/**
	 * Return the capacity of the coin purse.
	 * 
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Test whether the purse is full. The purse is full if number of items in
	 * purse equals or greater than the purse capacity.
	 * 
	 * @return true if purse is full.
	 */
	public boolean isFull() {
		if (money.size() == capacity)
			return true;
		return false;
	}

	/**
	 * Insert a coin into the purse. The coin is only inserted if the purse has
	 * space for it and the coin has positive value. No worthless coins!
	 * 
	 * @param coin
	 *            is a Coin object to insert into purse
	 * @return true if coin inserted, false if can't insert
	 */
	public boolean insert(Valuable coin) {
		// if the purse is already full then can't insert anything.
		if (!isFull() && coin.getValue() > 0) {
			money.add(coin);
			return true;
		}
		return false;
	}

	/**
	 * Withdraw the requested amount of money. Return an array of Coins
	 * withdrawn from purse, or return null if cannot withdraw the amount
	 * requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw
	 * @return array of Coin objects for money withdrawn, or null if cannot
	 *         withdraw requested amount.
	 */
	public Valuable[] withdraw(double amount) {

		/*
		 * See lab sheet for outline of a solution, or devise your own solution.
		 * The idea is to be greedy. Try to withdraw the largest coins possible.
		 * Each time you choose a coin as a candidate for withdraw, add it to a
		 * temporary list and decrease the amount (remainder) to withdraw.
		 * 
		 * If you reach a point where amountNeededToWithdraw == 0 then you found
		 * a solution! Now, use the temporary list to remove coins from the
		 * money list, and return the temporary list (as an array).
		 */

		List<Valuable> tempList = new ArrayList<Valuable>();
		double amountNeededToWithdraw = amount;

		if (amountNeededToWithdraw < 0) {
			return null;
		}
		if (amountNeededToWithdraw != 0) {
			Collections.sort(money, new Comparator<Valuable>() {

				@Override
				public int compare(Valuable o1, Valuable o2) {
					if(o1.getValue() < o2.getValue())
						return -1;
					else if (o1.getValue() > o2.getValue())
						return 1;
					return 0;
				}
			}); 
			Collections.reverse(money);
			for (Valuable c : money) {
				if (amountNeededToWithdraw >= c.getValue()) {
					tempList.add(c);
					amountNeededToWithdraw = amountNeededToWithdraw - c.getValue();
				}
			}

		}

		if (amountNeededToWithdraw == 0) {
			for (Valuable c : tempList) {
				money.remove(c);
			}
			Valuable[] array = new Valuable[tempList.size()];
			tempList.toArray(array);
			return array;
		}
		return null;
	}

	public List<Valuable> getMoney() {
		return money;
	}

	/**
	 * toString returns a string description of the purse contents. It can
	 * return whatever is a useful description.
	 */
	public String toString() {
		return count() + " coins with value " + getBalance();
	}


}
