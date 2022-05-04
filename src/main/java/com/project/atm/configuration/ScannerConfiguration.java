package com.project.atm.configuration;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScannerConfiguration {

    @Bean
    @ConditionalOnMissingBean(Scanner.class)
    public Scanner scanner(){
        // Scanner used to read user input from command line
        return new Scanner(System.in);
    }
}
