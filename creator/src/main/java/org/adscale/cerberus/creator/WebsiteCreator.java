package org.adscale.cerberus.creator;


import org.adscale.cerberus.model.Website;
import org.adscale.market.api.domain.WebsiteCreation;
import org.adscale.market.api.service.website.WebsiteService;
import org.adscale.market.vocabulary.Demographic;
import org.adscale.market.vocabulary.WebsiteCategory;
import org.adscale.market.vocabulary.identity.AccountId;
import org.adscale.market.vocabulary.identity.WebsiteId;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@Component
public class WebsiteCreator {

    @Resource
    WebsiteService websiteService;

    @Resource
    PropertyPlaceholderConfigurer properties;

    public WebsiteId createWebsite(String json) {
        Website website = Website.parseJson(json);
        WebsiteCreation.Builder websiteCreation = WebsiteCreation.builder();
        websiteCreation.name(website.getName());
        websiteCreation.accountId(AccountId.of(Long.parseLong(website.getAccountId())));
        websiteCreation.adexEnabeled(website.isAdexEnabled());
        websiteCreation.nuggadEnabled(website.isNuggadEnabled());
        websiteCreation.category(WebsiteCategory.valueOf(website.getCategory()));
        websiteCreation.demographicAges(website.getDemographicAges().stream().map(Demographic::valueOf).collect(Collectors.toList()));
        websiteCreation.demographicGenders(website.getDemographicGenders().stream().map(Demographic::valueOf).collect(Collectors.toList()));
        websiteCreation.demographicIncomes(website.getDemographicIncomes().stream().map(Demographic::valueOf).collect(Collectors.toList()));
        websiteCreation.description(website.getDescription());
        websiteCreation.url(website.getUrl());
        websiteCreation.whiteListedDomains(website.getWhiteListedDomains());
        return websiteService.create(websiteCreation.build());
    }

    public WebsiteService getWebsiteService() {
        return websiteService;
    }

    public void setWebsiteService(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    public PropertyPlaceholderConfigurer getProperties() {
        return properties;
    }

    public void setProperties(PropertyPlaceholderConfigurer properties) {
        this.properties = properties;
    }

}
