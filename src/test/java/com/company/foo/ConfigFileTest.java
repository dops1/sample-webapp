package com.company.foo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by zhelyan on 15/12/2016.
 */
class ConfigFileTest {
    ConfigFile c;

    @BeforeEach
    void setUp() {

        c = new ConfigFile("myfile", "abcd123");

    }

    @Test
    void getName() {
        assertEquals(c.getName(), "myfile");
    }

    @Test
    void getContents() {
        assertEquals(c.getContents(), "abcd123");
    }

    @Test
    void setName() {
        c.setName("foo");
        assertEquals(c.getName(), "foo");
    }

    @Test
    void testToString() {
        assertEquals(c.toString(), c.getName() + ";");
    }

}