package org.adscale.cerberus.model;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.net.URL;

public class WebsiteTestCase {

    @Test
    public void testParseJson() throws Exception {
        URL resource = Website.class.getResource("/json-templates/website.json");
        String text = Resources.toString(resource, Charsets.UTF_8);
        Website website = Website.parseJson(text);
        Assertions.assertThat(website.getName()).isEqualTo("websiteName");
        Assertions.assertThat(website.getAccountId()).isEqualTo("1");
        Assertions.assertThat(website.isAdexEnabled()).isEqualTo(false);
        Assertions.assertThat(website.isNuggadEnabled()).isEqualTo(false);
        Assertions.assertThat(website.getUrl()).isEqualTo("http://www.someUrl.com");
        Assertions.assertThat(website.getWhiteListedDomains()).isEqualTo("");
        Assertions.assertThat(website.getDescription()).isEqualTo("your new website");
        Assertions.assertThat(website.getCategory()).isEqualTo("ARTS");
        Assertions.assertThat(website.getDemographicAges()).isEqualTo(
                Lists.newArrayList("AGE_0_TO_18", "AGE_18_TO_29", "AGE_30_TO_45", "AGE_46_TO_60", "AGE_OVER_60"));
        Assertions.assertThat(website.getDemographicGenders()).isEqualTo(
                Lists.newArrayList("GENDER_MMM", "GENDER_MMF", "GENDER_MF", "GENDER_FFM", "GENDER_FFF"));
        Assertions.assertThat(website.getDemographicIncomes()).isEqualTo(
                Lists.newArrayList("INCOME_0_TO_25K", "INCOME_25_TO_50K", "INCOME_50_TO_75K", "INCOME_OVER_75K"));
    }
}