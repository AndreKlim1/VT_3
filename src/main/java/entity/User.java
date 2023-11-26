package entity;

import java.io.Serializable;

public class User implements Identifiable, Serializable {
    private int id;
    private String nickname;
    private String email;
    private String password;
    private int score;
    private boolean banned;
    private int roleId;
    private int statusId;



    public User() {}

    public User(int id, String nickname, String email, String password, int score, boolean banned, int roleId, int statusId) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.score = score;
        this.roleId = roleId;
        this.statusId = statusId;
        this.banned = banned;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean getBanned() { return banned; }
    public void setBanned(boolean banned) { this.banned = banned; }

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
                nickname.equals(user.nickname) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                roleId == user.roleId &&
                statusId == user.statusId &&
                score == user.score &&
                banned == user.banned;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +  id;
        result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + score;
        result = prime * result +  roleId;
        result = prime * result +  statusId;
        result = prime * result + (banned ? 1 : 0) ;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("User{");
        result.append("id=").append(id);
        result.append(", nickname=").append(nickname);
        result.append(", email='").append(email).append('\'');
        result.append(", password='").append(password).append('\'');
        result.append(", score='").append(score).append('\'');
        result.append(", banned='").append(banned);
        result.append(", roleId=").append(roleId);
        result.append(", statusId=").append(statusId);
        result.append('}');
        return result.toString();
    }
}
