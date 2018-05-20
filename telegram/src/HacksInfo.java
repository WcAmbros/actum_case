public class HacksInfo {
    private String name;
    private String date;
    private String info;
    private String place;
    private String urlImg;

    HacksInfo(String name, String date, String info, String place,String urlImg)
    {
        this.name = name;
        this.date = date;
        this.info = info;
        this.place = place;
        this.urlImg = urlImg;
    }
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getInfo() {
        return info;
    }

    public String getPlace() {
        return place;
    }

    public String getUrlImg() {
        return urlImg;
    }
}
