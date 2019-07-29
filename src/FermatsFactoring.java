import java.math.*;
import java.util.*;

/**
 * Title: Fermats's factoring method
 */

public class FermatsFactoring {

	private final int maxIterations = 10000000;
	private final BigInteger one = BigInteger.ONE;

	public FermatsFactoring() {
	}

	/**
	 * @return factorisation result or 1, if n is a prime number
	 */
	public BigInteger factoring(BigInteger n) {
		BigInteger two = new BigInteger("2");
		BigInteger x = this.decimalSQRT(n);
		BigInteger delta = x.shiftLeft(1).add(one);
		BigInteger q = x.multiply(x); // q = x2
		BigInteger y = one;
		int c = 0;
		do {
			c++;
			q = q.add(delta);
			delta = delta.add(two);
			y = this.integerSQRT(q.subtract(n));
		} while (y.compareTo(BigInteger.ZERO) == 0 && c < maxIterations);
		return this.integerSQRT(q).subtract(y).gcd(n);
	}

	private BigInteger decimalSQRT(BigInteger n) {
		BigDecimal two = new BigDecimal("2");
		BigDecimal n05 = new BigDecimal("0.5");
		BigDecimal a = new BigDecimal(n);
		BigDecimal x = new BigDecimal(new BigInteger(n.bitLength() / 2, new Random()));
		while (x.multiply(x).toBigInteger().compareTo(a.toBigInteger()) != 0) {
			if (x.multiply(x).add(n05).toBigInteger().compareTo(a.toBigInteger()) != 0) {
				break;
			}
			x = x.add(a.divide(x, 10, BigDecimal.ROUND_HALF_EVEN)).divide(two, 10, BigDecimal.ROUND_HALF_EVEN);
		}
		BigInteger result = x.toBigInteger();
		while (result.multiply(result).compareTo(n) > 0) { // Если result2 > n, то откатываемся назад
			result = result.subtract(one);
		}
		return result;
	}

	private BigInteger integerSQRT(BigInteger q) {
		int bits = q.getLowestSetBit();
		if ((bits % 2) == 1) {
			return BigInteger.ZERO;
		}
		q = q.shiftRight(bits);
		q = q.subtract(one);
		if (q.compareTo(BigInteger.ZERO) == 0) {
			return one.shiftLeft(bits / 2);
		}
		if (q.getLowestSetBit() < 3) {
			return BigInteger.ZERO;
		}
		q = q.shiftRight(2);
		BigInteger p = q;
		BigInteger b = one;
		BigInteger a = new BigInteger("4");
		while (p.compareTo(a) >= 0) {
			p = p.shiftRight(1);
			if (p.getLowestSetBit() == 0) {
				p = p.subtract(a.shiftRight(1)).subtract(b);
				b = b.add(a);
			}
			a = a.shiftLeft(1);
		}
		if (p.compareTo(BigInteger.ZERO) == 0) {
			return b.shiftLeft(bits / 2);
		}
		if (a.compareTo(b.shiftLeft(1).add(p)) == 0) {
			return a.subtract(b).shiftLeft(bits / 2);
		}
		if (p.compareTo(a.subtract(b).shiftLeft(2)) == 0) {
			return a.shiftLeft(1).subtract(b).shiftLeft(bits / 2);
		}
		return BigInteger.ZERO;
	}
}