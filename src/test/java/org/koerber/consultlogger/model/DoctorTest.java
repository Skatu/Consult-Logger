package org.koerber.consultlogger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {
    private Specialty specialty;

    @BeforeEach
    void setUp() {
        specialty = new Specialty("Some Specialty");
    }

    @Test
    void cannotCreateDoctorWithNullName(){
        var exception = assertThrows(IllegalArgumentException.class, () ->
            new Doctor(null,specialty));
        var expectedMessage = "Name cannot be blank";
        var actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void cannotCreateDoctorWithEmptyName(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Doctor("",specialty));
        var expectedMessage = "Name cannot be blank";
        var actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void cannotCreateDoctorWithNullSpecialty(){
        var exception = assertThrows(NullPointerException.class, () ->
                new Doctor("",null));
        var expectedMessage = "specialty is marked non-null but is null";
        var actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEqualsForEqualObjects(){
        var doctor1 = new Doctor("Test",specialty);
        var doctor2 = new Doctor("Test",specialty);
        doctor1.setId(1L);
        doctor2.setId(1L);
        assertEquals(doctor1, doctor2);
    }

    @Test
    void testEqualsForNonEqualObjects(){
        var doctor1 = new Doctor("Test1",specialty);
        var doctor2 = new Doctor("Test2",specialty);
        doctor1.setId(1L);
        doctor2.setId(1L);
        assertNotEquals(doctor1, doctor2);
    }

    @Test
    void testHashCodeForEqualObjects(){
        var doctor1 = new Doctor("Test",specialty);
        var doctor2 = new Doctor("Test",specialty);
        assertEquals(doctor1.hashCode(), doctor2.hashCode());
    }

    @Test
    void testHashCodeForNonEqualObjects(){
        var doctor1 = new Doctor("Test1",specialty);
        var doctor2 = new Doctor("Test2",specialty);
        assertNotEquals(doctor1.hashCode(), doctor2.hashCode());
    }
}
