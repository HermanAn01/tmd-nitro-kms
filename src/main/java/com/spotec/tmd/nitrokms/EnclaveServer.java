package com.spotec.tmd.nitrokms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spotec.tmd.nitrokms.service.KmsService;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EnclaveServer {
    // Nitro Enclaves vSock 端口
    private static final int VSOCK_PORT = 5005;

    public static void startServer() throws IOException {
        System.out.println("Starting Nitro Enclave Encryption Server...");

        try (ServerSocket serverSocket = new ServerSocket(VSOCK_PORT)) {
            KmsService encryptionService = new KmsService();
            ObjectMapper objectMapper = new ObjectMapper();

            while (true) {
                System.out.println("Waiting for connections on vSock port " + VSOCK_PORT);
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                    String request = reader.readLine();
                    if (request == null) {
                        continue;
                    }

                    System.out.println("Received request: " + request);

                    // 解析 JSON 请求
                    EncryptionRequest encryptionRequest = objectMapper.readValue(request, EncryptionRequest.class);
                    String response;

                    if ("encrypt".equalsIgnoreCase(encryptionRequest.getAction())) {
                        String encryptedData = encryptionService.encrypt(encryptionRequest.getKeyId(), encryptionRequest.getData());
                        response = encryptedData;
                    } else if ("decrypt".equalsIgnoreCase(encryptionRequest.getAction())) {
                        response = encryptionService.decrypt(encryptionRequest.getData());
                    } else {
                        response = "Invalid action";
                    }

                    writer.println(response);
                    System.out.println("Response sent: " + response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class EncryptionRequest {
        private String action;
        private String keyId;
        private String data;

        public String getAction() { return action; }
        public String getKeyId() { return keyId; }
        public String getData() { return data; }
    }
}