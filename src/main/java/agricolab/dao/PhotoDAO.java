package agricolab.dao;

import java.util.ArrayList;

public interface PhotoDAO {
    boolean uploadObject(String objectName, byte[] objectBytes);

    ArrayList<String> listObjects(String objectFolder);
}
