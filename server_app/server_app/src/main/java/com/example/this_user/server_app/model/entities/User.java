package com.example.this_user.ourproject5778_4711_9075.model.entities;

/**
 * Created by This_user on 08/04/2018.
 */

public class User
{
    private String userName;
    private String password;
    private IdentificationQuestions identificationQuestions;
    private String ans;

    /*
    get and set
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password1) {
        password = password1;
    }

    public IdentificationQuestions getIdentificationQuestions() {
        return identificationQuestions;
    }

    public void setIdentificationQuestions(IdentificationQuestions identificationQuestions) {
        this.identificationQuestions = identificationQuestions;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    /*
        constructors
         */
    public User(String userName, String password, IdentificationQuestions identificationQuestions, String ans) {
        this.userName = userName;
        this.password = password;
        this.identificationQuestions = identificationQuestions;
        this.ans = ans;
    }
    public User() {
        this.userName = "";
        password = "";
        identificationQuestions = IdentificationQuestions.your_favorite_food;
        ans = "";
    }
    public User(User user) {
        this.userName = user.userName;
        password = user.password;
        identificationQuestions = user.getIdentificationQuestions();
        ans = user.ans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userName != null ? !userName.equals(user.userName) : user.userName != null)
            return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        if (identificationQuestions != user.identificationQuestions) return false;
        return ans != null ? ans.equals(user.ans) : user.ans == null;

    }

}
