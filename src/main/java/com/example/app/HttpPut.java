package com.example.app;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class HttpPut {

	public static void main(String[] args) throws IOException {
		
		try {
            Random random = new Random();
            URL url = new URL("http://localhost:8087/getListaRasaPasa");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
            
            osw.write(String.format("{\"pos\":{\"left\":%1$d,\"top\":%2$d}}", random.nextInt(30), random.nextInt(20)));
            
            osw.flush();
            osw.close();
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

