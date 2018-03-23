package coinpurse.strategy;

import java.util.ArrayList;
import java.util.List;

import coinpurse.BankNote;
import coinpurse.Money;
import coinpurse.MoneyFactory;
import coinpurse.ThaiMoneyFactory;
import coinpurse.Valuable;

public class Test {

	public static void main(String[] args) {
		WithdrawStrategy strategy = new RecursiveWithdraw();
		MoneyFactory thaiMoney = new ThaiMoneyFactory();
		List<Valuable> withdraw;
		List<Valuable> moneyInPurse = new ArrayList<Valuable>();
		moneyInPurse.add(thaiMoney.createMoney(5));
		moneyInPurse.add(thaiMoney.createMoney(10));
		Money money = new BankNote(15, "Baht");
		withdraw = strategy.withdraw(money, moneyInPurse);
		System.out.println(moneyInPurse);
		System.out.println(withdraw);
	}
}
