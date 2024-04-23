package com.developers.serviceApi.service;

public interface SystemUserService {
    /**
     * This method provides login function
     * @param userName
     * @param password
     * @return boolean
     */
    boolean login(String userName, String password);

    /**
     * This method provides initialize system user function
     */
    void initializeUser();
}
