package com.example.chatai.infrastructure;

import com.alibaba.dashscope.app.Application;
import com.alibaba.dashscope.app.ApplicationParam;
import com.alibaba.dashscope.app.ApplicationResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HrLLMAppClient {
    private final Application application;
    @Value("${apikey.dashscope}")
    private String apiKey;
    @Value("${hr-app.id}")
    private String appId;

    public HrLLMAppClient() {
        this.application = new Application();
    }

    @SneakyThrows
    public ApplicationResult appCall(String content) {
        ApplicationParam param = ApplicationParam.builder()
                // 若没有配置环境变量，可用百炼API Key将下行替换为：.apiKey("sk-xxx")。但不建议在生产环境中直接将API Key硬编码到代码中，以减少API Key泄露风险。
                .apiKey(apiKey)
                .appId(appId)
                .prompt(content)
                .build();

        Application application = new Application();
        return application.call(param);
    }
}
