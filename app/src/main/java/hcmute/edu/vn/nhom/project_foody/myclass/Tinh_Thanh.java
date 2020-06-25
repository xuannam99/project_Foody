package hcmute.edu.vn.nhom.project_foody.myclass;

public class Tinh_Thanh {
    private Boolean isSelectded;
    private String name_place;
    private int matinh;

    public Tinh_Thanh(Boolean isSelectded, String name_place, int matinh) {
        this.isSelectded = isSelectded;
        this.name_place = name_place;
        this.matinh = matinh;
    }

    public int getMatinh() {
        return matinh;
    }

    public void setMatinh(int matinh) {
        this.matinh = matinh;
    }

    public Boolean getSelectded() {
        return isSelectded;
    }

    public String getName_place() {
        return name_place;
    }

    public void setSelectded(Boolean selectded) {
        isSelectded = selectded;
    }

    public void setName_place(String name_place) {
        this.name_place = name_place;
    }
}
