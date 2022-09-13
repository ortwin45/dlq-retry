package org.ojothepojo.vo.ebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
public class EboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(EboxApplication.class, args);
    }

//    @Bean
//    public Consumer<List<Message<String>>> myBatchedInput(MyConsumer myConsumer) {
//        return myConsumer::consumeBatch1;
//    }

    @Bean
    public Consumer<Message<List<String>>> myBatchedInput(MyConsumer myConsumer) {
        return myConsumer::consumeBatch2;
    }

//    @Bean
//    public Consumer<List<String>> myBatchedInput2(MyConsumer myConsumer) {
//        return myConsumer::consumeBatch3;
//    }

    @Bean
    public Consumer<Message<String>> mySingleInput(MyConsumer myConsumer) {
        return myConsumer::consumeSingle;
    }

}
