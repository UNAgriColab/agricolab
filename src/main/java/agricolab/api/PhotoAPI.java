package agricolab.api;

import agricolab.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/photo")
public class PhotoAPI {

    private final PhotoService photoService;

    @Autowired
    public PhotoAPI(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/upload/{offer}")
    public boolean postPhoto(@PathVariable String offer, @RequestParam("file") MultipartFile file){
        return photoService.uploadPhoto(offer, file);
    }
}
