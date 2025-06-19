package com.inna.book_management_api.models;

public interface Constants {
    int MIN_TITLE_LENGTH = 1;
    int MAX_TITLE_LENGTH = 100;
    int MIN_AUTHOR_LENGTH = 1;
    int MAX_AUTHOR_LENGTH = 50;
    String AUTHOR_NAME_PATTERN = "^[a-zA-Z\\s.-]+$";
    int MIN_PUBLISHED_YEAR = 1500;
}
