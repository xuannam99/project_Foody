package hcmute.edu.vn.nhom.project_foody.myclass;

public class Thuc_Don {
    private int mathudon;
    private int maloaimon;
    private String tenthucdon;

    public Thuc_Don(int mathudon, int maloaimon, String tenthucdon) {
        this.mathudon = mathudon;
        this.maloaimon = maloaimon;
        this.tenthucdon = tenthucdon;
    }

    public int getMathudon() {
        return mathudon;
    }

    public void setMathudon(int mathudon) {
        this.mathudon = mathudon;
    }

    public int getMaloaimon() {
        return maloaimon;
    }

    public void setMaloaimon(int maloaimon) {
        this.maloaimon = maloaimon;
    }

    public String getTenthucdon() {
        return tenthucdon;
    }

    public void setTenthucdon(String tenthucdon) {
        this.tenthucdon = tenthucdon;
    }
}
