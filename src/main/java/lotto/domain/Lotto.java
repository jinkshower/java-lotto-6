package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_SIZE;
import static lotto.domain.LottoConstants.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoConstants.MIN_LOTTO_NUMBER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
        validateRange(numbers);
    }

    // TODO: 추가 기능 구현
    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE.getValue()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        Set<Integer> tmp = new HashSet<>(numbers);
        if (tmp.size() != numbers.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateRange(List<Integer> numbers) {
        Stream<Integer> filtered = numbers.stream()
                .filter(i -> i < MIN_LOTTO_NUMBER.getValue() || i > MAX_LOTTO_NUMBER.getValue());
        if (filtered.findAny().isPresent()) {
            throw new IllegalArgumentException();
        }
    }
}
