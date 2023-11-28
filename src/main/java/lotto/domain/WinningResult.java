package lotto.domain;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinningResult {

    private static final String YIELD_FORMAT = "#,##0.0%";
    private final Map<Rank, Integer> winningResult;

    private WinningResult(Map<Rank, Integer> winningResult) {
        this.winningResult = new EnumMap<>(winningResult);
    }

    public static WinningResult from(WinningNumbers winningNumbers, List<Lotto> issuedLotto) {
        Map<Rank, Integer> winningResult = initialize();
        for (Lotto lotto : issuedLotto) {
            Rank rank = winningNumbers.assignRank(lotto);
            winningResult.put(rank, winningResult.get(rank) + 1);
        }
        return new WinningResult(winningResult);
    }

    private static Map<Rank, Integer> initialize() {
        Map<Rank, Integer> initialized = new EnumMap<>(Rank.class);
        Arrays.stream(Rank.values())
                .forEach(rank -> initialized.put(rank, 0));
        return initialized;
    }

    public int totalPrizeMoney() {
        return winningResult.entrySet().stream()
                .mapToInt(it -> it.getKey().getPrizeMoney() * it.getValue())
                .sum();
    }

    public Map<Rank, Integer> getWinningResult() {
        return new EnumMap<>(winningResult);
    }

    public String calculateYield(PurchaseAmount purchaseAmount) {
        double yield = (double) totalPrizeMoney() / purchaseAmount.getPurchaseAmount();

        DecimalFormat decimalFormat = new DecimalFormat(YIELD_FORMAT);
        return decimalFormat.format(yield);
    }

    public Map<Rank, Integer> sortedResult() {
        Map<Rank, Integer> sorted = new LinkedHashMap<>();

        winningResult.keySet().stream()
                .filter(rank -> rank != Rank.NONE)
                .sorted(Comparator.comparingInt(Rank::getPrizeMoney))
                .forEach(rank -> sorted.put(rank, winningResult.get(rank)));
        return sorted;
    }
}
