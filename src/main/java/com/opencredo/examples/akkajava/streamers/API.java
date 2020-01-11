package com.opencredo.examples.akkajava.streamers;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class API {


    /**
     * Metodas gauti Twitch API atsiųstų translacijų JSON duomenis
     * @param amount Transliacijų kiekis.
     * @return JSON String pavidalu
     */
    public static String getTopStreamsJson(int amount) throws IOException {
        String keyNew = "7b0054jz5xe4q5bphhg4l4a9px5eaw";

        String key = "tpq3c0xvybm40vblokctejt454ko8w";
        URL url = new URL("https://api.twitch.tv/helix/streams?first="+amount);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Client-ID", key);

        String a = con.getResponseMessage();

        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        return sb.toString();
    }

    /**
     * Metodas gauti Twitch API atsiųstas transliacijas Stream masyvo pavidalu
     * @param amount Transliacijų kiekis.
     * @return Transliacijų masyvas
     */
    public static Stream[] getTopStreams(int amount) throws IOException {

        Gson gson = new Gson();
        Stream[] streams = new Stream[amount];
        String json = getTopStreamsJson(amount);

        TopStreams topStreams = gson.fromJson(json,TopStreams.class);

        int index = 0;
        for(Stream stream : topStreams.data){
            streams[index++] = stream;
        }
        return streams;
    }




}
