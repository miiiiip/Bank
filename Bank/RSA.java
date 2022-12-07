package Bank;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
    BigInteger e;
    BigInteger n;
    BigInteger phiN;
    BigInteger d;
    BigInteger p;
    BigInteger q;

    public RSA(String intp, String intq) {
        q = new BigInteger(intq);
        p = new BigInteger(intp);
        e = new BigInteger("0");
        n = p.multiply(q);
        phiN = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));

        e = q.nextProbablePrime();
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
        RSA cringe = new RSA("1972895566537320284595771816580068493072380672766508921904060423053752554079004433589621687258281955826119788374807090052903397877220494503477953409951",
         "1706964464376704331537915103331259304150858511661865298751238727671301661171853268082341646239188710221958533336528984167140545490006467534900226907183");
        
        System.out.println("p: " + cringe.p);
        System.out.println("q: " + cringe.q);
        System.out.println("d: " + cringe.d);
        System.out.println("e: " + cringe.e);
        

        // p: 1972895566537320284595771816580068493072380672766508921904060423053752554079004433589621687258281955826119788374807090052903397877220494503477953409951
        // q: 1706964464376704331537915103331259304150858511661865298751238727671301661171853268082341646239188710221958533336528984167140545490006467534900226907183
        // d: 2716398043095924778704478363524570189179440463579536583192943204453169536659949227484406678303737121109057762922039001337899657630952374942425722955367469935157553010128729784800895967405180431073498052684132662092910486826087804260687310838931007624159846689260526061846474623544100128302826820663637
        // e: 1706964464376704331537915103331259304150858511661865298751238727671301661171853268082341646239188710221958533336528984167140545490006467534900226907373
        
        // System.out.println("Encrypt: " + RSA.encryptSpecific("102734180247043",
        // "664725562992008719", "3"));
        //System.out.println("Decrypt: " + cringe.decrpyt(new BigInteger("89497323804483257")));
    }
}
