

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;

public class Main {
    public static boolean checkPrime_Big(BigInteger x){
        BigInteger num_two = new BigInteger("2");
        boolean flag = false;
        for (BigInteger bi = new BigInteger("2");
             bi.compareTo(x.divide(num_two)) < 0;
             bi = bi.add(BigInteger.ONE)) {

            if(x.mod(bi) == BigInteger.ZERO)
            {
                flag = true;
                break;
            }
        }
        if (!flag){
            return true;
        }

        else {
            return false;
        }
    }
    private static BigInteger getPrime(int bits, Random rand) {
        BigInteger p;
        while (true) {
            p = new BigInteger(bits, 1000000, rand);
            if (checkPrime_Big(p))
                break;
        }
        return p;
    }

    static boolean isPrime(long n)
    {
        if (n <= 1)
            return false;
        for (long i = 2; i < n; i++)
            if (n % i == 0) {
                return false;
            }
            else{
                // System.out.println("pierwsza");
            }
        return true;
    }
    public static BigInteger generujLiczbePierwszaN(long liczbaPodanaPrzezUsera)
    {
        for( long j=liczbaPodanaPrzezUsera; j>2;j--) {
            if(isPrime(j)){
                BigInteger n=BigInteger.valueOf(j);
                return n;
            }
        }
        return null;
    }

    public static BigInteger powerBig(BigInteger x, BigInteger y, BigInteger p)
    {


        BigInteger res = BigInteger.ONE;     // Initialize result
        x = x.mod(p); // Update x if it is more than or
        // equal to p
        while (y.compareTo(BigInteger.ZERO) == 1)
        {
            // If y is odd, multiply x with result
            BigInteger k = y.mod(BigInteger.TWO);
            if (k.compareTo(BigInteger.ONE) == 0)
            {
                res = res.multiply(x).mod(p);
            }
            // y must be even now
            y = y.divide(BigInteger.TWO); // y = y/2
            x = x.multiply(x).mod(p);
        }
        return res;



    };
    // Utility function to store prime factors of a number
    static void findPrimefactorsBig(HashSet<BigInteger> s, BigInteger n)
    {
        BigInteger x = new BigInteger("2");
        // Print the number of 2s that divide n
        while (n.mod(x)  == BigInteger.ZERO)
        {
            s.add(x);
            n = n.divide(x);
        }
        // n must be odd at this point. So we can skip
        // one element (Note i = i +2)
        for (BigInteger i = new BigInteger("3"); i.compareTo(n.sqrt())== -1; i = i.add(x)) //Second number is bigger =-1
        {
            // While i divides n, print i and divide n
            while (n.mod(i) == BigInteger.ZERO)
            {
                s.add(i);
                n = n.divide(i);
            }
        }
        // This condition is to handle the case when
        // n is a prime number greater than 2
        if (n.compareTo(x) == 1) //Firt value greater then second
        {
            s.add(n);
        }
    }

    static BigInteger findPrimitiveBIG(BigInteger n)
    {
        BigInteger minusOne = new BigInteger("-1");
        HashSet<BigInteger> s = new HashSet<BigInteger>();
        // Check if n is prime or not

        if (checkPrime_Big(n) == false)
        {
            return minusOne;
        }
        // Find value of Euler Totient function of n
        // Since n is a prime number, the value of Euler
        // Totient function is n-1 as there are n-1
        // relatively prime numbers.
        BigInteger phi = n.subtract(BigInteger.ONE);
        // Find prime factors of phi and store in a set
        findPrimefactorsBig(s, phi);
        // Check for every number from 2 to phi
        for  (BigInteger r = new BigInteger("2"); r.compareTo(phi)== -1; r = r.add(BigInteger.ONE))
        {
            // Iterate through all prime factors of phi.
            // and check if we found a power with value 1
            boolean flag = false;
            for (BigInteger a : s)
            {
                // Check if r^((phi)/primefactors) mod n
                // is 1 or not
                BigInteger k = powerBig(r, phi.divide(a), n);
                if (k.compareTo(BigInteger.ONE) == 0)
                {
                    flag = true;
                    break;
                }
            }
            // If there was no power with value 1.
            if (flag == false)
            {
                return r;
            }
        }
        // If no primitive root found
        return minusOne;
    }
    public static BigInteger losujCalkowita()
    {
        Random randNum = new Random();
        int len = 16;
        BigInteger res = new BigInteger(len, randNum);
       // System.out.println(res);
       return res;
    }
  /*  public static BigInteger powerBigg(BigInteger number, BigInteger power)
    {

        BigInteger result = number.pow(power);
        return result;
    };*/


    public static BigInteger powerBigg(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) result = result.multiply(base);
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }
    public static BigInteger klucz(BigInteger g, BigInteger x, BigInteger n){
       return powerBigg(g,x).mod(n);
    }

    public static BigInteger obliczk(BigInteger X, BigInteger y, BigInteger n){
        return powerBigg(X,y).mod(n);
    }



    //_________________________________________________________________________
    public static void main(String[] args) {
        BigInteger n=generujLiczbePierwszaN(50004542);
        System.out.println("Liczba n: ");
        System.out.println(n);
        System.out.println("_______________________________________");
        System.out.println("_______________________________________");

        BigInteger g = findPrimitiveBIG(n);
        System.out.println("Liczba g: ");
        System.out.println(g);
        System.out.println("_______________________________________");
        System.out.println("_______________________________________");

        BigInteger x = losujCalkowita();
        System.out.println("Liczba x: ");
        System.out.println(x);
        System.out.println("_______________________________________");
        System.out.println("_______________________________________");

        BigInteger y = losujCalkowita();
        System.out.println("Liczba y: ");
        System.out.println(y);
        System.out.println("_______________________________________");
        System.out.println("_______________________________________");

        BigInteger X = klucz(g,x,n);
        System.out.println("Liczba X: ");
        System.out.println(X);
        System.out.println("_______________________________________");
        System.out.println("_______________________________________");

        BigInteger Y = klucz(g,y,n);
        System.out.println("Liczba Y: ");
        System.out.println(Y);
        System.out.println("_______________________________________");
        System.out.println("_______________________________________");

        BigInteger Ak= obliczk(Y,x,n);
        System.out.println("Liczba Ak: ");
        System.out.println(Ak);
        System.out.println("_______________________________________");
        System.out.println("_______________________________________");

        BigInteger Bk= obliczk(X,y,n);
        System.out.println("Liczba Bk: ");
        System.out.println(Bk);
        System.out.println("_______________________________________");
        System.out.println("_______________________________________");



        //g – pierwiastek     pierwotny       modulo  n,      i       gdzie   1<g<n.
    }
}
