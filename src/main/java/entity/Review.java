package entity;

import entity.Feedback;
import entity.Status;
import entity.User;

import java.io.Serializable;

public class Review implements Identifiable, Serializable {

    private int id;
    private Feedback feedback;
    private User user;
    private Status status;

    public Review() {}
    public Review(int id, Feedback feedback, User user, Status status){
        this.id = id;
        this.feedback = feedback;
        this.user = user;
        this.status = status;
    }
    @Override
    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public User getUser(){return user;}

    public void setUser(User user){this.user = user;}

    public Feedback getFeedback(){return feedback;}

    public void setFeedback(Feedback feedback){this.feedback = feedback;}

    public Status getStatus(){return status;}

    public void setStatus(Status status){this.status = status;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Review review = (Review) o;
        return id == review.id &&
                user.equals(review.user) &&
                feedback.equals(review.feedback) &&
                status.equals(review.status);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +  id;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

}
