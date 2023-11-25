package entity;

public class Status implements Identifiable {
    private int id;
    private String name;

    public Status() {
    }

    public Status(int id, String role) {
        this.id = id;
        this.name = role;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Status status = (Status) o;
        return id == status.id &&
                name.equals(status.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +  id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
}
