package lotto.controller;

import lotto.domain.LottoStore;
import lotto.domain.PurchaseAmount;
import lotto.util.ExceptionHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PurchaseAmount purchaseAmount = ExceptionHandler.repeatUntilValid(this::handlePurchaseAmount);
        LottoStore lottoStore = LottoStore.from(purchaseAmount);

        outputView.printIssuedLotto(lottoStore.getIssuedLotto(), purchaseAmount.count());
    }

    private PurchaseAmount handlePurchaseAmount() {
        int purchaseAmount = inputView.readPurchaseAmount();
        return new PurchaseAmount(purchaseAmount);
    }
}
