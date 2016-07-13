package org.adscale.cerberus.model;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;

import java.io.IOException;

public class Account {

    private String name;

    private String password;

    private boolean isSmallBusiness;

    private String vatNumber;

    private Account.Address address;

    private class Address {

        private String addresseeName;

        private String addressLine1;

        private String addressLine2;

        private String city;

        private String countryCode;

        private String zip;
    }

    private Account.Contact contact;

    private class Contact {

        private String company;

        private String email;

        private String fax;

        private String firstName;

        private String lastName;

        private String middleName;

        private String phone;

        private String title;
    }


    public Account() {
    }


    public static Account parseJson(String json) {
        return new Gson().fromJson(json, Account.class);
    }


    public static String getDefaultJson() throws IOException {
        return Resources.toString(Account.class.getResource("/json-templates/account.json"), Charsets.UTF_8);
    }


    public String getUsername() {
        return name;
    }


    public String getPassword() {
        return password;
    }


    public boolean isSmallBusiness() {
        return isSmallBusiness;
    }


    public String getVatNumber() {
        return vatNumber;
    }


    public String getAddresseeName() {
        return address.addresseeName;
    }


    public String getAddressLine1() {
        return address.addressLine1;
    }


    public String getAddressLine2() {
        return address.addressLine2;
    }


    public String getCity() {
        return address.city;
    }


    public String getCountryCode() {
        return address.countryCode;
    }


    public String getZip() {
        return address.zip;
    }


    public String getCompany() {
        return contact.company;
    }


    public String getEmail() {
        return contact.email;
    }


    public String getFax() {
        return contact.fax;
    }


    public String getFirstName() {
        return contact.firstName;
    }


    public String getLastName() {
        return contact.lastName;
    }


    public String getMiddleName() {
        return contact.middleName;
    }


    public String getPhone() {
        return contact.phone;
    }


    public String getTitle() {
        return contact.title;
    }
}
