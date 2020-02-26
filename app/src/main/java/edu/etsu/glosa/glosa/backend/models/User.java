package edu.etsu.glosa.glosa.backend.models;

public class User {

    private static final User ourInstance = new User();
    public static User getInstance() {
        return ourInstance;
    }
    private User() {}

}
