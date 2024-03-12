package Tests.Unit;

import accountsModule.*;
import choresModule.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AccountTest {
	@Test
    public void testValidAuthentication() {
        Account account = new Account("user123", "password123");
        assertTrue(account.authenticate("user123", "password123"));
    }

    @Test
    public void testInvalidAuthenticationIncorrectPassword() {
        Account account = new Account("user123", "password123");
        assertFalse(account.authenticate("user123", "wrongpassword"));
    }

    @Test
    public void testInvalidAuthenticationIncorrectUsername() {
        Account account = new Account("user123", "password123");
        assertFalse(account.authenticate("incorrectuser", "password123"));
    }

    @Test
    public void testInvalidAuthenticationEmptyUsername() {
        Account account = new Account("user123", "password123");
        assertFalse(account.authenticate("", "password123"));
    }

    @Test
    public void testInvalidAuthenticationEmptyPassword() {
        Account account = new Account("user123", "password123");
        assertFalse(account.authenticate("user123", ""));
    }

    @Test
    public void testInvalidAuthenticationEmptyUsernameAndPassword() {
        Account account = new Account("user123", "password123");
        assertFalse(account.authenticate("", ""));
    }

}
