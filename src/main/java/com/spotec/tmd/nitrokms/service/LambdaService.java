package com.spotec.tmd.nitrokms.service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.core.SdkBytes;
import org.springframework.stereotype.Service;

@Service
public class LambdaService {

    private final LambdaClient lambdaClient;

    public LambdaService() {
        this.lambdaClient = LambdaClient.builder()
                .region(Region.US_EAST_1) // 替换为您的区域
                .build();
    }

    public String invokeLambda(String functionName, String payload) {
        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName(functionName)
                .payload(SdkBytes.fromUtf8String(payload))
                .build();
        return lambdaClient.invoke(invokeRequest).payload().asUtf8String();
    }

    public String encrypt(String plaintext, String keyId) {
        // 构造加密请求的payload
        String payload = String.format("{\"plaintext\":\"%s\", \"keyId\":\"%s\"}", plaintext, keyId);
        return invokeLambda("encrypt", payload);
    }

    public String decrypt(String ciphertext) {
        // 构造解密请求的payload
        String payload = String.format("{\"ciphertext\":\"%s\"}", ciphertext);
        return invokeLambda("decrypt", payload);
    }
}