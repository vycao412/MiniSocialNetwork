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
public class ErrorUserDTO implements Serializable {

    private String errorMail;
    private String errorName;
    private String errorPassword;
    private String errorRePassword;
    private String errorCode;

    public ErrorUserDTO() {
    }

    public ErrorUserDTO(String errorMail, String errorName, String errorPassword, String errorRePassword) {
        this.errorMail = errorMail;
        this.errorName = errorName;
        this.errorPassword = errorPassword;
        this.errorRePassword = errorRePassword;
    }

    public ErrorUserDTO(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMail(String errorMail) {
        this.errorMail = errorMail;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public void setErrorPassword(String errorPassword) {
        this.errorPassword = errorPassword;
    }

    public void setErrorRePassword(String errorRePassword) {
        this.errorRePassword = errorRePassword;
    }

    public String getErrorMail() {
        return errorMail;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getErrorPassword() {
        return errorPassword;
    }

    public String getErrorRePassword() {
        return errorRePassword;
    }

}
