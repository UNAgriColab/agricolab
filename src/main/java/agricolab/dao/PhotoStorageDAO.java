package agricolab.dao;

import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Paths;

@Repository("Firestore")
public class PhotoStorageDAO implements PhotoDAO{

    @Override
    public boolean uploadObject(String objectName, byte[] objectBytes){
        Bucket bucket = StorageClient.getInstance().bucket();
        //BlobId blobId = BlobId.of(bucket.getName(), objectName);
        //BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        try {
            bucket.create(objectName, objectBytes);
            System.out.println(
                "File " + objectName + " uploaded to bucket " + bucket.getName());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}