package com.opencredo.examples.akkajava.streamers;

//Duomenų struktūra, skirta laikyti iš Twitch gautas transliacijas
public class TopStreams {

    public Stream[] data;

    public TopStreams(Stream[] data) {
        this.data = data;
    }
}
