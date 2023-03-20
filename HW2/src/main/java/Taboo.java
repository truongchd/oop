
/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/

import java.util.*;

public class Taboo<T> {
	private Map<T, Set<T>> multiMap = new HashMap<>();
	
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public List<String> stringToList(String s) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(String.valueOf(s.charAt(i)));
		}
		return list;
	}

	public Taboo(List<T> rules) {
		this.multiMap.clear();
		for (int i = 0; i < rules.size() - 1; i++) {
            T keyTaboo = rules.get(i);
			T valueTaboo = rules.get(i + 1);
			if (this.multiMap.containsKey(keyTaboo)) {
				multiMap.get(keyTaboo).add(valueTaboo);
			} else {
				this.multiMap.put(keyTaboo, new HashSet<T>());
				this.multiMap.get(keyTaboo).add(valueTaboo);
			}
        }
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		return multiMap.get(elem); // YOUR CODE HERE
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		Set<T> constraint = this.noFollow(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			while (i < list.size()) {
				if (constraint != null) {
					if (constraint.contains(list.get(i))) {
						list.remove(i);
					} else {
						break;
					}
				} else {
					break;
				}
			}
			if (i < list.size()) {
				constraint = this.noFollow(list.get(i));
			}
		}
	}
}
