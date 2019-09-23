package com.hjm.common;

import java.util.Set;

/**
 * @author hjm
 */

public class Const {

    public static final String CURRENT_USER = "currentUser";
    public static final String USERNAME = "username";

    public interface Role {
        // 普通用户
        String ROLE_CLIENT = "client";
        // 管理员
        String ROLE_ADMIN = "admin";
    }

}