package agricolab.dao;

public interface PhotoDAO {
    boolean uploadObject(String objectName, byte[] objectBytes);
}
