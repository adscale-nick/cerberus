package org.adscale.cerberus.creator;

import org.adscale.cerberus.model.Website;
import org.adscale.market.api.service.website.WebsiteRestriction;
import org.adscale.market.api.service.website.WebsiteService;
import org.adscale.market.vocabulary.identity.AccountId;
import org.junit.Test;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration("classpath:spring/marketTestConfiguration.xml")
@ImportResource("classpath:spring/marketConfiguration.xml")
public class WebsiteCreatorTestCase extends AbstractJUnit4SpringContextTests {

    @Resource
    WebsiteCreator websiteCreator;


    @Test
    public void testName() throws Exception {
        WebsiteService websiteService = websiteCreator.getWebsiteService();
        assertThat(websiteService).isNotNull();
        assertThat(websiteService.get(WebsiteRestriction.accountId(AccountId.of(1L)))).isNull();
        websiteCreator.createWebsite(Website.getDefaultJson());
        assertThat(websiteService.get(WebsiteRestriction.accountId(AccountId.of(1L)))).isNotEmpty();
    }
}