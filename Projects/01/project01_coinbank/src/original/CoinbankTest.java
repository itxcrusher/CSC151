package original;   // Don't change the package name.  Gradescope expects this
/**
 * JUnit 5 test class.  Use these tests as models for your own.
 */
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CoinbankTest {

	/**
	 * Set up a bank with the given coins
	 * @param pennies number of pennies you want
	 * @param nickels number of nickels you want
	 * @param dimes number of dimes you want
	 * @param quarters number of quarters you want
	 * @return the Coinbank filled with the requested coins of each type
	 */
	private Coinbank makeBank(int pennies, int nickels, int dimes, int quarters) {
		Coinbank c = new Coinbank();
		int[] money = new int[]{pennies, nickels, dimes, quarters};
		int[] denom = new int[]{1, 5, 10, 25};
		for (int index=0; index < money.length; index++) {
			int numCoins = money[index];
			for (int coin=0; coin < numCoins; coin++) {
				c.insert(denom[index]);
			}
		}
		return c;
	}

	@Test //  a newly constructed bank should be empty
	void testConstruct() {
		Coinbank emptyDefault = new Coinbank();
		String msg = "Bank should be empty upon construction.";
		assertEquals(0, emptyDefault.get(1), msg);
		assertEquals(0, emptyDefault.get(5), msg);
		assertEquals(0, emptyDefault.get(10), msg);
		assertEquals(0, emptyDefault.get(25), msg);
	}


	@Test // inserting nickel should return true & one nickel should be in bank
	void testInsert_nickel() {
		Coinbank c = new Coinbank();
		String msgInsertReturn = "Inserting a nickel into an empty bank should return true.";
		assertTrue(c.insert(5), msgInsertReturn);
		String msgBalance = "Insert a nickel into empty bank --> now one nickel should be in bank.";
		assertEquals(1,c.get(5), msgBalance);
	}

	@Test // getter should return correct values & not alter the bank
	void testGet_pennies() {
		Coinbank c = makeBank(0,2,15,1);
		int pennyBalance = c.get(1);
		assertEquals(0, pennyBalance, "0 pennies in bank -> get(1) should return 0");
		String expected = "The bank currently holds $1.85 consisting of \n0 pennies\n2 nickels\n15 dimes\n1 quarters\n";
		assertEquals(expected, c.toString(), "Getting the penny balance should not alter the bank.");
	}

	@Test // getter should return correct values & not alter the bank
	void testGet_nickels() {
		Coinbank c = makeBank(0,2,15,1);
		int nickelBalance = c.get(5);
		assertEquals(2, nickelBalance, "2 nickels in bank -> get(5) should return 2");
		String expected = "The bank currently holds $1.85 consisting of \n0 pennies\n2 nickels\n15 dimes\n1 quarters\n";
		assertEquals(expected, c.toString(), "Getting the nickel balance should not alter the bank.");
	}

	@Test // getter should return -1 when an invalid coing is requested
	void testGet_invalidCoin() {
		Coinbank c = makeBank(0, 2, 15, 1);
		String msg = "Getting the number of 4-cent coins should return -1.";
		assertEquals(-1, c.get(4), msg);
	}

	@Test // test of remove
	void testRemove_justEnough() {
		Coinbank c = makeBank(4, 1, 3, 5);
		int coinsReturned = c.remove(25, 5);
		String msgReturnTest = "Removing 5 quarters. Correct number of coins is returned.";
		assertEquals(5, coinsReturned, msgReturnTest);
		String expected = "The bank currently holds $0.39 consisting of \n4 pennies\n1 nickels\n3 dimes\n0 quarters\n";
		String msgBalanceTest = "After removing 5 coins correct amount remains in the bank.";
		assertEquals(expected, c.toString(), msgBalanceTest);
	}

	@Test // remove should not do anything if a 3-cent coin is requested
	void testRemove_invalidCoin() {
		Coinbank c = makeBank(4,1,3,5);
		String msg = "Trying to remove an invalid coin type should return 0.";
		assertEquals(0, c.remove(3,1), msg);
	}
}
