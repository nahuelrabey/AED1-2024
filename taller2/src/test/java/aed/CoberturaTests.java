package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CoberturaTests {
    Cobertura cobertura = new Cobertura();

    @Test
    void testFizzBuzz() {
        assertEquals("Fizz", cobertura.fizzBuzz(3));
        assertEquals("Buzz", cobertura.fizzBuzz(5));
        assertEquals("FizzBuzz", cobertura.fizzBuzz(15));
        assertEquals("2", cobertura.fizzBuzz(2));
    }

    @Test
    void testNumeroCombinatorio() {
        assertTrue(false);
    }

    @Test
    void testRepeticionesConsecutivas() {
        assertTrue(false);
    }
}
