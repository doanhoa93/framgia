package com.framgia.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framgia.support.RequestChatWorkBuilder;

@Slf4j
@Service
public class ChatworkService {

    @Autowired
    private RequestChatWorkBuilder requestChatWorkBuilder;

    public void sendMessage(String roomId, String message) {
        if (StringUtils.isBlank(roomId)) {
            throw new IllegalArgumentException("Room ID is empty");
        }
        try {
            Map<String, String> params = new HashMap<>();
            params.put("body", message);
            requestChatWorkBuilder.postForm("/rooms/" + roomId + "/messages", params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}