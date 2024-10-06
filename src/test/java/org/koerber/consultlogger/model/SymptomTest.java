package org.koerber.consultlogger.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SymptomTest {
    @Test
    void cannotCreateSymptomWithNullDescription(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Symptom(null));
        String expectedMessage = "Description cannot be blank";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void cannotCreateSymptomWithEmptyDescription(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Symptom(""));
        String expectedMessage = "Description cannot be blank";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testEqualsForEqualObjects(){
        Symptom symptom1 = new Symptom("Test");
        Symptom symptom2 = new Symptom("Test");
        symptom1.setId(1L);
        symptom2.setId(1L);
        assertEquals(symptom1, symptom2);
    }

    @Test
    void testEqualsForNonEqualObjects(){
        Symptom symptom1 = new Symptom("Test1");
        Symptom symptom2 = new Symptom("Test2");
        symptom1.setId(1L);
        symptom2.setId(1L);
        assertNotEquals(symptom1, symptom2);
    }

    @Test
    void testHashCodeForEqualObjects(){
        Symptom symptom1 = new Symptom("Test");
        Symptom symptom2 = new Symptom("Test");
        assertEquals(symptom1.hashCode(), symptom2.hashCode());
    }

    @Test
    void testHashCodeForNonEqualObjects(){
        Symptom symptom1 = new Symptom("Test1");
        Symptom symptom2 = new Symptom("Test2");
        assertNotEquals(symptom1.hashCode(), symptom2.hashCode());
    }
}
