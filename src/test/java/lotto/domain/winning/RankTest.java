package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import lotto.domain.winning.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @DisplayName("일치개수와 보너스여부로 등수를 정할 수 있다")
    @ParameterizedTest
    @MethodSource("provideMatchCountAndBonus")
    void createRank(int matchCount, boolean bonusStatus, Rank expected) {
        Rank actual = Rank.of(matchCount, bonusStatus);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideMatchCountAndBonus() {
        return Stream.of(
                Arguments.of(6, false, Rank.FIRST_PLACE),
                Arguments.of(5, true, Rank.SECOND_PLACE),
                Arguments.of(5, false, Rank.THIRD_PLACE),
                Arguments.of(4, true, Rank.FOURTH_PLACE),
                Arguments.of(3, false, Rank.FIFTH_PLACE),
                Arguments.of(2, true, Rank.NONE)
        );
    }
}
