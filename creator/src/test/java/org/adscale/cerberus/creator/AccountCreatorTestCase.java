package org.adscale.cerberus.creator;

import org.adscale.cerberus.model.Account;
import org.adscale.market.api.service.account.AccountService;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration("classpath:spring/marketTestConfiguration.xml")
public class AccountCreatorTestCase extends AbstractJUnit4SpringContextTests {

    @Resource
    AccountCreator accountCreator;


    @Test
    public void testDefaultAccountCreator() throws Exception {
        AccountService accountService = accountCreator.getAccountService();
        assertThat(accountCreator).isNotNull();
        assertThat(accountService.get("testuser")).isNull();
        accountCreator.createAccount(Account.getDefaultJson());
        assertThat(accountService.get("testuser")).isNotNull();
    }
}