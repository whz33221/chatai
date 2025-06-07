package com.example.chatai.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wuhongzhang@vhsoft.com.cn
 * @date 2025/6/7 15:12
 * @description
 */
@SpringBootTest
class ChatServiceTest {
    @Autowired
    private ChatService chatService;

    @Test
    public void testchat(){
        String res = chatService.chat("你是谁");
        System.out.println(res);
    }

}