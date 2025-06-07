package com.example.chatai.controller;

import com.example.chatai.pojo.ChatDTO;
import com.example.chatai.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuhongzhang@vhsoft.com.cn
 * @date 2025/6/7 14:13
 * @description
 */
@RestController
public class ChatFunctionController {
    @Autowired
    private ChatService chatService;

    record DTO(String content){

    }

    @Operation(summary = "单词对话")
    @PostMapping(path = "/prototype/one-chat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String chat(@RequestBody DTO dto) {
        return chatService.chat(dto.content());
    }

}
