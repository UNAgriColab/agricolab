package agricolab.model;

public class ID {
    private int ID;

    public ID(){

    }
    public ID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return Integer.toString(ID);
    }

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }
}
