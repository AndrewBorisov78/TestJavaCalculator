package Calculator;

import java.util.Scanner;

public class Calculator {
    private static boolean its_an_arabic_numbers = true;
    private static String[] pars(String input) {
        String[] parsed_input = input.split(" ");
        if (parsed_input.length != 3) {
            Scanner input_a_value_again = new Scanner(System.in);
            System.out.println("\uD83E\uDD37 УПС\u2757. Введите выражение, разделяя каждый символ _пробелом_ \u2757");
            input = input_a_value_again.nextLine();
            return pars(input);
        } else {
            return parsed_input;
        }
    }

    public static void main(String[] args) {

        Scanner input_a_value = new Scanner(System.in);
        System.out.println("\uD83D\uDE4B Привет\u2757");
        System.out.print("\uD83C\uDF52 Введите выражение: ");
        String input = input_a_value.nextLine();

        while (!input.isEmpty()) {
            String[] parsed_input = Calculator.pars(input);
            String operation = parsed_input[1];
            Number values;
            int value1 = 0;
            int value2 = 0;
            // Переводим в int. Если введены римские, выкинет исключение
            try {
                value1 = Integer.parseInt(parsed_input[0]);
                value2 = Integer.parseInt(parsed_input[2]);

            } catch (NumberFormatException e) {
                its_an_arabic_numbers = false;
                System.out.println("\uD83C\uDFDB Введены римские цифры");
            }

            if (its_an_arabic_numbers) {
                values = new Arabic(value1, value2);
            } else {
                values = new Romes(parsed_input[0], parsed_input[2]);
            }

            if (operation.equals("+")) {
                values.sum();
            } else if (operation.equals("-")) {
                values.sub();
            } else if (operation.equals("/") || operation.equals(":")) {
                values.div();
            } else if (operation.equals("*") || operation.equals("x")) {
                values.mul();
            }

            if (its_an_arabic_numbers) {
                System.out.println("\uD83D\uDC81 Ответ: " + values.getResult());
            } else {
                System.out.println("\uD83D\uDC81 Ответ: " + values.getStringResult());
            }
            System.out.print("\uD83C\uDF52 Введите следующее выражение: ");
            input = input_a_value.nextLine();
        }
    }
}
