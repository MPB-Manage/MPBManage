package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.UserAccountRequest;
import dk.mpb.manage.project.dto.UserAccountResponse;
import dk.mpb.manage.project.entity.UserAccount;
import dk.mpb.manage.project.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

    @Mock
    UserAccountRepository userAccountRepository;

    UserAccountService userAccountService;

    @BeforeEach
    void setUp() {
        userAccountService = new UserAccountService(userAccountRepository);
    }

    UserAccount testUserSetup() {
        return new UserAccount("testUser", "testPassword", "testEmail");
    }

    @Test
    void testGetAllUserAccounts() {
        UserAccount testUser = testUserSetup();
        when(userAccountRepository.findAll()).thenReturn(List.of(testUser));
        List<UserAccountResponse> userAccounts = userAccountService.getAllUserAccounts();
        assertEquals(1, userAccounts.size());
        assertEquals(testUser.getUsername(), userAccounts.get(0).getUsername());
        assertEquals(testUser.getEmail(), userAccounts.get(0).getEmail());
    }

    @Test
    void testGetUserAccount() {
        UserAccount testUser = testUserSetup();
        when(userAccountRepository.findById(testUser.getUsername())).thenReturn(Optional.of(testUser));
        UserAccountResponse userAccount = userAccountService.getUserAccount(testUser.getUsername());
        assertEquals(testUser.getUsername(), userAccount.getUsername());
        assertEquals(testUser.getEmail(), userAccount.getEmail());
    }

    @Test
    void testCreateUserAccount() {
        UserAccount testUser = testUserSetup();
        when(userAccountRepository.existsByEmail(testUser.getEmail())).thenReturn(false);
        UserAccountRequest testUserRequest = new UserAccountRequest(testUser.getUsername(), testUser.getPassword(), testUser.getEmail());
        userAccountService.createUserAccount(testUserRequest);

        verify(userAccountRepository, times(1)).save(argThat(savedUser ->
                savedUser.getUsername().equals(testUser.getUsername()) &&
                        savedUser.getEmail().equals(testUser.getEmail())
        ));
        verify(userAccountRepository, times(1)).existsByEmail(testUser.getEmail());
    }

    @Test
    void testUpdateUserAccount() {
        UserAccount testUser = testUserSetup();
        when(userAccountRepository.findById(testUser.getUsername())).thenReturn(Optional.of(testUser));
        UserAccountRequest testUserRequest = new UserAccountRequest(testUser.getUsername(), "NewTestPassword", testUser.getEmail());
        userAccountService.updateUserAccount(testUser.getUsername(), testUserRequest);
        verify(userAccountRepository, times(1)).save(argThat(savedUser ->
                savedUser.getUsername().equals(testUser.getUsername()) &&
                        savedUser.getEmail().equals(testUser.getEmail()) &&
                        savedUser.getPassword() != null && !savedUser.getPassword().isEmpty()
        ));
    }

    @Test
    void testDeleteUserAccountById() {
        UserAccount testUser = testUserSetup();
        userAccountService.deleteUserAccountById(testUser.getUsername());
        verify(userAccountRepository, times(1)).deleteById(testUser.getUsername());
    }

    @Test
    void testDeleteUserAccount() {
        UserAccount testUser = testUserSetup();
        userAccountService.deleteUserAccount(testUser.getUsername());
        verify(userAccountRepository, times(1)).deleteById(testUser.getUsername());
    }
}
