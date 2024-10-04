package test.java.student;   // Don't change the package name. Gradescope expects this

/**
 * JUnit 5 test class for Coinbank. Use these tests as models for your own.
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
        for (int index = 0; index < money.length; index++) {
            int numCoins = money[index];
            for (int coin = 0; coin < numCoins; coin++) {
                c.insert(denom[index]);
            }
        }
        return c;
    }

    @Test // a newly constructed bank should be empty
    void testConstruct() {
        Coinbank emptyDefault = new Coinbank();
        String msg = "Bank should be empty upon construction.";
        assertEquals(0, emptyDefault.get(1), msg);
        assertEquals(0, emptyDefault.get(5), msg);
        assertEquals(0, emptyDefault.get(10), msg);
        assertEquals(0, emptyDefault.get(25), msg);
    }

    @Test // inserting a nickel should return true & one nickel should be in bank
    void testInsertNickel() {
        Coinbank c = new Coinbank();
        String msgInsertReturn = "Inserting a nickel into an empty bank should return true.";
        assertTrue(c.insert(5), msgInsertReturn);
        String msgBalance = "Insert a nickel into empty bank --> now one nickel should be in bank.";
        assertEquals(1, c.get(5), msgBalance);
    }

    @Test // getter should return correct values & not alter the bank
    void testGetPennies() {
        Coinbank c = makeBank(0, 2, 15, 1);
        int pennyBalance = c.get(1);
        assertEquals(0, pennyBalance, "0 pennies in bank -> get(1) should return 0");
        String expected = "The bank currently holds $1.85 consisting of \n0 pennies\n2 nickels\n15 dimes\n1 quarters\n";
        assertEquals(expected, c.toString(), "Getting the penny balance should not alter the bank.");
    }

    @Test // getter should return correct values & not alter the bank
    void testGetNickels() {
        Coinbank c = makeBank(0, 2, 15, 1);
        int nickelBalance = c.get(5);
        assertEquals(2, nickelBalance, "2 nickels in bank -> get(5) should return 2");
        String expected = "The bank currently holds $1.85 consisting of \n0 pennies\n2 nickels\n15 dimes\n1 quarters\n";
        assertEquals(expected, c.toString(), "Getting the nickel balance should not alter the bank.");
    }

    @Test // getter should return -1 when an invalid coin is requested
    void testGetInvalidCoin() {
        Coinbank c = makeBank(0, 2, 15, 1);
        String msg = "Getting the number of 4-cent coins should return -1.";
        assertEquals(-1, c.get(4), msg);
    }

    @Test // removing coins should work correctly when exactly the requested amount is available
    void testRemoveJustEnough() {
        Coinbank c = makeBank(4, 1, 3, 5);
        int coinsReturned = c.remove(25, 5);
        String msgReturnTest = "Removing 5 quarters. Correct number of coins is returned.";
        assertEquals(5, coinsReturned, msgReturnTest);
        String expected = "The bank currently holds $0.39 consisting of \n4 pennies\n1 nickels\n3 dimes\n0 quarters\n";
        String msgBalanceTest = "After removing 5 coins correct amount remains in the bank.";
        assertEquals(expected, c.toString(), msgBalanceTest);
    }

    @Test // removing an invalid coin type should not alter the bank and return 0
    void testRemoveInvalidCoin() {
        Coinbank c = makeBank(4, 1, 3, 5);
        String msg = "Trying to remove an invalid coin type should return 0.";
        assertEquals(0, c.remove(3, 1), msg);
    }

    @Test // attempting to remove more coins than available should only remove the available coins
    void testRemoveMoreThanAvailable() {
        Coinbank c = makeBank(2, 1, 3, 1);
        int coinsRemoved = c.remove(1, 5); // Trying to remove more pennies than available
        assertEquals(2, coinsRemoved, "Removing more pennies than available should only remove the available amount.");
        String expected = "The bank currently holds $0.6 consisting of \n0 pennies\n1 nickels\n3 dimes\n1 quarters\n";
        String msgBalanceTest = "After attempting to remove more coins than available, the correct amount should remain in the bank.";
        assertEquals(expected, c.toString(), msgBalanceTest);
    }

    @Test // Insert multiple coins of each type and verify counts
    void testInsertMultipleCoins() {
        Coinbank c = makeBank(5, 3, 7, 2); // 5 pennies, 3 nickels, 7 dimes, 2 quarters
        assertEquals(5, c.get(1), "5 pennies should be present.");
        assertEquals(3, c.get(5), "3 nickels should be present.");
        assertEquals(7, c.get(10), "7 dimes should be present.");
        assertEquals(2, c.get(25), "2 quarters should be present.");
    }

    @Test // Attempt to insert an invalid coin type
    void testInsertInvalidCoin() {
        Coinbank c = new Coinbank();
        assertFalse(c.insert(2), "Inserting a 2-cent coin should return false.");
    }

    @Test // Removing a negative number of coins
    void testRemoveNegativeAmount() {
        Coinbank c = makeBank(5, 3, 7, 2);
        int coinsReturned = c.remove(10, -3); // Negative number of coins
        assertEquals(0, coinsReturned, "Removing a negative number of coins should return 0.");
    }

    @Test // Attempt to remove coins from an empty bank
    void testRemoveFromEmptyBank() {
        Coinbank c = new Coinbank();
        int coinsReturned = c.remove(1, 3); // No coins available
        assertEquals(0, coinsReturned, "Removing coins from an empty bank should return 0.");
    }

    @Test // Removing zero coins
    void testRemoveZeroCoins() {
        Coinbank c = makeBank(5, 3, 7, 2);
        int coinsReturned = c.remove(10, 0); // Remove 0 coins
        assertEquals(0, coinsReturned, "Removing zero coins should return 0.");
    }

    @Test // Removing the maximum integer value of coins
    void testRemoveMaxIntCoins() {
        Coinbank c = makeBank(0, 0, Integer.MAX_VALUE, 0);
        int coinsReturned = c.remove(10, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, coinsReturned, "Removing the maximum integer value of coins should return the maximum value.");
    }

    @Test // Verify consistency after multiple operations
    void testMultipleOperationsConsistency() {
        Coinbank c = makeBank(5, 5, 5, 5);
        c.remove(5, 2);
        c.insert(10);
        assertEquals(5, c.get(1), "Ensure correct penny count after multiple operations.");
        assertEquals(3, c.get(5), "Ensure correct nickel count after multiple operations.");
        assertEquals(6, c.get(10), "Ensure correct dime count after multiple operations.");
        assertEquals(5, c.get(25), "Ensure correct quarter count after multiple operations.");
    }

    @Test // Verify the bank's state when it contains only one type of coin
    void testSingleTypeOfCoins() {
        Coinbank c = makeBank(0, 0, 0, 10); // Only quarters
        assertEquals(0, c.get(1), "Should be 0 pennies.");
        assertEquals(0, c.get(5), "Should be 0 nickels.");
        assertEquals(0, c.get(10), "Should be 0 dimes.");
        assertEquals(10, c.get(25), "Should be 10 quarters.");
    }
    @Test // Verify the toString method for accurate representation
    void testToString() {
        Coinbank c = makeBank(1, 1, 1, 1);
        String expected = "The bank currently holds $0.41 consisting of \n1 pennies\n1 nickels\n1 dimes\n1 quarters\n";
        assertEquals(expected, c.toString(), "The string representation of the bank's state is incorrect.");
    }

}