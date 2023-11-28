package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        LottoController lottoController = new LottoController(inputView);
        lottoController.run();
    }
}
