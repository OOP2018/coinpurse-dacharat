package coinpurse;

import java.util.Comparator;

/**
 * Compare money by currency and if there are same currency it will compare value.
 * 
 * @author Dacharat Pankong
 *
 */
public class ValueComparator implements Comparator<Valuable> {

	/**
	 * Compare two objects that implement Valuable. First compare them by currency,
	 * so that "Baht" < "Dollar". If both objects have the same currency, order them
	 * by value.
	 */
	@Override
	public int compare(Valuable o1, Valuable o2) {
		if (o1.getCurrency().compareToIgnoreCase(o2.getCurrency()) == 0) {
			if (o1.getValue() < o2.getValue())
				return -1;
			else if (o1.getValue() > o2.getValue())
				return 1;
			return 0;
		}
		return o1.getCurrency().compareToIgnoreCase(o2.getCurrency());
	}

}
