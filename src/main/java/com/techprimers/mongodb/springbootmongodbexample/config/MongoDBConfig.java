package com.techprimers.mongodb.springbootmongodbexample.config;

import com.techprimers.mongodb.springbootmongodbexample.document.User;
import com.techprimers.mongodb.springbootmongodbexample.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {


    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return strings -> {
//            userRepository.save(new User("tranquocdat88888@gamil.com","123","Rnd"));
//            userRepository.save(new User("tranquocdat1555gg@gamil.com","123","Rnd"));
////            userRepository.save(new User(3,"tranquocdat2@gamil.com","123","Rnd"));
//            userRepository.save(new User("tranquocdat377@gamil.com","123","Rnd"));


//            userRepository.save(new User(5,"tranquocdadddddt@gamil.com","123","Rnduuu"));








        };
    }

    @Bean
    public String hello(){
        return "hello anh dat";
    }

}
