import java.math.BigInteger;
import java.util.Random;

public class RSA {
    BigInteger e;
    BigInteger n;
    BigInteger phiN;
    BigInteger d;
    BigInteger p;
    BigInteger q;

    public RSA(int intp, int intq) {
        q = new BigInteger(Integer.toString(intq));
        p = new BigInteger(Integer.toString(intp));
        e = new BigInteger("0");
        n = p.multiply(q);
        phiN = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));

        for (int i = 2; i <= phiN.intValue(); i++) {
            if (phiN.gcd(new BigInteger(Integer.toString(i))).equals(new BigInteger("1"))) {
                e = new BigInteger(Integer.toString(i));
                System.out.println(e);
                break;
            }
        }
        d = e.modInverse(phiN);
    }

    public RSA(int bitLength, Random rnd) {
        q = BigInteger.probablePrime(bitLength, rnd);
        p = BigInteger.probablePrime(bitLength, rnd);
        n = p.multiply(q);
        phiN = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));
        e = q.nextProbablePrime();
        d = e.modInverse(phiN);
    }



    public BigInteger encrypt(String input) {
        BigInteger bigInput = new BigInteger(input);
        BigInteger enc = bigInput.modPow(e, n);
        return enc;
    }

    public static BigInteger encryptSpecific(String input, String n, String e) {

        BigInteger bigInput = new BigInteger(input);
        BigInteger enc = bigInput.modPow(new BigInteger(e), new BigInteger(n));
        return enc;
    }

    public BigInteger decrpyt(BigInteger input) {
        BigInteger dec = input.modPow(d, n);
        return dec;
    }

    public static void main(String[] args) {
        RSA cringe = new RSA(500, new Random());
        System.out.println(cringe.e);
        System.out.println(cringe.d);
        System.out.println(cringe.p);
        System.out.println(cringe.q);
        
        // System.out.println("Encrypt: " + RSA.encryptSpecific("102734180247043",
        // "664725562992008719", "3"));
        //System.out.println("Decrypt: " + cringe.decrpyt(new BigInteger("89497323804483257")));
    }
}
