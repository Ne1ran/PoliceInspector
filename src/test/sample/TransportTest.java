package test.sample;

import org.junit.Test;
import sample.Transport;

import static org.junit.Assert.*;

public class TransportTest {

    Transport transportTest = new Transport("TP998R", "Toyota", "Тестеровов", "Тест", "Black", "R4");

    @Test
    public void getNumber() {
        assertEquals("TP998R", transportTest.getNumber());
    }

    @Test
    public void getBrand() {
        assertEquals("Toyota", transportTest.getBrand());
    }

    @Test
    public void getDriversurname() {
        assertEquals("Тестеровов", transportTest.getDriversurname());
    }

    @Test
    public void getDrivername() {
        assertEquals("Тест", transportTest.getDrivername());
    }

    @Test
    public void getColor() {
        assertEquals("Black", transportTest.getColor());
    }

    @Test
    public void getModel() {
        assertEquals("R4", transportTest.getModel());
    }
}