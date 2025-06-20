package com.example.chatai.service;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

/**
 * @author wuhongzhang@vhsoft.com.cn
 * @date 2025/6/7 14:17
 * @description
 */
@Service
public class ChatSseService {
    @Resource
    private ChatModel dashScopeChatModel;

    private final ChatClient chatClient;

    public ChatSseService(ChatClient.Builder builder) {
        InMemoryChatMemory chatMemory = new InMemoryChatMemory();
        this.chatClient = builder
                //顾问
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                //系统提示词
                .defaultSystem("你是档案管理员").build();
    }

    public Flux<ChatResponse> chatFlux(String message, String chatId) {
        return chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .stream()
                .chatResponse();
    }

}
