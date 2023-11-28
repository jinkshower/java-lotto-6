package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    private static final InputView instance = new InputView();

    public static InputView getInstance() {
        return instance;
    }

    private InputView() {
    }

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효한 입력이 아닙니다.");
        }
    }

    public List<Integer> readWinningLotto() {
        System.out.println("당첨 번호를 입력해 주세요.");
        try {
            return parseStringToInteger(Console.readLine());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 유효한 입력이 아닙니다.");
        }
    }

    private List<Integer> parseStringToInteger(String text) {
        List<Integer> generated = new ArrayList<>();
        String[] trimmedText = text.replace(" ", "").split(",");
        for (String string: trimmedText) {
            generated.add(Integer.parseInt(string));
        }
        return generated;
    }
}
