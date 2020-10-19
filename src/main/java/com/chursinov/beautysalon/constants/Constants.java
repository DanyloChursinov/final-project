package com.chursinov.beautysalon.constants;

public abstract class Constants {

    private Constants() {
    }

    public static class Attributes {
        public static final String CONNECTION_SOURCE = "connectionSource";
        public static final String CURRENT_LOCALE = "currentLocale";
    }

    public static class Settings {
        public static final String APPLICATION_PROPERTIES = "application.properties";
    }

    public static class GetActions{
        public static final String REVIEW = "review";
        public static final String ADMIN_PROFILE = "admin-profile";
        public static final String MASTER_PROFILE = "master-profile";
        public static final String CLIENT_PROFILE = "client-profile";
        public static final String SERVICES = "service";
    }

    public static class Pages {
        public static final String INDEX_PAGE = "/index.jsp";
        public static final String SIGNIN_PAGE = "/signin.jsp";
        public static final String SIGNUP_PAGE = "/signup.jsp";
        public static final String SERVICE_PAGE = "/service.jsp";
        public static final String REVIEW_PAGE = "/reviews.jsp";
        public static final String ADMIN_PROFILE_PAGE = "/admin-profile.jsp";
        public static final String MASTER_PROFILE_PAGE = "/master-profile.jsp";
        public static final String CLIENT_PROFILE_PAGE = "/client-profile.jsp";
    }

    public static class Errors {
        public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain data source.";
        public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain connection.";
        public static final String ERROR = "error";
        public static final String INCORRECT_EMAIL_OR_PASSWORD = "Incorrect email or password. Please retry.";
        public static final String USER_IS_ALREADY_EXIST = "User is already exist";
        public static final String SOMETHING_GOES_WRONG = "Something goes wrong. User not added. Try later!";
        public static final String TIME_IS_ALREADY_TAKEN = "Sorry, the time you selected is already taken, please change the time. Booked master's time for your date:";
        public static final String SET_MINUTES_IN_CORRECT = "Error. Please set minutes in correct value(Correct values are: '30' or '00'). Example of correct booking time: '2020.01.01 12:30' or '2020.01.01 12:00'";
    }

}
