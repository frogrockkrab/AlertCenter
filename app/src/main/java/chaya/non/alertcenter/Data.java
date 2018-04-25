package chaya.non.alertcenter;

/**
 * Created by admin on 4/26/2018.
 */

public class Data {
    private String txt1,txt2;
    private int pic;

    public Data(String txt1, String txt2, int pic) {
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.pic = pic;
    }

    public String getTxt1() {
        return txt1;
    }

    public String getTxt2() {
        return txt2;
    }

    public int getPic() {
        return pic;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
