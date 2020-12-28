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
public class EmotionDTO implements Serializable {

    private int id;
    private int postID;
    private int userID;
    private boolean isLike;
    private boolean isDislike;
    private Date date;

    public EmotionDTO() {
    }

    public EmotionDTO(int id, int postID, int userID, boolean isLike, boolean isDislike, Date date) {
        this.id = id;
        this.postID = postID;
        this.userID = userID;
        this.isLike = isLike;
        this.isDislike = isDislike;
        this.date = date;
    }

    public EmotionDTO(int postID, int userID, boolean isLike, boolean isDislike, Date date) {
        this.postID = postID;
        this.userID = userID;
        this.isLike = isLike;
        this.isDislike = isDislike;
        this.date = date;
    }

    public EmotionDTO(int postID, int userID, boolean isLike, boolean isDislike) {
        this.postID = postID;
        this.userID = userID;
        this.isLike = isLike;
        this.isDislike = isDislike;
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

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    public void setIsDislike(boolean isDislike) {
        this.isDislike = isDislike;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public boolean isIsLike() {
        return isLike;
    }

    public boolean isIsDislike() {
        return isDislike;
    }

    public Date getDate() {
        return date;
    }

}
