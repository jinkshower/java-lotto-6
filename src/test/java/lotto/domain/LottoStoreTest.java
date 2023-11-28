package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoStoreTest {

    @DisplayName("구입 금액만큼 로또를 발행한다")
    @Test
    void createLottos() {
        LottoStore lottoStore = LottoStore.from(new PurchaseAmount(4000));

        assertThat(lottoStore.getIssuedLotto().size()).isEqualTo(4);
        System.out.println(lottoStore.getIssuedLotto());
    }

}
