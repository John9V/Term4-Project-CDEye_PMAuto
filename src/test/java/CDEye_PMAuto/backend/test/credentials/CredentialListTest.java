package CDEye_PMAuto.backend.test.credentials;

import org.junit.Assert;
import org.junit.Test;
import CDEye_PMAuto.backend.credentials.*;



import static org.junit.jupiter.api.Assertions.*;

public class CredentialListTest {

    private final CredentialsList users = new CredentialsList();
    private final Credential newUser = new Credential();


    @Test
    public void userListTest(){
        assertEquals("user1",users.findCredentials("user1").getUserName());
        assertEquals("jdoe",users.findCredentials("jdoe").getUserName());
        assertEquals("jsmith",users.findCredentials("jsmith").getUserName());
    }

    @Test
    public void validCredentialTest(){
        assertTrue(users.validCredentials(users.findCredentials("user1")));
        assertTrue(users.validCredentials(users.findCredentials("jdoe")));
        assertTrue(users.validCredentials(users.findCredentials("jsmith")));
    }

    @Test
    public void getAllCredentialsTest(){

    }

    @Test
    public void addCredentialTest(){
        newUser.setUserName("newUser");
        newUser.setPassword("123123");
        users.addCredentials(newUser);
        assertTrue(users.validCredentials(users.findCredentials("newUser")));
    }

    @Test
    public void updateCredentialTest(){
        newUser.setUserName("newUser");
        newUser.setPassword("123456");
        users.addCredentials(newUser);
        users.updateCredentials(newUser);
        assertEquals("123456",users.findCredentials("newUser").getPassword());
    }


    @Test
    public void deleteCredentialTest(){
        newUser.setUserName("newUser");
        newUser.setPassword("123456");
        users.addCredentials(newUser);
        users.deleteCredentials(newUser);
        assertFalse(users.validCredentials(users.findCredentials("newUser")));
    }
}
