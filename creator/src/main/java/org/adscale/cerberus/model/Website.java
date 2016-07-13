package org.adscale.cerberus.model;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class Website {

    String name;

    String accountId;

    boolean adexEnabled;

    boolean nuggadEnabled;

    String url;

    String whiteListedDomains;

    String description;

    String category;

    List<String> demographicAges;

    List<String> demographicGenders;

    List<String> demographicIncomes;


    public static Website parseJson(String json) {
        return new Gson().fromJson(json, Website.class);
    }


    public static String getDefaultJson() throws IOException {
        return Resources.toString(Account.class.getResource("/json-templates/website.json"), Charsets.UTF_8);
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getAccountId() {
        return accountId;
    }


    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }


    public boolean isAdexEnabled() {
        return adexEnabled;
    }


    public void setAdexEnabled(boolean adexEnabled) {
        this.adexEnabled = adexEnabled;
    }


    public boolean isNuggadEnabled() {
        return nuggadEnabled;
    }


    public void setNuggadEnabled(boolean nuggadEnabled) {
        this.nuggadEnabled = nuggadEnabled;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getWhiteListedDomains() {
        return whiteListedDomains;
    }


    public void setWhiteListedDomains(String whiteListedDomains) {
        this.whiteListedDomains = whiteListedDomains;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public List<String> getDemographicAges() {
        return demographicAges;
    }


    public void setDemographicAges(List<String> demographicAges) {
        this.demographicAges = demographicAges;
    }


    public List<String> getDemographicGenders() {
        return demographicGenders;
    }


    public void setDemographicGenders(List<String> demographicGenders) {
        this.demographicGenders = demographicGenders;
    }


    public List<String> getDemographicIncomes() {
        return demographicIncomes;
    }


    public void setDemographicIncomes(List<String> demographicIncomes) {
        this.demographicIncomes = demographicIncomes;
    }
}
