package io.mountblue.naukriproject.service.impl;

import io.mountblue.naukriclone.entity.Company;
import io.mountblue.naukriclone.entity.Industries;
import io.mountblue.naukriclone.entity.RssFeed;
import io.mountblue.naukriclone.service.RssFeedService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RssFeedServiceImpl implements RssFeedService {


    @Override
    public List<RssFeed.RssItem> fetchRssFeed() {
        RestTemplate restTemplate = new RestTemplate();
        final String RSS_FEED_URL = "https://rss.app/feeds/v1.1/tbEH5CQWOBHc45jy.json";
        Map<String, Object> response = restTemplate.getForObject(RSS_FEED_URL, Map.class);

        List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");
        List<RssFeed.RssItem> rssItems = new ArrayList<>();

        for (Map<String, Object> item : items) {
            RssFeed.RssItem rssItem = new RssFeed.RssItem();
            rssItem.setId((String) item.get("id"));
            rssItem.setUrl((String) item.get("url"));
            rssItem.setTitle((String) item.get("title"));
            rssItem.setContentText((String) item.get("content_text"));
            rssItem.setDatePublished((String) item.get("date_published"));
            rssItem.setImage((String) item.get("image"));
            rssItems.add(rssItem);
        }

        return rssItems;
    }

    @Override
    public List<Company> fetchIndustries() {
        String rssUrl = "https://rss.app/feeds/v1.1/tw0vIdTDaRHVITXN.json";
        RestTemplate restTemplate = new RestTemplate();
        Industries industries = restTemplate.getForObject(rssUrl, Industries.class);

        if (industries != null && industries.getItems() != null) {
            return industries.getItems();
        }
        return List.of(); // Return an empty list if no jobs are available
    }

}
