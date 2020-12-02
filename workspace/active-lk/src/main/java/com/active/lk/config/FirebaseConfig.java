package com.active.lk.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseConfig {
	
	@PostConstruct
    public void initialize() throws IOException {
		String env = System. getenv("GOOGLE_APPLICATION_CREDENTIALS");
		Resource resource = new ClassPathResource("activelk-e5940-firebase-adminsdk-7veua-f65efe5ca7.json");
		InputStream input = resource.getInputStream();
		GoogleCredentials credentials = GoogleCredentials.fromStream(input);
				   
		FirebaseOptions options = new FirebaseOptions.Builder()
			    .setCredentials(credentials)
			    .setDatabaseUrl("https://activelk-76eeb.firebaseio.com")
			    .build();

			FirebaseApp.initializeApp(options);
	}

}
