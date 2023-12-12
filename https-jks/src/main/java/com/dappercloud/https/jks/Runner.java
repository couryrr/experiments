package com.dappercloud.https.jks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.PrivateKeyDetails;
import org.apache.http.ssl.PrivateKeyStrategy;
import org.apache.http.ssl.SSLContexts;

public class Runner {
	static final String TU_URL = "https://netaccess-test.transunion.com/ping";
	static final String TU_JKS = "C:\\Users\\couryrr\\Downloads\\CREDI138_SHA2.p12";
	static final String TU_JKS_PWD = "CfnaCB2019";
	static final String TU_JKS_ALIAS = "{c8cd79a4-01eb-4d0c-bfdc-e76ac26eb6ce}";

	public static void main(String[] args) {
		System.out.println("DapperCloud 2-way / mutual SSL-authentication test");

		try {
			KeyStore identityKeyStore = KeyStore.getInstance("jks");
			FileInputStream identityKeyStoreFile = new FileInputStream(new File(TU_JKS));
			identityKeyStore.load(identityKeyStoreFile, TU_JKS_PWD.toCharArray());

			KeyStore trustKeyStore = KeyStore.getInstance("jks");
			FileInputStream trustKeyStoreFile = new FileInputStream(new File("C:\\Program Files\\Java\\jdk-11.0.2\\lib\\security\\cacerts"));
			trustKeyStore.load(trustKeyStoreFile, "changeit".toCharArray());

			SSLContext sslContext = SSLContexts.custom()
					// load identity keystore
					.loadKeyMaterial(identityKeyStore, TU_JKS_PWD.toCharArray(), new PrivateKeyStrategy() {
						public String chooseAlias(Map<String, PrivateKeyDetails> aliases, Socket socket) {
							return "tuna_client";
						}
					})
					// load trust keystore
					.loadTrustMaterial(trustKeyStore, null).build();
			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
					new String[] { "TLSv1.2", "TLSv1.1" }, null,
					SSLConnectionSocketFactory.getDefaultHostnameVerifier());

			CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();

			// Call a SSL-endpoint
			callEndPoint(client, TU_URL);
		} catch (Exception ex) {
			System.out.println("Boom, we failed 1: " + ex);
			ex.printStackTrace();
		}
	}

	private static void callEndPoint(CloseableHttpClient aHTTPClient, String aEndPointURL) {

		try {
			System.out.println("Calling URL: " + aEndPointURL);
			HttpGet get = new HttpGet(aEndPointURL);
			// post.setHeader("Accept", "application/json");
			// post.setHeader("Content-type", "application/json");

			HttpResponse response = aHTTPClient.execute(get);

			int responseCode = response.getStatusLine().getStatusCode();
			System.out.println("Response Code: " + responseCode);
			System.out.println("Content:-\n");
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception ex) {
			System.out.println("Boom, we failed 2: " + ex);
			ex.printStackTrace();
		}

	}

}
