package agricolab.dao;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("PhotoFirestore")
public class PhotoStorageDAO implements PhotoDAO {

    @Override
    public boolean uploadObject(String objectName, byte[] objectBytes) {
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

    @Override
    public ArrayList<String> listObjects(String objectFolder) {
        Bucket bucket = StorageClient.getInstance().bucket();
        Page<Blob> blobs = bucket.list(Storage.BlobListOption.prefix(objectFolder), Storage.BlobListOption.currentDirectory());
        ArrayList<String> objectList = new ArrayList<>();
        for (Blob blob : blobs.iterateAll()) {
            objectList.add(blob.getName());
        }
        return objectList;
    }
}