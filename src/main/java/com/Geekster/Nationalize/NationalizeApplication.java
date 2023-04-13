package com.Geekster.Nationalize;

import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@SpringBootApplication

public class NationalizeApplication {
	@SneakyThrows
	public static void main(String[] args) {
		URL getUrl = new URL( "https://api.nationalize.io/?name=nathaniel");
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();

		connection.setRequestMethod("GET");
		int responseCode = connection.getResponseCode();
		if(responseCode == 200) {
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder jsonResponseData = new StringBuilder();
			String readLine = null;

			while ((readLine = br.readLine()) != null) {
				jsonResponseData.append(readLine);
			}
			br.close();
			JSONObject masterData = new JSONObject(jsonResponseData.toString());
			System.out.println(masterData);
		}else{
			System.out.println("Resource not found");
		}
	}
}
