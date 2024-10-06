package org.koerber.consultlogger.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {
    @Test
    void cannotCreatePatientWithNullName(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Patient(null,0, null));
        String expectedMessage = "Name cannot be blank";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void cannotCreatePatientWithEmptyName(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Patient("",0, null));
        String expectedMessage = "Name cannot be blank";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testEqualsForEqualObjects(){
        Patient Patient1 = new Patient("Test",1, Collections.emptyList());
        Patient Patient2 = new Patient("Test",1, Collections.emptyList());
        Patient1.setId(1L);
        Patient2.setId(1L);
        assertEquals(Patient1, Patient2);
    }

    @Test
    void testEqualsForNonEqualObjects(){
        Patient Patient1 = new Patient("Test1",1, Collections.emptyList());
        Patient Patient2 = new Patient("Test2",1, Collections.emptyList());
        Patient1.setId(1L);
        Patient2.setId(1L);
        assertNotEquals(Patient1, Patient2);
    }

    @Test
    void testHashCodeForEqualObjects(){
        Patient Patient1 = new Patient("Test",1, Collections.emptyList());
        Patient Patient2 = new Patient("Test",1, Collections.emptyList());
        assertEquals(Patient1.hashCode(), Patient2.hashCode());
    }

    @Test
    void testHashCodeForNonEqualObjects(){
        Patient Patient1 = new Patient("Test1",1, Collections.emptyList());
        Patient Patient2 = new Patient("Test2",1, Collections.emptyList());
        assertNotEquals(Patient1.hashCode(), Patient2.hashCode());
    }
}
