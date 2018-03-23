package coinpurse.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coinpurse.BankNote;
import coinpurse.Coin;
import coinpurse.MalayMoneyFactory;
import coinpurse.Money;
import coinpurse.MoneyFactory;
import coinpurse.ThaiMoneyFactory;
import coinpurse.Valuable;
import coinpurse.ValueComparator;

/**
 * Test the WithdrawStrategy using JUnit. This is a JUnit 4 test suite.
 * 
 * 
 * @author Dacharat Pankong
 *
 */
public class WithdrawTest {

	private static final double TOL = 1.0E-6;
	private WithdrawStrategy strategy;
	private MoneyFactory thaiMoney;
	private List<Valuable> withdraw;
	private List<Valuable> moneyInPurse = new ArrayList<Valuable>();

	/**
	 * Sets up the test fixture.
	 */
	@Before
	public void setUp() {
		// strategy = new GreedyWithdraw();
		strategy = new RecursiveWithdraw();
		thaiMoney = new ThaiMoneyFactory();
		moneyInPurse.add(thaiMoney.createMoney(100));
		moneyInPurse.add(thaiMoney.createMoney(50));
		moneyInPurse.add(thaiMoney.createMoney(20));
		moneyInPurse.add(thaiMoney.createMoney(10));
		moneyInPurse.add(thaiMoney.createMoney(5));
	}

	/**
	 * Test withdraw max value of money in list.
	 */
	@Test
	public void withdrawEverythingFromPurse() {
		Money money = new BankNote(185, "Baht");
		withdraw = strategy.withdraw(money, moneyInPurse);
		Collections.sort(moneyInPurse);
		Collections.sort(withdraw);
		// assertEquals(withdraw, moneyInPurse);
		assertEquals(sumList(moneyInPurse), sumList(withdraw), TOL);

	}

	/**
	 * Test withdraw everything except one item.
	 */
	@Test
	public void withdrawExceptOneItemFromPurse() {
		Money money = new BankNote(180, "Baht");
		withdraw = strategy.withdraw(money, moneyInPurse);
		Collections.sort(moneyInPurse);
		Collections.sort(withdraw);
		assertEquals(sumList(moneyInPurse) - 5, sumList(withdraw), TOL);
	}

	/**
	 * Test withdraw impossible value.
	 */
	@Test
	public void withdrawMoreThanPurseBalance() {
		Money money = new BankNote(2000, "Baht");
		assertNull(strategy.withdraw(money, moneyInPurse));
	}

	/**
	 * Test withdraw different currency.
	 */
	@Test
	public void withdrawDifferentCurrency() {
		List<Valuable> moneyToWithdraw = new ArrayList<Valuable>();
		MoneyFactory thai = new ThaiMoneyFactory();
		MoneyFactory malay = new MalayMoneyFactory();
		moneyToWithdraw.add(thai.createMoney(5));
		moneyToWithdraw.add(malay.createMoney(5));
		moneyToWithdraw.add(thai.createMoney(5));
		moneyToWithdraw.add(malay.createMoney(5));
		assertNull(strategy.withdraw(new Coin(20), moneyToWithdraw));
	}

	/**
	 * Test withdraw possible value (In greedy cannot withdraw, but recursive can).
	 */
	@Test
	public void possibleWithdraw() {
		List<Valuable> moneyToWithdraw = new ArrayList<Valuable>();
		moneyToWithdraw.add(thaiMoney.createMoney(5));
		moneyToWithdraw.add(thaiMoney.createMoney(2));
		moneyToWithdraw.add(thaiMoney.createMoney(2));
		moneyToWithdraw.add(thaiMoney.createMoney(2));

		// In greedy cannot withdraw
		WithdrawStrategy greedy = new GreedyWithdraw();
		assertNull(greedy.withdraw(new Coin(6), moneyToWithdraw));

		// In recursive can withdraw.
		withdraw = strategy.withdraw(new Coin(6), moneyToWithdraw);
		assertEquals(sumList(withdraw), 6, TOL);
	}

	/**
	 * Test that there are many solution to withdraw money(greedy and recursive will
	 * return different list).
	 */
	@Test
	public void manySolutionWithdraw() {

		List<Valuable> moneyToWithdraw = new ArrayList<Valuable>();
		moneyToWithdraw.add(thaiMoney.createMoney(2));
		moneyToWithdraw.add(thaiMoney.createMoney(5));
		moneyToWithdraw.add(thaiMoney.createMoney(1));
		moneyToWithdraw.add(thaiMoney.createMoney(2));
		moneyToWithdraw.add(thaiMoney.createMoney(2));

		// withdraw by recursive withdraw
		withdraw = strategy.withdraw(new Coin(6), moneyToWithdraw);

		List<Valuable> moneyRecurMustWithdraw = new ArrayList<Valuable>();
		moneyRecurMustWithdraw.add(thaiMoney.createMoney(2));
		moneyRecurMustWithdraw.add(thaiMoney.createMoney(2));
		moneyRecurMustWithdraw.add(thaiMoney.createMoney(2));

		assertEquals(withdraw, moneyRecurMustWithdraw);

		// withdraw by greedy withdraw
		Collections.sort(moneyToWithdraw, new ValueComparator());
		Collections.reverse(moneyToWithdraw);
		WithdrawStrategy greedy = new GreedyWithdraw();
		List<Valuable> greedyList = greedy.withdraw(new Coin(6), moneyToWithdraw);

		List<Valuable> moneyGreedyMustWithdraw = new ArrayList<Valuable>();
		moneyGreedyMustWithdraw.add(thaiMoney.createMoney(5));
		moneyGreedyMustWithdraw.add(thaiMoney.createMoney(1));

		assertEquals(greedyList, moneyGreedyMustWithdraw);

	}

	/**
	 * Sum value in list
	 * 
	 * @param money
	 *            is list to sum.
	 * @return sum value of money.
	 */
	public double sumList(List<Valuable> money) {
		double sum = 0;
		for (Valuable v : money) {
			sum += v.getValue();
		}
		return sum;
	}

}
