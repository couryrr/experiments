package com.dappercloud.https.jks;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class TestTwo {
	//keytool -importkeystore -srckeystore CREDI138_SHA2.p12 -destkeystore cacerts -srcalias "{c8cd79a4-01eb-4d0c-bfdc-e76ac26eb6ce}" 
	static final String TU_JKS = "C:\\Users\\couryrr\\Documents\\Development\\Git\\https-jks\\src\\main\\resources\\cert\\CREDI138_SHA2.p12";
	static final String JKS = "C:\\Users\\couryrr\\Documents\\Development\\Git\\https-jks\\src\\main\\resources\\cert\\cacerts";
	static final String TU_JKS_PWD = "CfnaCB2019";
	static final String TU_URL = "https://netaccess-test.transunion.com/ping";
	
	public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, KeyManagementException {
		// Creating SSLContextBuilder object
		SSLContextBuilder SSLBuilder = SSLContexts.custom();

		// Loading the Keystore file
		File file = new File(JKS);
		SSLBuilder = SSLBuilder.loadTrustMaterial(file, TU_JKS_PWD.toCharArray());

		// Building the SSLContext usiong the build() method
		SSLContext sslcontext = SSLBuilder.build();

		// Creating SSLConnectionSocketFactory object
		SSLConnectionSocketFactory sslConSocFactory = new SSLConnectionSocketFactory(sslcontext,
				new NoopHostnameVerifier());

		// Creating HttpClientBuilder
		HttpClientBuilder clientbuilder = HttpClients.custom();

		// Setting the SSLConnectionSocketFactory
		clientbuilder = clientbuilder.setSSLSocketFactory(sslConSocFactory);

		// Building the CloseableHttpClient
		CloseableHttpClient httpclient = clientbuilder.build();

		// Creating the HttpGet request
		HttpGet httpget = new HttpGet(TU_URL);

		// Executing the request
		HttpResponse httpresponse = httpclient.execute(httpget);

		// printing the status line
		System.out.println(httpresponse.getStatusLine());

		// Retrieving the HttpEntity and displaying the no.of bytes read
		HttpEntity entity = httpresponse.getEntity();
		if (entity != null) {
			System.out.println(EntityUtils.toByteArray(entity).length);
		}

	}

}
