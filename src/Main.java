import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws InputException {
        System.out.println("Введите выражение для вычисления");

          calc(in.nextLine());
    }



    public static void calc(String input) throws InputException {
        String result = "";
        String[] line = input.split(" ");
        if ( line.length != 3 ) {
            throw new InputException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        int romanCheck = 0;

        for (int i = 0; i < line.length; i = i + 2) {
            switch (line[i]) {
                case "I" -> {
                    line[i] = "1";
                    romanCheck += 1;
                }
                case "II" -> {
                    line[i] = "2";
                    romanCheck += 1;
                }
                case "III" -> {
                    line[i] = "3";
                    romanCheck += 1;
                }
                case "IV" -> {
                    line[i] = "4";
                    romanCheck += 1;
                }
                case "V" -> {
                    line[i] = "5";
                    romanCheck += 1;
                }
                case "VI" -> {
                    line[i] = "6";
                    romanCheck += 1;
                }
                case "VII" -> {
                    line[i] = "7";
                    romanCheck += 1;
                }
                case "VIII" -> {
                    line[i] = "8";
                    romanCheck += 1;
                }
                case "IX" -> {
                    line[i] = "9";
                    romanCheck += 1;
                }
                case "X" -> {
                    line[i] = "10";
                    romanCheck += 1;
                }
            }
        }

        for (int i = 0; i < line.length; i += 2) {

            if(!line[i].matches("\\d+")) {
                throw new InputException("Некорректный операнд");
            }

        }

        if (romanCheck == 1) {
            throw new InputException("используются одновременно разные системы счисления");
        }


        int a = Integer.parseInt(line[0]);
        int b = Integer.parseInt(line[2]);



        if( a > 10 || a < 1 || b > 10 || b < 1) {
            throw new InputException("Операнд меньше 0 или больше 10");

        }
        switch (line[1]) {
            case "+" -> result += (a + b);
            case "-" -> result += (a - b);
            case "*" -> result += (a * b);
            case "/" -> result += (a / b);
            default -> throw new InputException("Не найден оператор выражения (+, -, *, /)");
        }

        if (romanCheck == 2) {
            if (Integer.parseInt(result) <= 0) {
           throw new InputException("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
            }
            result = getRomanNumb(result);
        }
        System.out.println(result);
    }



    static String getRomanNumb(String inNumb) {

        int inNumber = Integer.parseInt(inNumb);

        int c1 = inNumber / 100;
        int c2 = inNumber % 100;

        String c = "C".repeat(Math.max(0, c1));

        StringBuilder l = new StringBuilder();
        int l1 = c2 / 50;
        int l2 = c2 % 50;

        l.append("L".repeat(Math.max(0, l1)));

        StringBuilder x = new StringBuilder();
        int x1 = l2 / 10;
        int x2 = l2 % 10;
        x.append("X".repeat(Math.max(0, x1)));

        String units = switch (x2) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            default -> "";
        };

        if (inNumber / 10 == 9) {
            l = new StringBuilder();
            x = new StringBuilder("XC");
        } else if (x1 == 4) {
            l = new StringBuilder();
            x = new StringBuilder("XL");
        }
        return c + l + x + units;
    }
}

class InputException extends Exception {
    public InputException(String description) {
        super(description);
    }

}

