package org.template.util;

public class Constants {
    public static class Property {
        public static final String HIBERNATE_CONNECTION_DRIVER_CLASS = "hibernate.connection.driver_class";
        public static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";
        public static final String HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username";
        public static final String HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password";
        public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
        public static final String HIBERNATE_DIALECT = "hibernate.dialect";
        public static final String HIBERNATE_CONNECTION_CHARSET = "hibernate.connection.charSet";
        public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
        public static final String HIBERNATE_CONNECTION_RELEASE_MODE = "hibernate.connection.release_mode";
        public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
        public static final String HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
        public static final String HIBERNATE_CONNECTION_POOL_SIZE = "hibernate.connection.pool_size";
        public static final String HIBERNATE_TRANSACTION_FACTORY_CLASS = "hibernate.transaction.factory_class";
        public static final String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";

        public static final String LANGUAGE_DEFAULT = "language.default";
        public static final String COUNTRY_DEFAULT = "country.default";
    }

    public static class RequestParam {
        public static final String ERROR = "error";
        public static final String LOGOUT = "logout";
    }

    public static class ModelAttribute {
        public static final String ERROR = "error";
        public static final String LOGOUT = "logout";
    }

    public static class Url {
        public static final String REDIRECT = "redirect:";
        public static final String RESOURCES = "/resources";
        public static final String ROOT = "/";
        public static final String LOGIN = "/login";
        public static final String SIGN_UP = "/signUp";
        public static final String ADMIN = "/admin";
        public static final String USER = "/user";
        public static final String ERROR_403 = "/403";
    }

    public static class View {
        public static final String LOGIN = "loginView";
        public static final String SIGN_UP = "signUpView";
        public static final String MAIN = "mainView";
        public static final String ADMIN = "adminView";
        public static final String USER = "userView";
        public static final String ERROR_403 = "error403View";
        public static final String ERROR_ALL = "errorAllView";
    }

    public static class Messages {
        public static final String PAGE_LOGIN_ERROR_INVALID_USERNAME_AND_PASSWORD = "page.login.error.InvalidUsernameAndPassword";
        public static final String PAGE_LOGIN_MESSAGE_LOGOUT = "page.login.message.logout";
        public static final String PAGE_SIGN_UP_ERROR_EMAIL_EXISTS = "page.signUp.error.emailExists";
    }
}
