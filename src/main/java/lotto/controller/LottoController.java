package lotto.controller;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.LottoStore;
import lotto.domain.PurchaseAmount;
import lotto.domain.winning.WinningNumbers;
import lotto.domain.winning.WinningResult;
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
        Lotto winningLotto = ExceptionHandler.repeatUntilValid(this::handleWinningLotto);
        WinningNumbers winningNumbers = ExceptionHandler.repeatUntilValid(() -> handleWinningNumbers(winningLotto));

        WinningResult winningResult = WinningResult.from(winningNumbers, lottoStore.getIssuedLotto());
        String yield = winningResult.calculateYield(purchaseAmount);
        outputView.printWinningStatistics(winningResult.sortedResult(), yield);
    }

    private PurchaseAmount handlePurchaseAmount() {
        int purchaseAmount = inputView.readPurchaseAmount();
        return new PurchaseAmount(purchaseAmount);
    }

    private Lotto handleWinningLotto() {
        List<Integer> userInput = inputView.readWinningLotto();
        return new Lotto(userInput);
    }

    private WinningNumbers handleWinningNumbers(Lotto winningLotto) {
        int bonusNumber = inputView.readBonusNumber();
        return new WinningNumbers(winningLotto, bonusNumber);
    }
}
