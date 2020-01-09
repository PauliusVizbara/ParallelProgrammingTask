package com.opencredo.examples.akkajava.streamers;

import java.util.Date;

public class Streamer {
    public String user_name;
    public int viewer_count;
    public Date started_at;

    public Streamer(String user_name, int viewer_count, Date started_at) {
        this.user_name = user_name;
        this.viewer_count = viewer_count;
        this.started_at = started_at;
    }
}
