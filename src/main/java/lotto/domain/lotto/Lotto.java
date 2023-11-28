package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 유효한 입력이 아닙니다.");
        }
        if (Set.copyOf(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 유효한 입력이 아닙니다.");
        }
        if (isOutOfRange(numbers)) {
            throw new IllegalArgumentException("[ERROR] 유효한 입력이 아닙니다.");
        }
    }

    private boolean isOutOfRange(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(i -> i < MIN_LOTTO_NUMBER || i > MAX_LOTTO_NUMBER);
    }


    public int countMatch(Lotto otherLotto) {
        return (int) numbers.stream()
                .filter(otherLotto::contains)
                .count();
    }

    public boolean contains(int number) {
        return this.numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

}
