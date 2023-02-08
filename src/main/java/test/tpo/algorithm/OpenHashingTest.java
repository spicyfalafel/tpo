package tpo.algorithm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class OpenHashingTest {
    MyMap<String, Integer> myMap;

    @BeforeEach
    public void initAll() {
        myMap = new MyMap<>();
    }

    @DisplayName("Empty map has size 0.")
    @Test
    public void testEmptyMap() {
        assertEquals(0, myMap.size_of_array());
        assertTrue(myMap.isEmpty());
    }

    @DisplayName("Add works ")
    @Test
    public void checkAdd() {
        myMap.add("Key1", 1);
        assertEquals(1, myMap.size_of_array());
        assertEquals(1, myMap.get("Key1"));

        myMap.add("Key2", 2);
        assertEquals(2, myMap.size_of_array());
        assertEquals(2, myMap.get("Key2"));

        myMap.add("Key1", 3);
        assertEquals(2, myMap.size_of_array());
        assertEquals(3, myMap.get("Key1"));
    }

    @DisplayName("null as key is not supported by get")
    @Test
    public void checkAddNullKey() {
        myMap.add(null, 1);
        assertEquals(1, myMap.size_of_array());
        assertThrows(NullPointerException.class, () -> {myMap.get(null);});
    }

    @DisplayName("null as value is supported")
    @Test
    public void checkAddNullValue() {
        MyMap<Integer, String> mp = new MyMap<>();
        mp.add(1, null);
        assertEquals(1, mp.size_of_array());
        assertNull(mp.get(1));
    }

    @DisplayName("The same keys update values")
    @Test
    public void checkAddSameKeys() {
        myMap.add("Same", 1);
        assertEquals(1, myMap.size_of_array());
        assertEquals(1, myMap.get("Same"));

        myMap.add("Same", 2);
        assertEquals(1, myMap.size_of_array());
        assertEquals(2, myMap.get("Same"));
    }

    @DisplayName("Remove works, size is counted right")
    @Test
    public void removeWorks() {
        myMap.add("Key1", 1);
        myMap.add("Key2", 2);
        myMap.add("Key3", 3);

        assertEquals(3, myMap.size_of_array());

        assertEquals(2, myMap.remove("Key2"));
        assertEquals(2, myMap.size_of_array());
        assertNull(myMap.get("Key2"));

        assertEquals(3, myMap.remove("Key3"));
        assertEquals(1, myMap.size_of_array());
        assertNull(myMap.get("Key3"));

        assertEquals(1, myMap.remove("Key1"));
        assertEquals(0, myMap.size_of_array());
        assertNull(myMap.get("Key1"));
    }

    @Test
    public void removeNullWorks() {
        myMap.add("Key1", 1);

        assertNull(myMap.remove(null));
        assertEquals(1, myMap.size_of_array());
        assertEquals(1, myMap.get("Key1"));
    }

    @DisplayName("Remove will not remove other values if executed few times for one key")
    @Test
    public void removeWorksForSameKey() {
        myMap.add("Key1", 1);
        myMap.add("Key2", 2);

        myMap.remove("Key1");
        myMap.remove("Key1");
        assertEquals(1, myMap.size_of_array());
        assertEquals(1, myMap.get("Key2"));
    }
}
