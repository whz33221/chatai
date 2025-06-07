package com.example.chatai.service;

import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClientBuilder;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

/**
 * @author wuhongzhang@vhsoft.com.cn
 * @date 2025/6/7 14:17
 * @description
 */
@Service
public class ChatService {
    @Resource
    private ChatModel dashScopeChatModel;

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        InMemoryChatMemory chatMemory = new InMemoryChatMemory();
        this.chatClient = builder
//                //顾问
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
//                //配置
//                .defaultOptions()
                //系统提示词
                .defaultSystem("你是档案管理员").build();
    }

    public String chat(String input){
        ChatResponse call = dashScopeChatModel.call(new Prompt(input));
        return call.getResult().getOutput().getText();
    }

    public String chatLoop(String input,String chatId){
        ChatResponse chatResponse = chatClient
                .prompt()
                .user(input)
                .advisors(spec -> spec
                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .chatResponse();
        return chatResponse.getResult().getOutput().getText();
    }



}
