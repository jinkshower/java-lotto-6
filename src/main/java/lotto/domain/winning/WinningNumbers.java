package lotto.domain.winning;

import lotto.domain.lotto.Lotto;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumbers(Lotto winningLotto, int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 유효한 입력이 아닙니다.");
        }
        if (isOutOfRange(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 유효한 입력이 아닙니다.");
        }
    }

    private boolean isOutOfRange(int bonusNumber) {
        return bonusNumber < 1 || bonusNumber > 45;
    }

    public Rank assignRank(Lotto lotto) {
        int matchCount = winningLotto.countMatch(lotto);
        boolean bonusStatus = lotto.contains(bonusNumber);
        return Rank.of(matchCount, bonusStatus);
    }
}
