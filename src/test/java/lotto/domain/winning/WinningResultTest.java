package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lotto.domain.PurchaseAmount;
import lotto.domain.lotto.Lotto;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningNumbers;
import lotto.domain.winning.WinningResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningResultTest {

    private final WinningNumbers winningNumbers =
            new WinningNumbers(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

    static Stream<Arguments> provideSampleLotto() {
        return Stream.of(
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                                new Lotto(List.of(1, 2, 3, 4, 7, 8)),
                                new Lotto(List.of(1, 2, 3, 7, 8, 9))),
                        Map.of(
                                Rank.FIRST_PLACE, 1,
                                Rank.SECOND_PLACE, 1,
                                Rank.THIRD_PLACE, 1,
                                Rank.FOURTH_PLACE, 1,
                                Rank.FIFTH_PLACE, 1
                        )
                )
        );
    }

    @DisplayName("당첨결과를 확인할 수 있다")
    @ParameterizedTest
    @MethodSource("provideSampleLotto")
    void createWinningResult(List<Lotto> issuedLotto, Map<Rank, Integer> expected) {
        WinningResult winningResult = WinningResult.from(winningNumbers, issuedLotto);

        assertThat(winningResult.getWinningResult()).isEqualTo(expected);
    }

    @DisplayName("수익률을 구할 수 있다")
    @ParameterizedTest
    @MethodSource("provideSampleLotto")
    void createYield(List<Lotto> issuedLotto) {
        WinningResult winningResult = WinningResult.from(winningNumbers, issuedLotto);

        assertThat(winningResult.calculateYield(new PurchaseAmount(5000)))
                .isEqualTo("40,631,100.0%");
    }
}