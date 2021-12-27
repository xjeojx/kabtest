package com.kabeli.user.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kabeli.user.model.Account;
import com.kabeli.user.model.Phone;
import com.kabeli.user.repository.AccountRepository;
import com.kabeli.user.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Gson gson;

    public Response createUser(Account user){

        String email = user.getEmail();
        
        Response response = new Response();

        if(!checkIsValidEmail(email)){
            response.setStatus(false);
            response.setMessage("Mail invalido");
        }
        else{
            if(!checkIfExistMail(email)){
                response.setStatus(false);
                response.setMessage("Mail ya existente");
            }
            else{
                if(checkIsValidPassword(user.getPassword())) {
                    response.setStatus(false);
                    response.setMessage("Password no cumple con los requisitos de seguridad");
                }
                else{
                    for(Phone phone: user.getPhones()){
                        phone.setAccount(user);
                    }
                    createUserToDB(user);

                    response.setStatus(true);
                    response.setMessage("Creado con exito");
                }
            }
        }

        return response;
    }

    public  boolean checkIsValidPassword(String password) {
        String regex = "[A-Z]";
        Pattern pat=Pattern.compile(regex);
        Matcher matcher1=pat.matcher(password);

        regex = "[a-z]";
        pat=Pattern.compile(regex);
        Matcher matcher2=pat.matcher(password);

        regex = "[0-9][\\w]*[0-9]";
        pat=Pattern.compile(regex);
        Matcher matcher3=pat.matcher(password);

        return matcher1.find() && matcher2.find() && matcher3.find();
    }

    @Transactional
    private void createUserToDB(Account user) {
        accountRepository.save(user);
    }

    private boolean checkIfExistMail(String mail) {
        Account found=accountRepository.findByEmail(mail);
        return found==null;
    }

    private boolean checkIsValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pat=Pattern.compile(regex);
        Matcher matcher=pat.matcher(email);
        return matcher.matches();
    }


    public Response listUser() {
        List<Account> accounts = accountRepository.findAll();

        JsonArray array=new JsonArray();
        for(Account account:accounts){
            array.add(account.toJson());
        }

        Response response = new Response();
        response.setStatus(true);
        response.setMessage("Solicitud realizada.");
        response.setData(array);
        return response;
    }
}
