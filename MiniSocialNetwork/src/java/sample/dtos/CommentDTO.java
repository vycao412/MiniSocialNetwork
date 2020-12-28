/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author User
 */
public class CommentDTO implements Serializable {

    private int id;
    private int postID;
    private int userID;
    private String content;
    private Date date;
    private int status;

    public CommentDTO() {
    }

    public CommentDTO(int id, int postID, int userID, String content, Date date, int status) {
        this.id = id;
        this.postID = postID;
        this.userID = userID;
        this.content = content;
        this.date = date;
        this.status = status;
    }

    public CommentDTO(int postID, int userID, String content, Date date, int status) {
        this.postID = postID;
        this.userID = userID;
        this.content = content;
        this.date = date;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getPostID() {
        return postID;
    }

    public int getUserID() {
        return userID;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

}
