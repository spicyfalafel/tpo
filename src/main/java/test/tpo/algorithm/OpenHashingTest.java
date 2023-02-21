package tpo.algorithm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class OpenHashingTest {
    MyMap<String, String> stringMap;
    MyMap<TestClass, Integer> testClassMap;

    @BeforeEach
    public void initAll() {
        stringMap = new MyMap<>();
        testClassMap = new MyMap<>();
    }

    @DisplayName("Empty map has size 0.")
    @Test
    public void testEmptyMap() {
        assertEquals(0, stringMap.size_of_array());
        assertTrue(stringMap.isEmpty());
    }

    @DisplayName("Add works")
    @ParameterizedTest(name = "{index}. add works")
    @CsvSource({
            "Key1, val1, Key2, val2, val3"
    })
    public void checkAdd(String k1, String v1, String k2, String v2, String v3) {
        stringMap.add(k1, v1);
        assertEquals(1, stringMap.size_of_array());
        assertEquals(v1, stringMap.get(k1));

        stringMap.add(k2, v2);
        assertEquals(2, stringMap.size_of_array());
        assertEquals(v2, stringMap.get(k2));

        stringMap.add(k2, v3);
        assertEquals(2, stringMap.size_of_array());
        assertEquals(v3, stringMap.get(k2));
    }

    @DisplayName("null as key is not supported by get")
    @Test
    public void checkAddNullKey() {
        stringMap.add(null, "val");
        assertEquals(1, stringMap.size_of_array());
        assertThrows(NullPointerException.class, () -> {
            stringMap.get(null);});
    }

    @DisplayName("null as value is supported")
    @Test
    public void checkAddNullValue() {
        stringMap.add("key", null);
        assertEquals(1, stringMap.size_of_array());
        assertNull(stringMap.get("key"));
    }

    @DisplayName("The same keys update values")
    @ParameterizedTest(name = "{index}. checkAddSameKeys")
    @CsvSource({
            "Same, val1, val2"
    })
    public void checkAddSameKeys(String key, String val1, String val2) {
        stringMap.add(key, val1);
        assertEquals(1, stringMap.size_of_array());
        assertEquals(val1, stringMap.get(key));

        stringMap.add(key, val2);
        assertEquals(1, stringMap.size_of_array());
        assertEquals(val2, stringMap.get(key));
    }

    @DisplayName("Remove works, size is counted right")
    @ParameterizedTest(name = "{index}. remove works, size is counted right")
    @CsvSource({
            "1, Key1, 2, Key2, 3, Key3",
    })
    public void removeWorks(String key1, String value1, String key2, String value2, String key3, String value3) {
        stringMap.add(key1, value1);
        stringMap.add(key2, value2);
        stringMap.add(key3, value3);

        assertEquals(3, stringMap.size_of_array());

        assertEquals(value2, stringMap.remove(key2));
        assertEquals(2, stringMap.size_of_array());
        assertNull(stringMap.get(key2));

        assertEquals(value3, stringMap.remove(key3));
        assertEquals(1, stringMap.size_of_array());
        assertNull(stringMap.get(key3));

        assertEquals(value1, stringMap.remove(key1));
        assertEquals(0, stringMap.size_of_array());
        assertNull(stringMap.get(key1));
    }

    @DisplayName("Remove null does nothing")
    @ParameterizedTest(name = "{index}. remove null does nothing")
    @CsvSource({
            "1, value",
            "1,"
    })
    public void removeNullWorks(String key, String value) {
        stringMap.add(key, value);
        assertNull(stringMap.remove(null));
        assertEquals(1, stringMap.size_of_array());
        assertEquals(value, stringMap.get(key));
    }

    @DisplayName("Remove will not remove other values if executed few times for one key")
    @ParameterizedTest(name = "{index}. Remove will not remove other values if executed few times for one key")
    @CsvSource({
            "Key1, 1, Key2, 2",
            "Key2, 2, Key1, 1"
    })
    public void removeWorksForSameKey(String key1, String value1, String key2, String value2) {
        stringMap.add(key1, value1);
        stringMap.add(key2, value2);

        stringMap.remove(key1);
        stringMap.remove(key1);
        assertEquals(1, stringMap.size_of_array());
        assertEquals(value2, stringMap.get(key2));
    }

    @DisplayName("Test getBucketIndex method")
    @ParameterizedTest(name = "{index}. Test getBucketIndex method")
    @CsvSource({
            "1, 12, 1",
            "12, 12, 0",
            "-1, 12, 1",
            "13, 12, 1"
    })
    public void getBucketIndexTest(int hashValue, int numbucks, int expectedIndex) {
        TestClass a = new TestClass(hashValue);
        testClassMap.numbucks = numbucks;
        testClassMap.add(a, 1);
        assertEquals(expectedIndex, testClassMap.getBucketIndex(a));
    }
    @DisplayName("Test resize when adding new element")
    @ParameterizedTest(name = "{index}. Test resize")
    @CsvSource({
            "1, 2, 4",
            "2, 2, 4",
            "3, 3, 3"
    })
    public void testResize(int numbucks1, int numbucks2, int numbucks3) {
        stringMap.numbucks = numbucks1;
        stringMap.add("key", "val");
        assertEquals(numbucks2, stringMap.numbucks);

        stringMap.add("key1", "val1");
        assertEquals(numbucks3, stringMap.numbucks);
    }
}