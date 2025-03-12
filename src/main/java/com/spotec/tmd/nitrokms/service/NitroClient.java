package com.spotec.tmd.nitrokms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.Socket;

public class NitroClient {
    // Nitro Enclave vSock 地址
    private static final String VSOCK_HOST = "169.254.0.1";
    private static final int VSOCK_PORT = 5005;

    public static void main(String[] args) {
        try (Socket socket = new Socket(VSOCK_HOST, VSOCK_PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // 构造 JSON 请求
            EncryptionRequest request = new EncryptionRequest("encrypt", "arn:aws:kms:us-east-1:123456789012:key/example-key", "Hello Nitro!");
            String jsonRequest = new ObjectMapper().writeValueAsString(request);

            writer.println(jsonRequest);
            System.out.println("Sent request: " + jsonRequest);

            String response = reader.readLine();
            System.out.println("Received response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class EncryptionRequest {
        private String action;
        private String keyId;
        private String data;

        public EncryptionRequest(String action, String keyId, String data) {
            this.action = action;
            this.keyId = keyId;
            this.data = data;
        }
    }
}
