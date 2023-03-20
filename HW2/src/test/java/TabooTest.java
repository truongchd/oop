// TabooTest.java
// Taboo class tests -- nothing provided.
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

import java.util.*;

public class TabooTest {
    // try to copy teacher's way of creating test
    private List<String> stringToList(String s) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(String.valueOf(s.charAt(i)));
		}
		return list;
	}

    @Test
    public void test_1() {
        List<String> list = stringToList("acab");
        
        Taboo<String> rules = new Taboo<String>(list);
        Set<String> ans = new HashSet<String>();
        ans.add("c");
        ans.add("b");

        assertEquals(ans, rules.noFollow("a"));
    }

    @Test
    public void test_2() {
        List<String> rule = stringToList("acab");
        List<String> list = stringToList("acbxca");
        Taboo<String> rules = new Taboo<String>(rule);

        List<String> ans = stringToList("axc");
        rules.reduce(list);
        assertEquals(ans, list);
    }
}