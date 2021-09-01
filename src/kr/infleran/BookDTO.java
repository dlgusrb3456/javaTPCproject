package kr.infleran;

public class BookDTO {
    private String title;
    private  int price;
    private String company;
    private  int page;
    public BookDTO(){

    }

    public BookDTO(String title, int price,String company,int page) {
        this.title=title;
        this.price=price;
        this.page=page;
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", company='" + company + '\'' +
                ", page=" + page +
                '}';
    }
}
