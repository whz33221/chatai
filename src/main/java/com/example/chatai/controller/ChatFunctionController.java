package com.example.chatai.controller;

import com.example.chatai.service.ChatService;
import com.example.chatai.service.ChatSseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author wuhongzhang@vhsoft.com.cn
 * @date 2025/6/7 14:13
 * @description
 */
@RestController
public class ChatFunctionController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatSseService chatFluxService;

    record DTO(String content){

    }

    @Operation(summary = "单词对话")
    @PostMapping(path = "/prototype/one-chat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String chat(@RequestBody DTO dto) {
        return chatService.chat(dto.content());
    }



    @Operation(summary = "sse响应")
    @PostMapping(path = "/chatbot/chat/sse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatResponse> chatFlux(@RequestBody DTO dto) {
        return chatFluxService.chatFlux(dto.content(),"112312");
    }



}
