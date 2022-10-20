
import java.io.IOException;
import java.util.Scanner;



public class Main {
    public static void main(String args[]) {
        calc("");
    }

    public static String calc(String input) {
        Function myFunction = new Function();
        boolean roman = false;
        int myIndex = -1;
        int index;
        int codeSymbol = 0;
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение:");
        input = in.nextLine();
        in.close();
        input = input.replaceAll(" ", "");
        int[] numbers = new int[4];
        numbers[0] = 43;
        numbers[1] = 45;
        numbers[2] = 42;
        numbers[3] = 47;
        int i;
        for (i = 0; numbers.length > i; i++) {
            index = input.indexOf(numbers[i]);

            if (1 > codeSymbol && index > -1) {
                codeSymbol = numbers[i];
                myIndex = index;

            } else if (1 < codeSymbol && index > -1) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    return "Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
                }

            }

        }

        if (1 > codeSymbol) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Cтрока не является математической операцией.");
                return "Cтрока не является математической операцией.";
            }

        }

        String myXString = input.substring(0, myIndex);
        String myYString = input.substring(myIndex + 1);
        int myX;
        myX = myFunction.romanToArab(myXString);
        int myY = 0;
        if (myX > 0) {
            roman = true;
            myY = myFunction.romanToArab(myYString);
            if (myY == 0) {
                System.out.println("Используются одновременно разные системы счисления.");
                return "Используются одновременно разные системы счисления.";
            }
            if (myY > myX && codeSymbol == 45) {
                System.out.println("В римской системе нет отрицательных чисел.");
                return "В римской системе нет отрицательных чисел.";
            }
        } else {
            myX = myFunction.value(myXString);
            if (myX > 0) {
                myY = myFunction.value(myYString);

            }
        }

        int rez = 0;
        if (myY > 0) {
            if (codeSymbol == 43) {
                rez = myX + myY;
            } else if (codeSymbol == 45) {
                rez = myX - myY;
            } else if (codeSymbol == 42) {
                rez = myX * myY;
            } else if (codeSymbol == 47) {
                rez = myX / myY;
            }
        }

        if (roman) {
            input = myFunction.arabToRoman(rez);
        } else {
            input = String.valueOf(rez);
        }
        System.out.println(input);
        return input;

    }
    }


    class Function {

        int romanToArab(String str) {
            int[] arab = new int[13];
            arab[0] = 1;
            arab[1] = 4;
            arab[2] = 5;
            arab[3] = 9;
            arab[4] = 10;
            arab[5] = 40;
            arab[6] = 50;
            arab[7] = 90;
            arab[8] = 100;
            arab[9] = 400;
            arab[10] = 500;
            arab[11] = 900;
            arab[12] = 1000;

            String[] roman = new String[13];
            roman[0] = String.valueOf("I");
            roman[1] = String.valueOf("IV");
            roman[2] = String.valueOf("V");
            roman[3] = String.valueOf("IX");
            roman[4] = String.valueOf("X");
            roman[5] = String.valueOf("XL");
            roman[6] = String.valueOf("L");
            roman[7] = String.valueOf("XC");
            roman[8] = String.valueOf("C");
            roman[9] = String.valueOf("CD");
            roman[10] = String.valueOf("D");
            roman[11] = String.valueOf("CM");
            roman[12] = String.valueOf("M");

            str = str.toUpperCase();

            int ret = 0;
            int i = arab.length - 1;
            int pos = 0;
            boolean equals = false;
            String subString = "";
            while (i >= 0 && (str.length() - pos) > 0) {
                if ((str.length() - pos) - roman[i].length() >= 0) {
                    equals = str.substring(pos, pos + roman[i].length()).equals(roman[i]);
                }

                if (equals) {
                    ret += arab[i];
                    pos += roman[i].length();
                    i = arab.length - 1;
                    equals = false;
                } else {
                    i--;
                }

            }

            return ret;
        }



        String arabToRoman(int number) {
            int[] arab = new int[13];
            arab[0] = 1;
            arab[1] = 4;
            arab[2] = 5;
            arab[3] = 9;
            arab[4] = 10;
            arab[5] = 40;
            arab[6] = 50;
            arab[7] = 90;
            arab[8] = 100;
            arab[9] = 400;
            arab[10] = 500;
            arab[11] = 900;
            arab[12] = 1000;

            String[] roman = new String[13];
            roman[0] = String.valueOf("I");
            roman[1] = String.valueOf("IV");
            roman[2] = String.valueOf("V");
            roman[3] = String.valueOf("IX");
            roman[4] = String.valueOf("X");
            roman[5] = String.valueOf("XL");
            roman[6] = String.valueOf("L");
            roman[7] = String.valueOf("XC");
            roman[8] = String.valueOf("C");
            roman[9] = String.valueOf("CD");
            roman[10] = String.valueOf("D");
            roman[11] = String.valueOf("CM");
            roman[12] = String.valueOf("M");


            String ret = "";
            int i = arab.length - 1;
            while (number > 0) {
                if (number >= arab[i]) {
                    ret += roman[i];
                    number -= arab[i];
                    i = arab.length - 1;
                } else {
                    i--;
                }

            }
            return ret;

        }

        int value(String MyString) throws NumberFormatException {


            int i;
            try {
                i = Integer.parseInt(MyString);
            } catch (NumberFormatException e) {
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                return 0;
            }
            if (i < 10) {
                if (0 < i) {
                    return i;
                } else {
                    System.out.println("Калькулятор принимает на вход числа  от 1 включительно, не менее.");
                    return 0;
                }
            } else {
                System.out.println("Калькулятор принимает на вход числа  до 10 включительно, не более.");
                return 0;
            }
        }
    }





