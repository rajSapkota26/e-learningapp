package com.raj.eLearning.model;

import java.util.Date;

public class WithdrawRequest {

    private String userId;
    private String emailAddress;
    private String phoneAddress;
    private String requestedBy;

    public WithdrawRequest() {

    }

    public String getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(String phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public WithdrawRequest(String userId, String emailAddress, String phoneAddress, String requestedBy) {
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.phoneAddress = phoneAddress;
        this.requestedBy = requestedBy;
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }


    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
