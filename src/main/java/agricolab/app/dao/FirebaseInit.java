package agricolab.app.dao;

import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FirebaseInit {

    @PostConstruct
    public void init() {
        try {
            FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/agricolab-un-firebase-adminsdk-49uv0-20f565f9e1.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://agricolab-un.firebaseio.com")
                .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
