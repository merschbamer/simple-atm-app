package com.project.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.atm.controller.AtmCommandLineController;

@SpringBootApplication
public class AtmApplication implements CommandLineRunner {

    @Autowired
    private AtmCommandLineController commandLineController;

    public static void main(String[] args) {
        SpringApplication.run(AtmApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        commandLineController.start();
    }
}
