package agricolab.api;

import agricolab.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/photo")
public class PhotoAPI {

    private final PhotoService photoService;

    @Autowired
    public PhotoAPI(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping(value = "/upload/{offer}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean postPhoto(@PathVariable String offer, @RequestParam("file") MultipartFile file){
        try {
            byte[] fileBytes = file.getBytes();
            return photoService.uploadPhoto(offer, file.getOriginalFilename(), fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/list/{offer}")
    public ArrayList<String> listPhotos(@PathVariable String offer){
        return photoService.listPhotos(offer);
    }
}
