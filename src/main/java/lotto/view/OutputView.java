package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import lotto.domain.BonusStatus;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {

    private static final String MATCH_FORMAT = "%d개 일치";
    private static final String BONUS_MATCH = ", 보너스 볼 일치";
    private static final String MONEY_FORMAT = " (%,d원)";
    private static final String COUNT_FORMAT = " - %d개";

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

    public void printWinningStatistics(Map<Rank, Integer> winningResult, String yield) {
        System.out.println("당첨 통계");
        System.out.println("---");
        winningResult.forEach(this::printResult);
        System.out.printf("총 수익률은 %s입니다.", yield);
    }

    private void printResult(Rank rank, int count) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(MATCH_FORMAT, rank.getMatchCount()));

        if (rank.getBonusStatus() == BonusStatus.MATCHED) {
            stringBuilder.append(BONUS_MATCH);
        }
        stringBuilder.append(String.format(MONEY_FORMAT, rank.getPrizeMoney()));
        stringBuilder.append(String.format(COUNT_FORMAT, count));

        System.out.println(stringBuilder);
    }

}
