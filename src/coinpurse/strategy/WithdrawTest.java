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

public class WithdrawTest {

	private WithdrawStrategy strategy;
	private MoneyFactory thaiMoney;
	private List<Valuable> withdraw;
	private List<Valuable> moneyInPurse = new ArrayList<Valuable>();

	@Before
	public void setUp() {
		// strategy = new GreedyWithdraw();
		strategy = new RecursiveWithdraw();
		thaiMoney = new ThaiMoneyFactory();
		moneyInPurse.add(thaiMoney.createMoney(5));
		moneyInPurse.add(thaiMoney.createMoney(10));
		moneyInPurse.add(thaiMoney.createMoney(20));
		moneyInPurse.add(thaiMoney.createMoney(50));
		moneyInPurse.add(thaiMoney.createMoney(100));
	}

	@Test
	public void withdrawEverythingFromPurse() {
		Money money = new BankNote(185, "Baht");
		withdraw = strategy.withdraw(money, moneyInPurse);
		Collections.sort(moneyInPurse);
		Collections.sort(withdraw);
		assertEquals(withdraw, moneyInPurse);
	}

	@Test
	public void withdrawExceptOneItemFromPurse() {
		Money money = new BankNote(180, "Baht");
		withdraw = strategy.withdraw(money, moneyInPurse);
		Collections.sort(moneyInPurse);
		Collections.sort(withdraw);
		moneyInPurse.remove(0);
		assertEquals(withdraw, moneyInPurse);
	}

	@Test
	public void withdrawMoreThanPurseBalance() {
		Money money = new BankNote(2000, "Baht");
		assertNull(strategy.withdraw(money, moneyInPurse));
	}

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

	@Test
	public void possibleWithdraw() {
		List<Valuable> moneyToWithdraw = new ArrayList<Valuable>();
		moneyToWithdraw.add(thaiMoney.createMoney(5));
		moneyToWithdraw.add(thaiMoney.createMoney(2));
		moneyToWithdraw.add(thaiMoney.createMoney(2));
		moneyToWithdraw.add(thaiMoney.createMoney(2));
		withdraw = strategy.withdraw(new Coin(6), moneyToWithdraw);
		List<Valuable> moneyMustWithdraw = new ArrayList<Valuable>();
		moneyMustWithdraw.add(thaiMoney.createMoney(2));
		moneyMustWithdraw.add(thaiMoney.createMoney(2));
		moneyMustWithdraw.add(thaiMoney.createMoney(2));
		assertEquals(withdraw, moneyMustWithdraw);
	}

	@Test
	public void manySolutionWithdraw() {
		
		List<Valuable> moneyToWithdraw = new ArrayList<Valuable>();
		moneyToWithdraw.add(thaiMoney.createMoney(2));
		moneyToWithdraw.add(thaiMoney.createMoney(5));
		moneyToWithdraw.add(thaiMoney.createMoney(1));
		moneyToWithdraw.add(thaiMoney.createMoney(2));
		moneyToWithdraw.add(thaiMoney.createMoney(2));
		
		withdraw = strategy.withdraw(new Coin(6), moneyToWithdraw);
		
		List<Valuable> moneyRecurMustWithdraw = new ArrayList<Valuable>();
		moneyRecurMustWithdraw.add(thaiMoney.createMoney(2));
		moneyRecurMustWithdraw.add(thaiMoney.createMoney(2));
		moneyRecurMustWithdraw.add(thaiMoney.createMoney(2));
		
		assertEquals(withdraw, moneyRecurMustWithdraw);

		WithdrawStrategy greedy = new GreedyWithdraw();
		List<Valuable> greedyList = greedy.withdraw(new Coin(6), moneyToWithdraw);
		
		List<Valuable> moneyGreedyMustWithdraw = new ArrayList<Valuable>();
		moneyGreedyMustWithdraw.add(thaiMoney.createMoney(5));
		moneyGreedyMustWithdraw.add(thaiMoney.createMoney(1));
		
		assertEquals(greedyList, moneyGreedyMustWithdraw);

	}

}
