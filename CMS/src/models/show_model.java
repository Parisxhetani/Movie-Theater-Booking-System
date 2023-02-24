package models;

public class show_model
{
    private int id;
    private String showDate;
    private String showTime;
    private String playTitle;
    private String hallName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getPlayTitle() {
        return playTitle;
    }

    public void setPlayTitle(String playTitle) {
        this.playTitle = playTitle;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }
}
