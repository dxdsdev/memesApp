package com.crowlabs.memesapp.model;

import lombok.Data;

/**
 *
 * @author croweloper
 */
public @Data class Meme {
    private String url;
    private String postLink;
    private String subreddit;
    private String title;
    //private final String apiKey= "";
}
    