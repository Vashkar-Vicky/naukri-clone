package io.mountblue.naukriproject.controller;


import io.mountblue.naukriclone.entity.Company;
import io.mountblue.naukriclone.entity.RssFeed;
import io.mountblue.naukriclone.service.RssFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RssFeedController {

    @Autowired
    private RssFeedService rssFeedService;

//    @GetMapping("/rss-feed")
//    public List<RssFeed.RssItem> getRssFeed() {
//        return rssFeedService.fetchRssFeed();
//    }

    @GetMapping("/rss-feed-page")
    public String getRssFeedPage(Model model) {
        List<RssFeed.RssItem> feedItems = rssFeedService.fetchRssFeed();
        model.addAttribute("feed", feedItems);
        return "rss-feed";
    }

    @GetMapping("/trends")
    public String showJobs(Model model) {
        List<Company> jobItems = rssFeedService.fetchIndustries();
        model.addAttribute("jobItems", jobItems);
        return "trendsNews";
    }
}
