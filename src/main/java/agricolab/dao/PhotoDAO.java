package agricolab.dao;

import java.io.IOException;

public interface PhotoDAO {
    boolean uploadObject(String objectName, String filePath);
}
