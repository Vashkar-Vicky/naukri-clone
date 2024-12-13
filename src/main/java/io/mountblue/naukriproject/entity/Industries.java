package io.mountblue.naukriproject.entity;

import java.util.List;

public class Industries {
    private String title;
    private String home_page_url;
    private String feed_url;
    private List<Company> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHome_page_url() {
        return home_page_url;
    }

    public void setHome_page_url(String home_page_url) {
        this.home_page_url = home_page_url;
    }

    public String getFeed_url() {
        return feed_url;
    }

    public void setFeed_url(String feed_url) {
        this.feed_url = feed_url;
    }

    public List<Company> getItems() {
        return items;
    }

    public void setItems(List<Company> items) {
        this.items = items;
    }
}
