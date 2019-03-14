package com.framgia.support;

import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FaceBookClient {

    @Value("${facebook.accessToken}")
    private String ACCESS_TOKEN;

    @Value("${facebook.pageId}")
    private String PAGE_ID;

    public String getPermaLinkUrl(){
        try {
            FacebookClient facebookClient1 = new DefaultFacebookClient(ACCESS_TOKEN, Version.LATEST);
            Connection<Post> albumConnection = facebookClient1.fetchConnection(
                    PAGE_ID + "/posts", Post.class);
            List<Post> posts = albumConnection.getData();
            return posts.get(0).getPermalinkUrl();
        }catch (FacebookOAuthException ex){
            return ex.getMessage();
        }
    }
}
