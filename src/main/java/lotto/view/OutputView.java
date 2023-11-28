package lotto.view;

import java.util.List;
import lotto.domain.Lotto;

public class OutputView {

    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printIssuedLotto(List<Lotto> issuedLotto, int count) {
        System.out.printf("%n%s개를 구매했습니다.%n", count);
        for (Lotto lotto : issuedLotto) {
            System.out.println(lotto.getNumbers());
        }
    }
}
