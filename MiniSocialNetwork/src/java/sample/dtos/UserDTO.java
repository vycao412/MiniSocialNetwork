/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class UserDTO implements Serializable {

    private int userID;
    private String mail;
    private String name;
    private String password;
    private int role;
    private int status;
    private String verifyCode;

    public UserDTO() {
    }

    public UserDTO(int userID, String mail, String name, String password, int role, int status, String verifyCode) {
        this.userID = userID;
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.role = role;
        this.status = status;
        this.verifyCode = verifyCode;
    }

    public UserDTO(String mail, String name, String password, int role, int status) {
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public UserDTO(int userID, String mail, String name, String password, int role, int status) {
        this.userID = userID;
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public UserDTO(String mail, String name, String password, int role, int status, String verifyCode) {
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.role = role;
        this.status = status;
        this.verifyCode = verifyCode;
    }

    public UserDTO(int userID, String mail, String name, int role, int status) {
        this.userID = userID;
        this.mail = mail;
        this.name = name;
        this.role = role;
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

}
