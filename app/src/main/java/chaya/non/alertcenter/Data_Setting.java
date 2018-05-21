package chaya.non.alertcenter;

public class Data_Setting {
    private String txt1;
    private int pic;

    public Data_Setting(String txt1, int pic) {
        this.txt1 = txt1;
        this.pic = pic;
    }

    public String getTxt1() {
        return txt1;
    }

    public int getPic() {
        return pic;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
