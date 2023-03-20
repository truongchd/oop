import java.util.*;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		HashMap<T, Integer> map = new HashMap<T, Integer>();
		for (T item : a) {
			if (map.containsKey(item)) {
				int current = map.get(item);
				map.put(item, current + 1);
			} else {
				map.put(item, 1);
			}
		}
		for (T item: b) {
			if (map.containsKey(item)) {
				int current = map.get(item);
				map.put(item, current - 1);
			} 
		}
		int total = 0;
		for (Map.Entry<T, Integer> hashItem: map.entrySet()) {
			if (hashItem.getValue() == 0) {
				total = total + 1;
			}
		}
		return total; // YOUR CODE HERE
	}
	
}
