package coinpurse;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MoneyFactoryTest {

	private static final double TOL = 1.0E-6;

	private MoneyFactory mf;

	/**
	 * Sets up the test fixture. Called before every test method.
	 */
	@Before
	public void setUp() {
		MoneyFactory.setMoneyFactory(new ThaiMoneyFactory());
		mf = MoneyFactory.getInstance();
	}

	/** Insert some coins. Easy test. */
	@Test
	public void testInsert() {
		Purse purse = new Purse(3);
		assertTrue(purse.insert(mf.createMoney(5)));
		assertTrue(purse.insert(mf.createMoney(10)));
		assertTrue(purse.insert(mf.createMoney(1)));
		assertEquals(3, purse.count());
		// purse is full so insert should fail
		assertFalse(purse.insert(mf.createMoney(1)));
	}

	@Test(timeout = 1000)
	public void testIsFull() { // borderline case (capacity 1)
		Purse purse = new Purse(1);
		assertFalse(purse.isFull());
		purse.insert(mf.createMoney(1));
		assertTrue(purse.isFull());
		// real test
		int capacity = 4;
		purse = new Purse(capacity);
		for (int k = 1; k <= capacity; k++) {
			assertFalse(purse.isFull());
			purse.insert(mf.createMoney(5));
		}
		// should be full now
		assertTrue(purse.isFull());
		assertFalse(purse.insert(mf.createMoney(5)));
	}

	/**
	 * Should be able to insert same coin many times, since spec doesn't say
	 * anything about this.
	 */
	@Test(timeout = 1000)
	public void testInsertSameCoin() {
		int capacity = 5;
		double value = 10.0;
		Purse purse = new Purse(capacity);
		assertTrue(purse.insert(mf.createMoney(value)));
		assertTrue(purse.insert(mf.createMoney(value))); // should be allowed
		assertTrue(purse.insert(mf.createMoney(value))); // should be allowed
		assertTrue(purse.insert(mf.createMoney(value))); // should be allowed
		assertTrue(purse.insert(mf.createMoney(value))); // should be allowed
		assertEquals(purse.getBalance(), 5 * value, TOL);
	}

	/** Add one coin and remove it. */
	@Test(timeout = 1000)
	public void testEasyWithdraw() {
		Purse purse = new Purse(10);
		double[] values = { 1, 20, 0.5, 10 }; // values of coins we will insert

		for (double value : values) {
			Valuable valuable = mf.createMoney(value);
			assertTrue(purse.insert(valuable));
			assertEquals(value, purse.getBalance(), TOL);
			Valuable[] result = purse.withdraw(value);
			assertTrue(result != null);
			assertEquals(1, result.length);
			assertSame(valuable, result[0]); // should be same object
			assertEquals(0, purse.getBalance(), TOL);
		}
	}

	/** Add 4 coins and then withdraw in pairs, but not in same order. */
	@Test(timeout = 1000)
	public void testMultiWithdraw() {
		Purse purse = new Purse(10);
		Valuable[] valuable = { mf.createMoney(5), mf.createMoney(10.0), mf.createMoney(1.0), mf.createMoney(5.0) };
		// insert them all
		for (Valuable v : valuable)
			assertTrue(purse.insert(v));

		double amount1 = valuable[1].getValue() + valuable[3].getValue();
		double amount2 = valuable[0].getValue() + valuable[2].getValue();
		assertEquals(amount1 + amount2, purse.getBalance(), TOL);

		Valuable[] wd1 = purse.withdraw(amount1);
		assertEquals(amount1, sum(wd1), TOL);
		assertEquals(amount2, purse.getBalance(), TOL);

		Valuable[] wd2 = purse.withdraw(amount2);
		assertEquals(amount2, sum(wd2), TOL);
		// should be empty now
		assertEquals(0, purse.getBalance(), TOL);
	}

	/** Withdraw full amount in purse, using varying numbers of objects. */
	@Test(timeout = 1000)
	public void testWithdrawEverything() {
		Purse purse = new Purse(10);
		// Coins we want to insert and then withdraw.
		// Use values such that greedy will succeed, but not monotonic
		List<Valuable> valuable = Arrays.asList(mf.createMoney(1.0), mf.createMoney(0.5), mf.createMoney(10.0),
				mf.createMoney(0.25), mf.createMoney(5.0));
		// num = number of coins to insert and then withdraw
		for (int num = 1; num <= valuable.size(); num++) {
			double amount = 0.0;
			List<Valuable> subList = valuable.subList(0, num);
			for (Valuable v : subList) {
				purse.insert(v);
				amount += v.getValue();
			}
			// balance should be exactly what we just inserted
			// can we withdraw it all?
			Valuable[] result = purse.withdraw(amount);
			String errmsg = String.format("couldn't withdraw %.2f but purse has %s", amount,
					Arrays.toString(subList.toArray()));
			assertNotNull(errmsg, result);
			// is the amount correct?
			assertEquals("Withdraw wrong amount", amount, sum(result), TOL);
			// should not be anything left in the purse
			assertEquals(0.0, purse.getBalance(), TOL);
		}
	}

	@Test(timeout = 1000)
	public void testImpossibleWithdraw() {
		Purse purse = new Purse(10);
		assertNull(purse.withdraw(1));
		purse.insert(mf.createMoney(20));
		assertNull(purse.withdraw(1));
		assertNull(purse.withdraw(19));
		assertNull(purse.withdraw(21));
		purse.insert(mf.createMoney(20)); // now it has 20 + 20
		assertNull(purse.withdraw(30));
	}

	@Test(timeout = 1000)
	public void testSingletom() {
		MoneyFactory mf1 = MoneyFactory.getInstance();
		MoneyFactory mf2 = MoneyFactory.getInstance();
		assertTrue(mf1 == mf2);
	}

	@Test(timeout = 1000)
	public void testSatangCurrency() {
		Purse p = new Purse(5);
		p.insert(mf.createMoney(0.25));
		p.insert(mf.createMoney(0.5));
		for (Valuable v : p.getMoney()) {
			assertEquals("Baht", v.getCurrency());
		}
	}

	@Test(timeout = 1000)
	public void testCointoString() {
		Valuable v1 = mf.createMoney(5);
		assertEquals("5.00 - Baht coin", v1.toString());
		Valuable v2 = mf.createMoney(0.5);
		assertEquals("50.00 - Satang coin", v2.toString());
	}

	@Test(timeout = 1000)
	public void testSerialNumber() {
		Valuable v1 = mf.createMoney(50);
		Valuable v2 = mf.createMoney(50);
		Valuable v3 = mf.createMoney(50);
		Valuable v4 = mf.createMoney(50);
		Valuable v5 = mf.createMoney(50);
		
		assertNotEquals(v1.toString(), v2.toString());
		assertNotEquals(v2.toString(), v3.toString());
		assertNotEquals(v3.toString(), v4.toString());
		assertNotEquals(v4.toString(), v5.toString());
		assertNotEquals(v5.toString(), v1.toString());

	}

	/**
	 * Sum the value of some coins.
	 * 
	 * @param coins
	 *            array of coins
	 * @return sum of values of the coins
	 */
	private double sum(Valuable[] coins) {
		if (coins == null)
			return 0.0;
		double sum = 0;
		for (Valuable c : coins)
			if (c != null)
				sum += c.getValue();
		return sum;
	}
}
