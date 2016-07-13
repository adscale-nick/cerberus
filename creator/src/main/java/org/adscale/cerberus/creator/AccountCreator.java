package org.adscale.cerberus.creator;

import org.adscale.cerberus.model.Account;
import org.adscale.market.api.domain.AccountCreation;
import org.adscale.market.api.domain.Address;
import org.adscale.market.api.domain.ContactDetails;
import org.adscale.market.api.service.account.AccountService;
import org.adscale.market.vocabulary.identity.AccountId;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AccountCreator {

    @Resource
    AccountService accountService;

    @Resource
    PropertyPlaceholderConfigurer properties;

    public AccountId createAccount(String json) {
        Account account = Account.parseJson(json);

        AccountCreation.Builder accountCreation = AccountCreation.builder();
        accountCreation.setUsername(account.getUsername());
        accountCreation.setSmallBusiness(account.isSmallBusiness());
        accountCreation.setPassword(account.getPassword());
        accountCreation.setVatNumber(account.getVatNumber());

        accountCreation.setContactDetails(getDefaultContactDetails(account));
        return accountService.create(accountCreation.build());
    }

    private ContactDetails getDefaultContactDetails(Account account) {
        ContactDetails.Builder contactDetails = ContactDetails.builder();
        Address defaultAddress = getDefaultAddress(account);
        contactDetails.setAddress(defaultAddress);
        contactDetails.setBillingAddress(defaultAddress);
        contactDetails.setCompany(account.getCompany());
        contactDetails.setEmail(account.getEmail());
        contactDetails.setFax(account.getFax());
        contactDetails.setFirstName(account.getFirstName());
        contactDetails.setLastName(account.getLastName());
        contactDetails.setMiddleName(account.getMiddleName());
        contactDetails.setPhone(account.getPhone());
        contactDetails.setTitle(account.getTitle());
        return contactDetails.buildWithQueryValidation();
    }

    private Address getDefaultAddress(Account account) {
        Address.Builder address = Address.builder();
        address.setAddresseeName(account.getAddresseeName());
        address.setAddressLine1(account.getAddressLine1());
        address.setAddressLine2(account.getAddressLine2());
        address.setCity(account.getCity());
        address.setCountryCode(account.getCountryCode());
        address.setZip(account.getZip());
        return address.buildWithQueryValidation();
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public PropertyPlaceholderConfigurer getProperties() {
        return properties;
    }

    public void setProperties(PropertyPlaceholderConfigurer properties) {
        this.properties = properties;
    }
}
