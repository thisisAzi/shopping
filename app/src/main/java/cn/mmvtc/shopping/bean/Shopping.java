package cn.mmvtc.shopping.bean;
//显示商品实例
public class Shopping {
    private String category;
    private int imageSrc;
    private String Message;
    private String  Price;


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }



    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }


}
