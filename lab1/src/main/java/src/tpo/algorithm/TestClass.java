package tpo.algorithm;

public class TestClass {
    public int value;

    public TestClass(int value) {
        this.value = value;
    }
    @Override
    public int hashCode() {
        return value;
    }
}