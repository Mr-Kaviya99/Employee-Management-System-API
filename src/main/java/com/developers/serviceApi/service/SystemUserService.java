package com.developers.serviceApi.service;

public interface SystemUserService {
    boolean login(String userName, String password);

    void initializeUser();
}
