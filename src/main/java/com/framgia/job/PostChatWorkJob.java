package com.framgia.job;

import com.framgia.service.ChatworkService;
import com.framgia.support.FaceBookClient;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class PostChatWorkJob implements Job {

    @Autowired
    private FaceBookClient faceBookClient;

    @Autowired
    private ChatworkService chatworkService;

    @Value("${chatwork.roomId}")
    private String chatworkRoomId;

    @Override
    public void execute(JobExecutionContext context) {
        log.info(String.valueOf(LocalDateTime.now().getHour()) + ":" + String.valueOf(LocalDateTime.now().getMinute()));
        String message = faceBookClient.getPermaLinkUrl();
        chatworkService.sendMessage(chatworkRoomId, message);
    }
}