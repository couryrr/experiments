package com.dappercloud.https.jks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

public class Test {

	static final String TU_JKS = "C:\\Users\\couryrr\\Documents\\Development\\Git\\https-jks\\src\\main\\resources\\cert\\CREDI138_SHA2.p12";
	static final String TU_JKS_PWD = "CfnaCB2019";
	static final String TU_URL = "https://netaccess-test.transunion.com/ping";

	public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, KeyManagementException, UnrecoverableKeyException {
		System.out.println("starting test");
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(new FileInputStream(new File(TU_JKS)), TU_JKS_PWD.toCharArray());
		System.out.println("ks created end test");

		System.out.println("Ssl conext test");

		SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(ks, TU_JKS_PWD.toCharArray())
				.loadTrustMaterial(null, new TrustSelfSignedStrategy())
				.build();

		System.out.println("Ssl conext start test");

		SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(sslContext);

		System.out.println("Ssl conext end test");

		System.out.println("Starting registry");

		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https", sslConnectionFactory).register("http", new PlainConnectionSocketFactory()).build();

		BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(registry);

		System.out.println("end registry");

		HttpClient client = HttpClients.custom().setConnectionManager(connManager)
				.setSSLSocketFactory(sslConnectionFactory)
				.build();
		
		HttpGet get = new HttpGet(TU_URL);
		
		HttpResponse response = client.execute(get);

	}

}
