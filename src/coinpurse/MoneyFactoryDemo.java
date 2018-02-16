package coinpurse;

public class MoneyFactoryDemo {

	public static void main(String[] args) {
		MoneyFactory.setMoneyFactory(new ThaiMoneyFactory());
		MoneyFactory mf = MoneyFactory.getInstance();
		Purse p = new Purse(10);

		// 1. Test it is singleton
		System.out.println("=====================================");
		MoneyFactory mfTest = MoneyFactory.getInstance();
		System.out.println(mf == mfTest);
		System.out.println("=====================================");

		// 2. Test all the methods work as specified.
		p.insert(mf.createMoney(100));
		p.insert(mf.createMoney(0.5));
		p.insert(mf.createMoney(0.25));
		p.insert(mf.createMoney(50));
		p.insert(mf.createMoney(10));

		for (Valuable v : p.getMoney()) {
			System.out.println(v + ", but getCurrency is " + v.getCurrency());
		}
		System.out.println("=====================================");

	}
}
