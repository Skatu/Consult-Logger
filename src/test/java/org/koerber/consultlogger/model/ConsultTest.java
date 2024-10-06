package org.koerber.consultlogger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.koerber.consultlogger.exception.InvalidSpecialtyException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ConsultTest {
    private Patient patient;
    private Doctor doctor1;
    private Doctor doctor2;

    @BeforeEach
    void setUp() {
        var specialty1 = new Specialty("specialty 1");
        var specialty2 = new Specialty("specialty 2");
        doctor1 = new Doctor("António", specialty1);
        doctor2 = new Doctor("Maria", specialty2);
        patient = new Patient("Test1", 1, Collections.emptyList());
    }
    @Test
    void test(){
        var exception = assertThrows(InvalidSpecialtyException.class, () ->
                new Consult(doctor1, patient));
        var expectedMessage = "Doctor not assigned to specialty";
        var actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEqualsForEqualObjects() throws InvalidSpecialtyException {
        var consult1 = new Consult(doctor1, patient);
        var consult2 = new Consult(doctor1, patient);
        consult1.setId(1L);
        consult2.setId(1L);
        assertEquals(consult1, consult2);
    }

    @Test
    void testEqualsForNonEqualObjects() throws InvalidSpecialtyException {
        var consult1 = new Consult(doctor1, patient);
        var consult2 = new Consult(doctor2, patient);
        consult1.setId(1L);
        consult2.setId(1L);
        assertNotEquals(consult1, consult2);
    }

    @Test
    void testHashCodeForEqualObjects() throws InvalidSpecialtyException {
        var consult1 = new Consult(doctor1, patient);
        var consult2 = new Consult(doctor1, patient);
        assertEquals(consult1.hashCode(), consult2.hashCode());
    }

    @Test
    void testHashCodeForNonEqualObjects() throws InvalidSpecialtyException {
        var consult1 = new Consult(doctor1, patient);
        var consult2 = new Consult(doctor2, patient);
        assertNotEquals(consult1.hashCode(), consult2.hashCode());
    }
}
