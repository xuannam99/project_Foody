package hcmute.edu.vn.nhom.project_foody.myclass;

public class Mon_An {
    private String name_food;
    private float price_food;

    public Mon_An(String name_food, float price_food) {
        this.name_food = name_food;
        this.price_food = price_food;
    }

    public String getName_food() {
        return name_food;
    }

    public float getPrice_food() {
        return price_food;
    }

    public void setName_food(String name_food) {
        this.name_food = name_food;
    }

    public void setPrice_food(float price_food) {
        this.price_food = price_food;
    }
}
