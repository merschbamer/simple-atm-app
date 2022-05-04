package com.project.atm.configuration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScannerConfigurationTests {

    private ScannerConfiguration scannerConfiguration;

    @BeforeEach
    public void init() {
        scannerConfiguration = new ScannerConfiguration();
    }

    @Test
    public void testScanner() {
        assertTrue(scannerConfiguration.scanner() instanceof Scanner);
    }
}
