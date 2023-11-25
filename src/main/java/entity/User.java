package entity;

import java.io.Serializable;

public class User implements Identifiable, Serializable {
    private int id;
    private String userName;
    private String email;
    private String password;
    private int roleId;
    private int statusId;


    public User() {}

    public User(int id, String userName, String email, String password, int roleId, int statusId) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.statusId = statusId;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        User user = (User) o;
        return id == user.id &&
                userName.equals(user.userName) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                roleId == user.roleId &&
                statusId == user.statusId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +  id;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result +  roleId;
        result = prime * result +  statusId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("User{");
        result.append("id=").append(id);
        result.append(", userName=").append(userName);
        result.append(", email='").append(email).append('\'');
        result.append(", password='").append(password).append('\'');
        result.append(", roleId=").append(roleId);
        result.append(", statusId=").append(statusId);
        result.append('}');
        return result.toString();
    }
}
