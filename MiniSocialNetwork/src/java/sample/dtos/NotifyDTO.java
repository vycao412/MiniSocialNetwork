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
public class NotifyDTO implements Serializable {

    private int id;
    private int postID;
    private int userID;
    private Date date;
    private String type;
    private int status;

    public NotifyDTO() {
    }

    public NotifyDTO(int id, int postID, int userID, Date date, String type, int status) {
        this.id = id;
        this.postID = postID;
        this.userID = userID;
        this.date = date;
        this.type = type;
        this.status = status;
    }

    public NotifyDTO(int postID, int userID, Date date, String type, int status) {
        this.postID = postID;
        this.userID = userID;
        this.date = date;
        this.type = type;
        this.status = status;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
