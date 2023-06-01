package Calculator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение формата 'a + b':");
        String str = scn.nextLine();
        System.out.println("Равно: " + calc(str));
    }

    public static String calc(String input) throws Exception {
        String[] strs = input.split(" ");
        if (strs.length != 3 || strs[1].length() != 1){
            throw new Exception("Неверный формат ввода");
        }
        char c = strs[1].charAt(0);
        boolean b = false;
        try {
            Integer.parseInt(strs[0]);
            Integer.parseInt(strs[2]);
        }catch (NumberFormatException e) {
            strs[0] = converter(strs[0]);
            strs[2] = converter(strs[2]);
            b = true;
        }
        int a1;
        int a2;
        try {
            a1 = Integer.parseInt(strs[0]);
            a2 = Integer.parseInt(strs[2]);
        }catch (NumberFormatException e){
            throw new Exception("Нельзя в одном выражении использовать арабские и римские числа");
        }
        if (a1 > 10 || a1 < 1 || a2 > 10 || a2 < 1) {
            throw new  Exception("Числа должны быть от 1 до 10");
        }
        int res = switch (c) {
            case '+' -> a1 + a2;
            case '-' -> a1 - a2;
            case '*' -> a1 * a2;
            case '/' -> a1 / a2;
            default -> throw new Exception("Неверный арифметический знак");
        };
        input = Integer.toString(res);
        if (!b){
            return input;
        }else {
            return converter(input);
        }
    }

    static String converter(String input) throws Exception{
        String[] rom1 = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] rom2 = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        int res;
        if (Arrays.asList(rom1).contains(input)) {
            for (int i = 0; i < rom1.length; i++) {
                if (input.equals(rom1[i])) {
                    input = Integer.toString(i);
                }
            }
        }else {
            try {
                res = Integer.parseInt(input);
                if (res <= 0 ) {
                    throw new Exception("В римской системе ответ может быть только положительным");
                } else {
                    input = rom2[res / 10] + rom1[res % 10];
                }
            }
            catch (NumberFormatException e){
                throw new Exception("Можно вводить только арабские целые числа от 1 до 10 и римские от I до X");
            }
        }
        return input;
    }
}