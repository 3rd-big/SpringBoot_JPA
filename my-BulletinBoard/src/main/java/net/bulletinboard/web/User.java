package net.bulletinboard.web;

/**
 * 필드명이 중요한게 아님.. set[여기] form.html 에서 넘어온 값
 * 자동으로 값을 세팅하는 부분은 추후 스프링 내용 공부할 것
 */
public class User {
    private String userId;
    private String password;
    private String name;
    private String email;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
