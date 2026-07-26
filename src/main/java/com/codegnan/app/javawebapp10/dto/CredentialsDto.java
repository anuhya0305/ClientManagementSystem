package com.codegnan.app.javawebapp10.dto;

public class CredentialsDto {
    private int credentialsId;
    private String username;
    private String loginPassword;

    public CredentialsDto() {
    }

    public CredentialsDto(int credentialsId, String username, String loginPassword) {
        this.credentialsId = credentialsId;
        this.username = username;
        this.loginPassword = loginPassword;
    }

    public int getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(int credentialsId) {
        this.credentialsId = credentialsId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public String toString() {
        return "CredentialsDto{" +
                "credentialsId=" + credentialsId +
                ", username='" + username + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                '}';
    }
}