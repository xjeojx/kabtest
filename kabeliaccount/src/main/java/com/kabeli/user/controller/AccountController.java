package com.kabeli.user.controller;
import com.kabeli.user.model.Account;
import com.kabeli.user.service.AccountService;
import com.kabeli.user.utils.Response;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@CommonsLog
public class AccountController {

    @Autowired
    private AccountService userService;

    public AccountController(AccountService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody Account account) {

        Response response = userService.createUser(account);
        boolean success = response.isStatus();

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        String message = response.getMessage();

        if(success){
            headers.add("Status", "OK");
            status = HttpStatus.OK;
        }
        else{
            headers.add("Status", "ERROR");
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(message, headers, status);

    }

    @RequestMapping("/list")
    @ResponseBody
    public ResponseEntity<?> listUser() {

        Response response = userService.listUser();
        boolean success = response.isStatus();

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        String message = response.getMessage();

        if(success){
            headers.add("Status", "OK");
            status = HttpStatus.OK;
        }
        else{
            headers.add("Status", "ERROR");
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(message, headers, status);

    }

}


