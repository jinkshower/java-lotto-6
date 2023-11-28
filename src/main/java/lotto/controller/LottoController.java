package lotto.controller;

import lotto.domain.PurchaseAmount;
import lotto.util.ExceptionHandler;
import lotto.view.InputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        PurchaseAmount purchaseAmount = ExceptionHandler.repeatUntilValid(this::handlePurchaseAmount);
    }

    private PurchaseAmount handlePurchaseAmount() {
        int purchaseAmount = inputView.readPurchaseAmount();
        return new PurchaseAmount(purchaseAmount);
    }
}
