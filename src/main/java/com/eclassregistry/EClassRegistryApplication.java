package com.eclassregistry;

import com.eclassregistry.controller.WelcomeController;
import com.eclassregistry.service.impl.MessageServiceImpl;
import com.eclassregistry.service.impl.TeacherServiceImpl;
import com.eclassregistry.shared.dto.MessageDto;
import com.eclassregistry.shared.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EClassRegistryApplication {

     private static final Logger LOGGER = LoggerFactory.getLogger(EClassRegistryApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(EClassRegistryApplication.class, args);
	}

        
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder(){
            return new BCryptPasswordEncoder();
        }
        
//        public static void main(String[] args) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        
//            System.out.println(bCryptPasswordEncoder.encode("123456"));
//    }
        
//        public static void main(String[] args) {
//        TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl();
//        MessageServiceImpl messageServiceImpl = new MessageServiceImpl();
//        List<UserDto> allTeachersForMessages = teacherServiceImpl.getTeachersForParent(1);
//        List<MessageDto> allChats = new ArrayList<>();
//         for(int i=0; i < allTeachersForMessages .size();i++){
//            MessageDto messageDto = messageServiceImpl.getAllChatsForParent(1, allTeachersForMessages.get(i).getId());
//            if(messageDto!=null) allChats.add(messageDto);
//            
//
//        }
//    }
        
        

        
       
}
