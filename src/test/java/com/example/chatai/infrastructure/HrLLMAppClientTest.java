package com.example.chatai.infrastructure;

import com.alibaba.dashscope.app.ApplicationResult;
import com.example.chatai.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HrLLMAppClientTest {
    @Autowired
    private HrLLMAppClient hrLLMAppClient;

    @Test
    public void testchat(){
        ApplicationResult applicationResult = hrLLMAppClient.appCall("我想问下，我们公司流程是怎么走的？");
        System.out.println(applicationResult.getOutput().getText());
    }

}