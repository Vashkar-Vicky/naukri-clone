package io.mountblue.naukriproject.service;

import io.mountblue.naukriclone.entity.Company;
import io.mountblue.naukriclone.entity.RssFeed;

import java.util.List;

public interface RssFeedService {
    List<RssFeed.RssItem> fetchRssFeed();
    List<Company> fetchIndustries();
}
