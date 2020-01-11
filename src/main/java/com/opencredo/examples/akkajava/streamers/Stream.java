package com.opencredo.examples.akkajava.streamers;

import java.util.Date;

public class Stream {
    public String user_name;
    public int viewer_count;
    public Date started_at;
    public double streamGrowth;
    public double streamHours;

    public Stream(String user_name, int viewer_count, Date started_at) {
        this.user_name = user_name;
        this.viewer_count = viewer_count;
        this.started_at = started_at;
    }

    public static void calculateStreamGrowth(Stream stream){
        Date currentDate = new Date();
        Date streamDuration = new Date(currentDate.getTime() - stream.started_at.getTime());

        double converter = (1 /3.6 ) * 0.000001;
        double streamHours = streamDuration.getTime() * converter;
        stream.streamGrowth = stream.viewer_count / streamHours;
        stream.streamHours = streamHours;
    }
}
