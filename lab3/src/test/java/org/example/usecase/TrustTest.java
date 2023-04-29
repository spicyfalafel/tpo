package org.example.usecase;

import org.example.Utils;
import org.example.model.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrustTest {

    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void testTrust() {
        Utils.getDrivers().forEach(driver ->
        {

        });
    }

    @AfterAll
    static void exit(){
        Utils.exit();
    }
}
