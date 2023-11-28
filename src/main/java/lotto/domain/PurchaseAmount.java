package lotto.domain;

public class PurchaseAmount {

    private static final int MIN_PURCHASE_AMOUNT = 1_000;

    private final int purchaseAmount;

    public PurchaseAmount(int userInput) {
        validate(userInput);
        this.purchaseAmount = userInput;
    }

    private void validate(int userInput) {
        if (userInput % MIN_PURCHASE_AMOUNT != 0) {
            throw new IllegalArgumentException();
        }
        if (userInput < MIN_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException();
        }
    }

    public int count() {
        return purchaseAmount / MIN_PURCHASE_AMOUNT;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }
}
