package edu.upenn.cis551.pncbank.bank;

import java.math.BigInteger;

/**
 * Representation of an account. Provides methods for adding or removing value from the account
 * subject to the validator.
 * 
 * @author jlautman
 *
 * @param <D> The type of the validator object that the account uses.
 */
public class Account {
  /**
   * Current account balance. Uses a BigInteger to prevent overflow/underflow.
   */
  BigInteger balance;
  String cardValidator;
  long sequence;

  public Account(long balance, String validator, long sequence) {
    this.balance = BigInteger.valueOf(balance);
    this.cardValidator = validator;
    this.sequence = sequence;
  }

  /**
   * @return the balance
   */
  public final BigInteger getBalance() {
    return this.balance;
  }

  /**
   * @return the cardValidator
   */
  public final String getCardValidator() {
    return this.cardValidator;
  }

  /**
   * @return the sequence
   */
  public final long getSequence() {
    return this.sequence;
  }

  /**
   * Updates the value in the account. The caller is responsible for making sure that the update is
   * validated by the validator and the sequence number (which is why the method is package
   * private).
   * 
   * @param delta The change to make on the account value
   * @param validation The string to validate using the account's cardValidator.
   * @param sequence The sequence number that the request sent.
   * @return false iff the wrong sequence number is found, the validation fails, or the amount
   *         results in a negative balance.
   */
  boolean updateValue(long delta) {
    BigInteger newVal = this.balance.add(BigInteger.valueOf(delta));
    if (newVal.compareTo(BigInteger.ZERO) < 0) {
      return false;
    }
    this.balance = newVal;
    this.sequence++;
    return true;
  }

  /**
   * Reads the balance of the account and increases the sequence number.
   * 
   * @return The String representation of the balance.
   */
  public String readValue() {
    this.sequence++;
    return this.balance.toString();
  }
}
