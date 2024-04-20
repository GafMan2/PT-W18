package wine.cellar.utility;

import java.util.UUID;

import wine.cellar.entity.Owner;

public class UserCredentialsGenerator {

    public static String generateRandomUsername() {

        return "user_" + UUID.randomUUID().toString().substring(0, 8); // this actually returns the email address
    }

    public static String generateRandomPassword() {
        // This generates a random password using UUID (Universally Unique Identifier)
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static Owner createOwnerWithRandomCredentials() { // This method handles the initial creation of an OwnerId
        String randomUsername = generateRandomUsername(); // My application allows for the use of an email address as username
        String randomPassword = generateRandomPassword(); // The password is generated randomly

        Owner owner = new Owner();  // This creates a new owner with a randomized password
        owner.setUsername(randomUsername);
        owner.setPassword(randomPassword);
        
        return owner;
    }
}