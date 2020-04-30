package agricolab.model;

public class ID {
    private int id;

    public ID(){

    }
    public ID(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
