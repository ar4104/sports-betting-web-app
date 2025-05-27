package com.labpatterns2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ClientFactoryTest {

    @Test
    public void testClientCreation() {
        Client vipClient = ClientFactory.createClient("John", 1, "123456", true);
        Client standardClient = ClientFactory.createClient("Mike", 2, "654321", false);

        assertEquals("John", vipClient.getName());
        assertEquals(1, vipClient.getId());
        assertEquals("123456", vipClient.getPhone());
        assertTrue(vipClient.isVIP());

        assertEquals("Mike", standardClient.getName());
        assertEquals(2, standardClient.getId());
        assertEquals("654321", standardClient.getPhone());
        assertFalse(standardClient.isVIP());
    }
}

// Supporting classes for the test

class Client {
    private String name;
    private int id;
    private String phone;
    private boolean isVIP;

    public Client(String name, int id, String phone, boolean isVIP) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.isVIP = isVIP;
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public String getPhone() { return phone; }
    public boolean isVIP() { return isVIP; }
}

class ClientFactory {
    public static Client createClient(String name, int id, String phone, boolean isVIP) {
        return new Client(name, id, phone, isVIP);
    }
}
