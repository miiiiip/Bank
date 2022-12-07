package Bank;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Bank {

    
    // encrypter is our RSA implementation that has two random 500 bit primes as inputs
    RSA encrypter = new RSA("1972895566537320284595771816580068493072380672766508921904060423053752554079004433589621687258281955826119788374807090052903397877220494503477953409951",
    "1706964464376704331537915103331259304150858511661865298751238727671301661171853268082341646239188710221958533336528984167140545490006467534900226907183");
    

    public static void main(String[] args) {
        
        
    }

    public boolean veryifyWithdrawl(String a, String c, String d, String I, String r, String chunkValue) {
        try{
        // digest is the SHA-256 instance we will be using for the Hash function
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // Constructing I XOR a to help generate the chunk value
        BigInteger bigA = new BigInteger(a);
        BigInteger bigI = new BigInteger(I);
        String IXa = bigA.xor(bigI).toString();

        // Creating x and y based on the given ibputs
        byte[] x = digest.digest((a+c).getBytes());
        byte[] y = digest.digest((IXa+d).getBytes());

        // This is our way of concatenating two byte Arrays without converting to BigIntegers
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(x);
        outputStream.write(y);
        byte[] xconcaty = outputStream.toByteArray();

        // Give the concatentation to the Hash function
        byte[] fXY = digest.digest(xconcaty);

        // Converting the result to a bigInteger
        // and constructing the r^e based on the r given to us and the e in our RSA instance
        BigInteger bigfXY = new BigInteger(fXY);
        BigInteger bigRe = this.encrypter.encrypt(r);

        // Constructing our expected r^e*f(x, y) and the given one into BigIntegers to compare
        BigInteger expectedChunkValue = bigRe.multiply(bigfXY);
        BigInteger givenChunkValue = new BigInteger(chunkValue, 2);

        return expectedChunkValue.equals(givenChunkValue);
        } catch (Exception e) {return false;}
    }

    public boolean depositVerification0(String IXa, String d, String x, String chunkValue){
        try{
            // digest is the SHA-256 instance we will be using for the Hash function
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Constructing x and y 
            byte[] byteX = new BigInteger(x, 2).toByteArray();
            byte[] byteY = digest.digest((IXa+d).getBytes());
            
            // This is our way of concatenating two byte Arrays without converting to BigIntegers
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(byteX);
            outputStream.write(byteY);
            byte[] xconcaty = outputStream.toByteArray();

            // Give the concatentation to the Hash function
            byte[] fXY = digest.digest(xconcaty);

            // Constructing our expected r^e*f(x, y) and the given one into BigIntegers to compare
            BigInteger expectedChunkValue = new BigInteger(fXY);
            BigInteger givenChunkValue = new BigInteger(chunkValue, 2);

            return expectedChunkValue.equals(givenChunkValue);
        } catch (Exception e) {return false;}
    }

    public boolean depositVerification1(String a, String c, String y, String chunkValue){
        try{
            // digest is the SHA-256 instance we will be using for the Hash function
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Constructing x and y 
            byte[] byteX = digest.digest((a+c).getBytes());
            byte[] byteY = new BigInteger(y, 2).toByteArray();    
            
            // This is our way of concatenating two byte Arrays without converting to BigIntegers
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(byteX);
            outputStream.write(byteY);
            byte[] xconcaty = outputStream.toByteArray();

            // Give the concatentation to the Hash function
            byte[] fXY = digest.digest(xconcaty);

            // Constructing our expected r^e*f(x, y) and the given one into BigIntegers to compare
            BigInteger expectedChunkValue = new BigInteger(fXY);
            BigInteger givenChunkValue = new BigInteger(chunkValue, 2);

            return expectedChunkValue.equals(givenChunkValue);
        } catch (Exception e) {return false;}
    }
}