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
 * @author Dacharat Pankong
 */
public class Purse {
	/** Collection of objects in the purse. */
	private List<Valuable> money;
	private Comparator<Valuable> com = new ValueComparator();

	/**
	 * Capacity is maximum number of items the purse can hold. Capacity is set when
	 * the purse is created and cannot be changed.
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
		for (Valuable v : money) {
			balance += v.getValue();
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
	 * Test whether the purse is full. The purse is full if number of items in purse
	 * equals or greater than the purse capacity.
	 * 
	 * @return true if purse is full.
	 */
	public boolean isFull() {
		return money.size() == capacity;
	}

	/**
	 * Insert a coin or banknote into the purse. The money is only inserted if the
	 * purse has space for it and the money has positive value. No worthless coins!
	 * 
	 * @param value
	 *            is a Coin or Banknote object to insert into purse
	 * @return true if coin inserted, false if can't insert
	 */
	public boolean insert(Valuable value) {
		// if the purse is already full then can't insert anything.
		if (!isFull() && value.getValue() > 0) {
			money.add(value);
			return true;
		}
		return false;
	}

	/**
	 * Withdraw the requested amount of money. Return an array of Coins withdrawn
	 * from purse, or return null if cannot withdraw the amount requested.
	 * 
	 * @param amount
	 *            is the amount to withdraw
	 * @return array of Money objects for money withdrawn, or null if cannot withdraw
	 *         requested amount.
	 */
	public Valuable[] withdraw(double amount) {

		/*
		 * See lab sheet for outline of a solution, or devise your own solution. The
		 * idea is to be greedy. Try to withdraw the largest coins possible. Each time
		 * you choose a coin as a candidate for withdraw, add it to a temporary list and
		 * decrease the amount (remainder) to withdraw.
		 * 
		 * If you reach a point where amountNeededToWithdraw == 0 then you found a
		 * solution! Now, use the temporary list to remove coins from the money list,
		 * and return the temporary list (as an array).
		 */

		Money m = new Money(amount,	"Baht");
		return withdraw(m);
		
	}

	/**
	 * Withdraw the amount, using only items that have the same currency as the
	 * parameter(amount). Amount must not be null and amount.getValue() > 0;
	 * 
	 * @param amount is the amount to withdraw with same currency.
	 * @return array of Money objects for money withdrawn, or null if cannot withdraw
	 *         requested amount.
	 */
	public Valuable[] withdraw(Valuable amount) {

		List<Valuable> tempList = new ArrayList<Valuable>();
		double amountNeededToWithdraw = amount.getValue();

		if (amount == null || amountNeededToWithdraw <= 0)
			return null;
		else {
			Collections.sort(money, com);
			Collections.reverse(money);
			for (Valuable v : money) {
				if (amountNeededToWithdraw >= v.getValue() && amount.getCurrency().equals(v.getCurrency())) {
					tempList.add(v);
					amountNeededToWithdraw = amountNeededToWithdraw - v.getValue();
				}
			}
		}
		if (amountNeededToWithdraw == 0) {
			for (Valuable v : tempList) {
				money.remove(v);
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
	 * toString returns a string description of the purse contents. It can return
	 * whatever is a useful description.
	 */
	public String toString() {
		return count() + " coins with value " + getBalance();
	}

}
