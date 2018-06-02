package com.example.admin.myandroidappdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmsSender {
    public String sendSms(String phoneNo){
        try {
            // Construct data
            String apiKey = "apikey=" + "4e6p8SSx1Ng-IeXkVcX8TxlYJOMGVtAgK46iEg1Fcy";
            String message = "&message=" + "Thank you for registration of Image Tracker Calories Application.  Written by Phodchara Khiawsaart";
            String sender = "&sender=" + "Image Tracker Application ";
            String numbers = "&numbers=" + phoneNo;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            return "Error "+e;
        }
    }

}
