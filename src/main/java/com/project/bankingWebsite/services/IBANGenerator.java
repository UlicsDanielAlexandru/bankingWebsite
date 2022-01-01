package com.project.bankingWebsite.services;

import org.apache.commons.lang3.RandomStringUtils;

public class IBANGenerator {

    public static String generateIBAN()
    {
        String IBAN = "RO";
        IBAN = IBAN.concat(RandomStringUtils.random(2,false,true));
        IBAN = IBAN.concat("MYBA");
        return IBAN.concat(RandomStringUtils.random(16,true,true).toUpperCase());
    }
}
