package dk.mpb.manage.project.api;

import dk.mpb.manage.project.dto.UserAccountRequest;
import dk.mpb.manage.project.dto.UserAccountResponse;
import dk.mpb.manage.project.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {
    private UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    /**
     *  Get all useraccounts
     * */
    @GetMapping()
    public List<UserAccountResponse> getAllUserAccounts() {
        return userAccountService.getAllUserAccounts();
    }

    /**
     *  Get useraccount by id
     * */
    @GetMapping("/user-as-authenticated")
    public UserAccountResponse getUserAccountById(Principal principal) {
        return userAccountService.getUserAccount(principal.getName());
    }

    /**
     *  Create a useraccount
     * */
    @PostMapping()
    public void createUserAccount(@RequestBody UserAccountRequest userAccountRequest) {
        userAccountService.createUserAccount(userAccountRequest);
    }

    /**
     *  Add a property to a useraccount
     * */
    @PostMapping("/properties")
    public void addPropertyToUserAccount(Principal principal, @RequestParam int property) {
        userAccountService.addPropertyToUserAccount(principal.getName(), property);
    }

    /**
     *  Update a useraccount
     * */
    @PutMapping("/user-as-authenticated")
    public ResponseEntity<Boolean> updateUserAccount(Principal principal, @RequestBody UserAccountRequest userAccountRequest) {
        return userAccountService.updateUserAccount(principal.getName(), userAccountRequest);
    }

    /**
     *  Delete a useraccount by id
     * */
    @DeleteMapping("/{id}")
    public void deleteUserAccountById(@PathVariable String id) {
        userAccountService.deleteUserAccountById(id);
    }

    /**
     *  Delete a useraccount
     * */
    @DeleteMapping("/user-as-authenticated")
    public void deleteUserAccount(Principal principal) {
        userAccountService.deleteUserAccount(principal.getName());
    }
}
