package com.example.chatai.service;

import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

/**
 * @author wuhongzhang@vhsoft.com.cn
 * @date 2025/6/7 14:17
 * @description
 */
@Service
public class ChatService {
    @Resource
    private ChatModel dashScopeChatModel;

    public String chat(String input){
        ChatResponse call = dashScopeChatModel.call(new Prompt(input));
        return call.getResult().getOutput().getText();
    }
}
