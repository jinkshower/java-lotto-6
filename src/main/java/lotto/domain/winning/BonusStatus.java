package lotto.domain.winning;

public enum BonusStatus {

    MATCHED {
        @Override
        public boolean matches(boolean bonusMatch) {
            return bonusMatch;
        }
    },
    UNMATCHED {
        @Override
        public boolean matches(boolean bonusMatch) {
            return !bonusMatch;
        }
    },
    IRRELEVANT {
        @Override
        public boolean matches(boolean bonusMatch) {
            return true;
        }
    };

    public abstract boolean matches(boolean bonusMatch);
}
