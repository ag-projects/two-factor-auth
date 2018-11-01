package com.gharibi.service;

import com.gharibi.web.model.User;

public interface UserService {
    /**
     * @param user
     * @return
     * @throws Exception
     */
    User registerNewUser(User user) throws Exception;
}
