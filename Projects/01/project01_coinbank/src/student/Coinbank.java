package student; // Don't change the package name.  Gradescope expects this.

/**
 * FILL THIS IN FOR EVERY PROJECT.  Include a class description, name, and
 * date (for version)
 * @author Ali Hamza
 * @version 09/15/2024
 * I affirm that I have carried out the attached academic endeavors with full academic honesty, in accordance with the Union College Honor Code and the course syllabus.
 */
public class Coinbank {

    /** Value of the denominations */
    public static final int PENNY_VALUE = 1;
    public static final int NICKEL_VALUE = 5;
    public static final int DIME_VALUE = 10;
    public static final int QUARTER_VALUE = 25;

    // Give meaningful names to holder array indices
    private static final int PENNY_INDEX = 0;
    private static final int NICKEL_INDEX = 1;
    private static final int DIME_INDEX = 2;
    private static final int QUARTER_INDEX = 3;

    // How many types of coins does the bank hold?
    private static final int COINTYPES = 4;

    private int[] holder;

    /**
     * Default constructor
     */
    public Coinbank() {
        holder = new int[COINTYPES];
    }

    /**
     * Setter
     * @param coinType denomination of coin to set
     * @param numCoins number of coins
     */
    public int get(int coinType) {
        if (isBankable(coinType)) {
            return holder[getIndex(coinType)];
        }
        return -1;
    }

    /**
     * Setter
     * @param coinType denomination of coin to set
     * @param numCoins number of coins
     */
    private void set(int coinType, int numCoins) {
        if (isBankable(coinType)) {
            holder[getIndex(coinType)] = numCoins;
        }
    }

    /**
     * Check if a coin type can be held by this bank
     * @param coinType denomination of coin
     * @return true if bank can hold this coin, else false
     */
    private boolean isBankable(int coinType) {
        return coinType == PENNY_VALUE || coinType == NICKEL_VALUE || coinType == DIME_VALUE || coinType == QUARTER_VALUE;
    }

    /**
     * Get the index in the holder array for a given coin denomination
     * @param coinType denomination of coin
     * @return index in the holder array
     */
    private int getIndex(int coinType) {
        switch (coinType) {
            case PENNY_VALUE: return PENNY_INDEX;
            case NICKEL_VALUE: return NICKEL_INDEX;
            case DIME_VALUE: return DIME_INDEX;
            case QUARTER_VALUE: return QUARTER_INDEX;
            default: return -1;
        }
    }

    /**
     * Insert valid coin into bank.  Return true if deposit
     * successful (i.e. coin was penny, nickel, dime, or quarter).
     * Return false if coin not recognized
     *
     * @param coinType either 1, 5, 10, or 25 to be valid
     * @return true if deposit successful, else false
     */
    public boolean insert(int coinType) {
        if (isBankable(coinType)) {
            set(coinType, get(coinType) + 1);
            return true;
        }
        return false;
    }

    /**
     * Return the requested number of the requested coin type, if possible.
     * Do nothing if the coin type is invalid.  If bank holds
     * fewer coins than is requested, then all of the coins of that
     * type will be returned.
     * @param coinType either 1, 5, 10, or 25 to be valid
     * @param requestedCoins number of coins to be removed
     * @return number of coins that are actually removed
     */
    public int remove(int coinType, int requestedCoins) {
        if (isBankable(coinType) && requestedCoins >= 0) {
            int index = getIndex(coinType);
            int availableCoins = holder[index];
            int coinsLeftAfterRemoval = numLeft(requestedCoins, availableCoins);
            int coinsRemoved = availableCoins - coinsLeftAfterRemoval;
            set(coinType, coinsLeftAfterRemoval);
            return coinsRemoved;
        }
        return 0;
    }

    /**
     * Return number of coins remaining after removing requested amount
     * @param numWant number of coins to be removed
     * @param numHave number of coins you have
     * @return number of coins left after removal
     */
    private int numLeft(int numWant, int numHave) {
        return Math.max(0, numHave - numWant);
    }

    /**
     * Return bank as a printable string
     * @return printable string representation of the bank
     */
    /**
	 * Returns bank as a printable string
	 */
	public String toString() {
		double total = (get(PENNY_VALUE) * PENNY_VALUE +
				get(NICKEL_VALUE) * NICKEL_VALUE + 
				get(DIME_VALUE) * DIME_VALUE +
				get(QUARTER_VALUE) * QUARTER_VALUE) / 100.0;
				
		String toReturn = "The bank currently holds $" + total + " consisting of \n";
		toReturn+=get(PENNY_VALUE) + " pennies\n";
		toReturn+=get(NICKEL_VALUE) + " nickels\n";
		toReturn+=get(DIME_VALUE) + " dimes\n";
		toReturn+=get(QUARTER_VALUE) + " quarters\n";
		return toReturn;
	}
}