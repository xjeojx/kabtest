package com.kabeli.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private AccountService accountService = new AccountService();

    @Test
    public void testPassword(){
        assertFalse(accountService.checkIsValidPassword("abc"));
        assertTrue(accountService.checkIsValidPassword("1ddD0"));
        assertFalse(accountService.checkIsValidPassword("00000a0"));
    }

}