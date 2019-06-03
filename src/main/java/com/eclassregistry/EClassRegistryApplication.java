package com.eclassregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EClassRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EClassRegistryApplication.class, args);
	}

        
//        @Bean
//        public BCryptPasswordEncoder bCryptPasswordEncoder(){
//            return new BCryptPasswordEncoder();
//        }
        

        
       
}
