package agricolab.dao;

import java.util.ArrayList;

public interface PhotoDAO {
    boolean uploadObject(String objectName, byte[] objectBytes);

    boolean deleteObject(String objName);

    ArrayList<String> listObjects(String objectFolder);
}
