package org.adscale.cerberus.model;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.net.URL;

public class AccountTestCase {
    @Test
    public void testParseJson() throws Exception {
        URL resource = Account.class.getResource("/json-templates/account.json");
        String text = Resources.toString(resource, Charsets.UTF_8);
        Account account = Account.parseJson(text);
        Assertions.assertThat(account.getUsername()).isEqualTo("testuser");
        Assertions.assertThat(account.getPassword()).isEqualTo("password");
        Assertions.assertThat(account.isSmallBusiness()).isEqualTo(false);
        Assertions.assertThat(account.getVatNumber()).isEqualTo("121");
        Assertions.assertThat(account.getAddresseeName()).isEqualTo("testuser");
        Assertions.assertThat(account.getAddressLine1()).isEqualTo("123 Fake Street");
        Assertions.assertThat(account.getAddressLine2()).isEqualTo("Riccarton");
        Assertions.assertThat(account.getCity()).isEqualTo("Christchurch");
        Assertions.assertThat(account.getCountryCode()).isEqualTo("DE");
        Assertions.assertThat(account.getZip()).isEqualTo("1234");
        Assertions.assertThat(account.getCompany()).isEqualTo("adscale");
        Assertions.assertThat(account.getEmail()).isEqualTo("all-nz@adscale.co.nz");
        Assertions.assertThat(account.getFax()).isEqualTo("123-456-789");
        Assertions.assertThat(account.getFirstName()).isEqualTo("testuser");
        Assertions.assertThat(account.getLastName()).isEqualTo("Smith");
        Assertions.assertThat(account.getMiddleName()).isEqualTo("");
        Assertions.assertThat(account.getPhone()).isEqualTo("987-654-321");
        Assertions.assertThat(account.getTitle()).isEqualTo("");
    }
}