package app.firebase.web.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Configuration
public class FirebaseConfig {
    
    @Bean
    public Firestore firebaseConfiguration() throws IOException {

        FileInputStream serviceAccount = new FileInputStream("./KeyService.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))   
                .build();

        

        FirebaseApp app = null;

        if(FirebaseApp.getApps().isEmpty()){
            app = FirebaseApp.initializeApp(options,"firebase-proyecto-1");
        }
        else{
            app = FirebaseApp.getApps().get(0);
        }

       return FirestoreClient.getFirestore(app);
       
    }

}
