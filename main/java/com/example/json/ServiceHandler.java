package com.example.json;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServiceHandler {
    public static final String GET = "GET";
    public final static String POST = "POST";
    private static final String TAG = ServiceHandler.class.getSimpleName();
    private static final int READ_TIMEOUT = 10000; // milliseconds
    private static final int CONNECT_TIMEOUT = 15000; // milliseconds

    // Constructor
    public ServiceHandler() {

    }

    /**
     * Making service call
     *
     * @param url    - url to make request
     * @param method - http request method (GET or POST)
     * @param params - http request params for POST method
     * @return - response from the server
     */
    public String makeServiceCall(String url, String method, ArrayList<HashMap<String, String>> params) {
        String response = null;
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        try {
            URL requestUrl = new URL(url);
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
            urlConnection.setRequestMethod(method);

            if ("POST".equals(method) && params != null) {
                urlConnection.setDoOutput(true);
                OutputStream outputStream = urlConnection.getOutputStream();
                outputStream.write(getQuery((Map<String, String>) params).getBytes());
                outputStream.flush();
                outputStream.close();
            }

            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                response = convertInputStreamToString(inputStream);
            } else {
                Log.e(TAG, "Failed to fetch data. HTTP Status Code: " + statusCode);
            }

        } catch (MalformedURLException e) {
            Log.e(TAG, "Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IO Exception: " + e.getMessage());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing InputStream: " + e.getMessage());
                }
            }
        }

        return response;
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    private String getQuery(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

