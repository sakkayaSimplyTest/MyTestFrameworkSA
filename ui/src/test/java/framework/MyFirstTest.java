package framework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyFirstTest {

    @Test
    void myFirstTest() {
        int result = sum(2, 2);
//        Assertions.assertNotEquals(5, result);
        assertEquals(4, result);
    }

    static int sum(int a,  int b) {
        return a + b;
    }



}
