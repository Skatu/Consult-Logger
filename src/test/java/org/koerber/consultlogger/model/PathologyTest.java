package org.koerber.consultlogger.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PathologyTest {
    @Test
    void cannotCreateSymptomWithNullName(){
        var exception = assertThrows(IllegalArgumentException.class, () ->
            new Pathology(null));
        var expectedMessage = "Name cannot be blank";
        var actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void cannotCreateSymptomWithEmptyName(){
        var exception = assertThrows(IllegalArgumentException.class, () ->
                new Pathology(""));
        var expectedMessage = "Name cannot be blank";
        var actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEqualsForEqualObjects(){
        var pathology1 = new Pathology("Test");
        var pathology2 = new Pathology("Test");
        pathology1.setId(1L);
        pathology2.setId(1L);
        assertEquals(pathology1, pathology2);
    }

    @Test
    void testEqualsForNonEqualObjects(){
        var pathology1 = new Pathology("Test1");
        var pathology2 = new Pathology("Test2");
        pathology1.setId(1L);
        pathology2.setId(1L);
        assertNotEquals(pathology1, pathology2);
    }

    @Test
    void testHashCodeForEqualObjects(){
        var pathology1 = new Pathology("Test");
        var pathology2 = new Pathology("Test");
        assertEquals(pathology1.hashCode(), pathology2.hashCode());
    }

    @Test
    void testHashCodeForNonEqualObjects(){
        var pathology1 = new Pathology("Test1");
        var pathology2 = new Pathology("Test2");
        assertNotEquals(pathology1.hashCode(), pathology2.hashCode());
    }
}
