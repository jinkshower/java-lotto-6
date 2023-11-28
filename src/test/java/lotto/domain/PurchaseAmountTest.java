package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @DisplayName("최소 구매금액 이하일시 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1000, 0, 999})
    void createPurchaseAmount(int amount) {
        assertThatThrownBy(() -> new PurchaseAmount(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기본 구매금액으로 나누어 떨어지지 않을시 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {1234, 70700})
    void createPurchaseAmount2(int amount) {
        assertThatThrownBy(() -> new PurchaseAmount(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정상적으로 구매금액을 생성한다")
    @ParameterizedTest
    @ValueSource(ints = {1000, 6000, 707000})
    void createProperInstance(int amount) {
        assertDoesNotThrow(() -> new PurchaseAmount(amount));
    }

    @DisplayName("구매금액에 따라 구매횟수를 알 수 있다")
    @Test
    void getPurchaseCount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(3000);

        assertThat(purchaseAmount.count()).isEqualTo(3);
    }

}
