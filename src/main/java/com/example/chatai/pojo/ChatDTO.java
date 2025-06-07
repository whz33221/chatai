package com.example.chatai.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author wuhongzhang@vhsoft.com.cn
 * @date 2025/6/7 14:22
 * @description
 */
@Getter
@Setter
@Accessors(chain = true)
public class ChatDTO {
    private String content;
}
