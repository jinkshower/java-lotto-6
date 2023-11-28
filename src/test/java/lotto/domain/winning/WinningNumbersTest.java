package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.winning.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @DisplayName("보너스 번호가 당첨번호와 중복되면 예외가 발생한다")
    @Test
    void createWinningNumbersDuplicated() {
        assertThatThrownBy(() -> new WinningNumbers(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), 1));
    }

}