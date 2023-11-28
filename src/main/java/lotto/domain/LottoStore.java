package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {

    private final List<Lotto> issuedLotto;

    private LottoStore(List<Lotto> issuedLotto) {
        this.issuedLotto = new ArrayList<>(issuedLotto);
    }

    public static LottoStore from(PurchaseAmount purchaseAmount) {
        List<Lotto> issuedLotto = new ArrayList<>();
        NumberGenerator numberGenerator = new LottoNumberGenerator();

        for (int i = 0; i < purchaseAmount.count(); i++) {
            issuedLotto.add(new Lotto(numberGenerator.generate()));
        }
        return new LottoStore(issuedLotto);
    }

    public List<Lotto> getIssuedLotto() {
        return new ArrayList<>(issuedLotto);
    }

}
