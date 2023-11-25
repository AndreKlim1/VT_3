package entity;

import java.io.Serializable;

public class Movie implements Identifiable, Serializable {
    private int id;
    private String name;
    private String description;
    private String image;
    private double averageRating;

    public Movie() {}

    public Movie(int id, String name, String description, String image, double averageRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.averageRating = averageRating;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image) {
        this.image = image;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;
        return id == movie.id &&
                name.equals(movie.name) &&
                description.equals(movie.description) &&
                image.equals(movie.image) &&
                averageRating == movie.averageRating;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +  id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result +  (int)averageRating;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("Movie{");
        result.append("id=").append(id);
        result.append(", name=").append(name);
        result.append(", description='").append(description).append('\'');
        result.append(", image='").append(image).append('\'');
        result.append(", averageRating=").append(averageRating);
        result.append('}');
        return result.toString();
    }

}
