package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningResult {

    private final Map<Rank, Integer> winningResult;

    private WinningResult(Map<Rank, Integer> winningResult) {
        this.winningResult = new EnumMap<>(winningResult);
    }

    public static WinningResult from(WinningNumbers winningNumbers, List<Lotto> issuedLotto) {
        Map<Rank, Integer> winningResult = new EnumMap<>(Rank.class);
        for (Lotto lotto : issuedLotto) {
            Rank rank = winningNumbers.assignRank(lotto);
            winningResult.put(rank, winningResult.getOrDefault(rank, 0) + 1);
        }
        return new WinningResult(winningResult);
    }

    public int totalPrizeMoney() {
        return winningResult.entrySet().stream()
                .mapToInt(it -> it.getKey().getPrizeMoney() * it.getValue())
                .sum();
    }

    public Map<Rank, Integer> getWinningResult() {
        return new EnumMap<>(winningResult);
    }
}
