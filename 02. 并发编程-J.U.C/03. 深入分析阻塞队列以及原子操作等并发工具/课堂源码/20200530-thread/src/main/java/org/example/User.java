package org.example;

/**
 * 风骚的Mic 老师
 * create-date: 2020/5/30-20:51
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
