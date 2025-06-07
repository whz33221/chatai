package com.example.chatai.service;

import cn.hutool.core.lang.UUID;
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


    @Test
    public void testchatLoop(){
        //uuid 作为 chat id;
        String chatid = "c6ad9a7c511542599bd2ad9adb4db011";
        String res = chatService.chatLoop("你是谁",chatid);
        System.out.println(res);
         res = chatService.chatLoop("你知道国家管理办法吗",chatid);
        System.out.println(res);
         res = chatService.chatLoop("你总共回答了我几个问题，包括现在这一次回答",chatid);
        System.out.println(res);
    }

    public static void main(String[] args) {
        String string = UUID.fastUUID().toString(true);
        System.out.println(string);
    }

}