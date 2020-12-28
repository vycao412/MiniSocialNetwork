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
public class ArticleDTO implements Serializable {

    private int postID;
    private String title;
    private String description;
    private String image;
    private Date date;
    private int userID;
    private int status;
    private int numLike;
    private int numDislike;

    public ArticleDTO() {
    }

    public ArticleDTO(int postID, String title, String description, String image, Date date, int userID, int status, int numLike, int numDislike) {
        this.postID = postID;
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = date;
        this.userID = userID;
        this.status = status;
        this.numLike = numLike;
        this.numDislike = numDislike;
    }

    public ArticleDTO(String title, String description, String image, Date date, int userID, int status, int numLike, int numDislike) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.date = date;
        this.userID = userID;
        this.status = status;
        this.numLike = numLike;
        this.numDislike = numDislike;
    }

    public ArticleDTO(int postID, int numLike, int numDislike) {
        this.postID = postID;
        this.numLike = numLike;
        this.numDislike = numDislike;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumLike() {
        return numLike;
    }

    public void setNumLike(int numLike) {
        this.numLike = numLike;
    }

    public int getNumDislike() {
        return numDislike;
    }

    public void setNumDislike(int numDislike) {
        this.numDislike = numDislike;
    }

}
