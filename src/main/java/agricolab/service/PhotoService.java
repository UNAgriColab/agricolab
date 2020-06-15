package agricolab.service;

import agricolab.dao.PhotoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class PhotoService {

    private final PhotoDAO photoDAO;

    @Autowired
    public PhotoService(@Qualifier("PhotoFirestore") PhotoDAO photoDAO) {
        this.photoDAO = photoDAO;
    }

    public boolean uploadPhoto(String offer, String originalFilename, byte[] fileBytes) {
        String objName = "photos/" + offer + "/" + originalFilename;
        return photoDAO.uploadObject(objName, fileBytes);
    }

    public ArrayList<String> listPhotos(String offer) {
        String photoFolder = "photos/" + offer + "/";
        return photoDAO.listObjects(photoFolder);
    }
}
