package com.example.hana.hana.Data;

/**
 * Created by JinHee on 2016-11-14.
 */

public class Comment {
    final static int DATA_COUNT = 2;
    private String[] commentData;

    public Comment(String[] commentData) {

        this.commentData = commentData;

    }

    public Comment(String commentId,
                   String content) {

        commentData = new String[DATA_COUNT];
        commentData[0] = commentId;
        commentData[1] = content;

    }

    public String[] getCommentData() {
        return commentData;
    }

    public String getCommentData(int idx) {
        return commentData[idx];
    }

    public void setCommentData(String[] commentData) {
        this.commentData = commentData;
    }

}
