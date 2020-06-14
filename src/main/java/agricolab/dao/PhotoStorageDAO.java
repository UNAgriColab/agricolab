package agricolab.dao;

import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class PhotoStorageDAO implements PhotoDAO{

    @Override
    public boolean uploadObject(String objectName, String filePath){
        Bucket bucket = StorageClient.getInstance().bucket();
        BlobId blobId = BlobId.of(bucket.getName(), objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        //bucket.create(blobInfo, Files.readAllBytes());

        System.out.println(
            "File " + filePath + " uploaded to bucket " + bucket.getName() + " as " + objectName);
    }
}