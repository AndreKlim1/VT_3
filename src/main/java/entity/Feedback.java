package entity;

import java.io.Serializable;

public class Feedback implements Identifiable, Serializable {
    private int id;
    private int rating;
    private String content;
    private int userId;
    private int movieId;

    public Feedback() {}

    public Feedback(int id, int rating, String content, int userId, int movieId) {
        this.id = id;
        this.rating = rating;
        this.content = content;
        this.userId = userId;
        this.movieId = movieId;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId( int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;
        return id == feedback.id &&
                rating == feedback.rating &&
                content.equals(feedback.content) &&
                userId == feedback.userId &&
                movieId == feedback.movieId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +  id;
        result = prime * result +  rating;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + userId;
        result = prime * result +  movieId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("Comment{");
        result.append("id=").append(id);
        result.append(", rating=").append(rating);
        result.append(", content='").append(content).append('\'');
        result.append(", userId='").append(userId).append('\'');
        result.append(", movieId=").append(movieId);
        result.append('}');
        return result.toString();
    }

}
