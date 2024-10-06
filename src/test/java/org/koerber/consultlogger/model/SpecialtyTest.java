package org.koerber.consultlogger.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SpecialtyTest {
    @Test
    void cannotCreateSymptomWithNullName(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Specialty(null));
        String expectedMessage = "Name cannot be blank";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void cannotCreateSymptomWithEmptyName(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Specialty(""));
        String expectedMessage = "Name cannot be blank";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEqualsForEqualObjects(){
        Specialty specialty1 = new Specialty("Test");
        Specialty specialty2 = new Specialty("Test");
        specialty1.setId(1L);
        specialty2.setId(1L);
        assertEquals(specialty1, specialty2);
    }

    @Test
    void testEqualsForNonEqualObjects(){
        Specialty specialty1 = new Specialty("Test1");
        Specialty specialty2 = new Specialty("Test2");
        specialty1.setId(1L);
        specialty2.setId(1L);
        assertNotEquals(specialty1, specialty2);
    }

    @Test
    void testHashCodeForEqualObjects(){
        Specialty specialty1 = new Specialty("Test");
        Specialty specialty2 = new Specialty("Test");
        assertEquals(specialty1.hashCode(), specialty2.hashCode());
    }

    @Test
    void testHashCodeForNonEqualObjects(){
        Specialty specialty1 = new Specialty("Test1");
        Specialty specialty2 = new Specialty("Test2");
        assertNotEquals(specialty1.hashCode(), specialty2.hashCode());
    }
}
