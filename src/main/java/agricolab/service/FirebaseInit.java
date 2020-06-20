package agricolab.service;

import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;

import java.io.IOException;

@Service
public class FirebaseInit {

    @PostConstruct
    public void init() {
        // Use the application default credentials
        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.getApplicationDefault();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setProjectId("agricolab-un")
                .setStorageBucket("agricolab-un.appspot.com")
                .build();
        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();
    }
}
