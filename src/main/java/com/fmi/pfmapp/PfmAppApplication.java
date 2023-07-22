package com.fmi.pfmapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PfmAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(PfmAppApplication.class, args);
        System.out.println("Ready!");
    }
}
