package com.kabeli.user.repository;

import com.kabeli.user.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID>{

    Account findByName(String name);

    Account findByEmail(String email);

}

