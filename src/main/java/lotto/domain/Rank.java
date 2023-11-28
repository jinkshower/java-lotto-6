package lotto.domain;

import java.util.Arrays;

public enum Rank {

    FIRST_PLACE(6, BonusStatus.IRRELEVANT, 2_000_000_000),
    SECOND_PLACE(5, BonusStatus.MATCHED, 30_000_000),
    THIRD_PLACE(5, BonusStatus.UNMATCHED, 1_500_000),
    FOURTH_PLACE(4, BonusStatus.IRRELEVANT, 50_000),
    FIFTH_PLACE(3, BonusStatus.IRRELEVANT, 5_000),
    NONE(0, BonusStatus.IRRELEVANT, 0);

    private final int matchCount;
    private final BonusStatus bonusStatus;
    private final int prizeMoney;

    Rank(int matchCount, BonusStatus bonusStatus, int prizeMoney) {
        this.matchCount = matchCount;
        this.bonusStatus = bonusStatus;
        this.prizeMoney = prizeMoney;
    }

    public static Rank of(int matchCount, boolean bonusStatus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matches(matchCount, bonusStatus))
                .findAny()
                .orElse(NONE);
    }

    private boolean matches(int matchCount, boolean bonusStatus) {
        return this.matchCount == matchCount && this.bonusStatus.matches(bonusStatus);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public BonusStatus getBonusStatus() {
        return bonusStatus;
    }

}
