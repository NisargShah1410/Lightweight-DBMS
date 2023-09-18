import java.math.BigInteger;
import java.security.MessageDigest;

// class for Authentication using Hashing
public class Authentication {
    // method be called for getting the hash value and storing it

    /**
     *
     * @param input
     * @return
     */
    public static String md5(String input)
    {
        try {
            // Details about the md5 Hashing technique in Report [1]
            // getInstance method is called with MD5 hashing
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Convert byte array into signum representation
            // signum => -1 for negative, 0 for zero, 1 for positive
            // digest() method is called to calculate message digest
            BigInteger number = new BigInteger(1, md.digest(input.getBytes()));

            // message digest is converted into hex value
            String hashValue = number.toString(16);
            while (hashValue.length() < 32) {
                hashValue = "0" + hashValue;
            }
            // hex value is returned where function is called
            return hashValue;
        }
        // For exception handling
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}