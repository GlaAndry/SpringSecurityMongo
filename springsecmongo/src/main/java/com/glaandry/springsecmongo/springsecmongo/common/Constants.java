package com.glaandry.springsecmongo.springsecmongo.common;

public class Constants {

    public static final String DEFAULT_ROLE = "ROLE_USER";
    public static final String MODERATOR_ROLE = "ROLE_MODERATOR";
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    public static final String[] ADMIN_ACCESS = {"ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER"};
    public static final String[] MODERATOR_ACCESS = {"ROLE_MODERATOR", "ROLE_USER"};

}
