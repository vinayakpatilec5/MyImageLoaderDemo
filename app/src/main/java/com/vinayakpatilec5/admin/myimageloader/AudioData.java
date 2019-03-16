package com.vinayakpatilec5.admin.myimageloader;

public class AudioData {
    private String url;
    private String trackName;
    private String artistName;

    public AudioData(String url, String trackName, String artistName) {
        this.url = url;
        this.trackName = trackName;
        this.artistName = artistName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
