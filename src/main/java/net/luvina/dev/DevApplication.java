package net.luvina.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class DevApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(DevApplication.class, args);
	}
}
