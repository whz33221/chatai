package com.example.chatai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

/**
 * @author wuhongzhang@vhsoft.com.cn
 * @date 2025/6/7 17:03
 * @description
 */
@SpringBootTest
public class StructureOutputDemo {

    @Autowired
    private ChatModel chatModel;
    private ChatClient chatClient;

    @BeforeEach
    public void init() {
        if(chatClient==null){
            InMemoryChatMemory chatMemory = new InMemoryChatMemory();
            ChatClient.Builder builder = ChatClient.builder(chatModel);
            this.chatClient = builder
                    //顾问
                    .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                    //系统提示词
                    .defaultSystem("你是一个电影推介员").build();
        }
    }


    record Movie(String name,String desc){
        @Override
        public String toString() {
            return "Movie{" +
                    "name='" + name + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    @Test
    public void testStructureOutput() throws JsonProcessingException {
        String chatid = "c6ad9a7c511542599bd2ad9adb4db011";
        List<Movie> entity = chatClient
                .prompt()
                .user("给我推介5部2025最热电影,name为电影名称，desc为电影详细介绍")
                .advisors(spec -> spec
                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatid)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .entity(new ParameterizedTypeReference<>() {});

        String s = new ObjectMapper().writeValueAsString(entity);
        System.out.println(s);
    }


}
