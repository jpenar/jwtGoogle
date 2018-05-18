package gapi;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class App {

	public static void main(String[] args) throws GeneralSecurityException, IOException {

		  
		  
		  GoogleCredential credential =
			        GoogleCredential.fromStream(new FileInputStream("/Users/jpenar/Documents/gapi/My Project 10337-11cdb46c43e3.json"));
			PrivateKey privateKey = credential.getServiceAccountPrivateKey();
			String privateKeyId = credential.getServiceAccountPrivateKeyId();

			long now = System.currentTimeMillis();
			try {
			    Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) privateKey);
			    String signedJwt = JWT.create()
			        .withKeyId(privateKeyId)
			        .withIssuer("gcherwell@inner-virtue-202123.iam.gserviceaccount.com").withClaim("scope", "https://www.googleapis.com/auth/prediction")
			        .withSubject("gcherwell@inner-virtue-202123.iam.gserviceaccount.com")
			        .withAudience("https://www.googleapis.com/oauth2/v4/token")
			        .withIssuedAt(new Date(now))
			        .withExpiresAt(new Date(now + 3600 * 1000L))
			        .sign(algorithm);
			    System.out.println(signedJwt);
			} catch (Exception e){}
	}
}
