package by.it_academy.jd2.Mk_JD2_82_21_employees.model;

public class PageableFilter {
    private String page;
    private String size;

    public PageableFilter(String page, String size) {
        this.page = page;
        this.size = size;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
