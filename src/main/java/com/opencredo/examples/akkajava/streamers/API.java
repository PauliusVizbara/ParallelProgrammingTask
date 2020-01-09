package com.opencredo.examples.akkajava.streamers;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class API {
    public static String getTopStreamersJson(int amount) throws IOException {
        String keyNew = "7b0054jz5xe4q5bphhg4l4a9px5eaw";

        String key = "tpq3c0xvybm40vblokctejt454ko8w";
        URL url = new URL("https://api.twitch.tv/helix/streams");
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

    public static Streamer[] getTopStreamers(int amount) throws IOException {

        Gson gson = new Gson();
        Streamer[] streamers = new Streamer[amount];
        String json = getTopStreamersJson(amount);
        //streamers = gson.fromJson(json,Streamer[].class);

        TopStreams topStreams = gson.fromJson(json,TopStreams.class);

        System.out.println(streamers);
        for(Streamer streamer : topStreams.data){
            System.out.println(streamer.user_name);
        }
        return streamers;
    }




}
