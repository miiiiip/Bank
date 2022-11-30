import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Bank {
    

    public static void main(String[] args) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] potato = new byte[2];
            potato[0] = (byte)74;
            potato[1] = (byte)108;
            byte[] bean = digest.digest(potato);
            for (int i = 0; i < bean.length; i++) {
                System.out.println(bean[i]);
            }
            System.out.println(bean.length);
            System.out.flush();
    
    } catch(NoSuchAlgorithmException e) {System.out.println(e);}
        
    }
}
