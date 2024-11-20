package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.UserAccountRequest;
import dk.mpb.manage.project.dto.UserAccountResponse;
import dk.mpb.manage.project.entity.UserAccount;
import dk.mpb.manage.project.repository.UserAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserAccountService {
    UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }
    public List<UserAccountResponse> getAllUserAccounts() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        return userAccounts.stream().map(userAccount -> new UserAccountResponse(userAccount)).toList();
    }

    public UserAccountResponse getUserAccount(String name) {
        UserAccount userAccount = userAccountRepository.findById(name).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new UserAccountResponse(userAccount);
    }

    public void createUserAccount(UserAccountRequest userAccountRequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userAccountRequest.getUsername());
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccountRequest.getPassword()));
        userAccountRepository.save(userAccount);
    }

    public ResponseEntity<Boolean> updateUserAccount(String name, UserAccountRequest userAccountRequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserAccount userAccount = userAccountRepository.findById(name).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (bCryptPasswordEncoder.matches(userAccountRequest.getPassword(), userAccount.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is the same");
        }
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccountRequest.getPassword()));
        userAccount.setUsername(userAccountRequest.getUsername());
        userAccountRepository.save(userAccount);
        return ResponseEntity.ok(true);
    }

    public void deleteUserAccountById(String id) {
        userAccountRepository.deleteById(id);
    }

    public void deleteUserAccount(String name) {
        userAccountRepository.deleteById(name);
    }

}
