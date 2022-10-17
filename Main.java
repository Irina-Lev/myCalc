
import java.io.IOException;
import java.util.Scanner;



public class Main {
public static  void main(String args[]) {
calc("");
}

    public static String calc (String input)  {
        Function myFunction = new Function();
        int myIndex = -1;
        int index ;
        int codeSymbol = 0;
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение:");
        input = in.nextLine();
        in.close();
        input = input.replaceAll(" ", "");
        int [] numbers = new int[4];
        numbers[0]=43;
        numbers[1]=45;
        numbers[2]=42;
        numbers[3]=47;
        int i;
        for (i=0; numbers.length > i; i++ ){
            index = input.indexOf(numbers[i]);

            if ( 1 > codeSymbol  &&  index > -1){
                codeSymbol = numbers[i];
                myIndex = index;

            } else if ( 1 < codeSymbol  &&  index > -1) {
                try {
                    throw new IOException();
                }catch (IOException e){
                    System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    return "Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
                }

            }

        }

        if ( 1 > codeSymbol) {try {
            throw new IOException();
        }catch (IOException e){
            System.out.println("Cтрока не является математической операцией.");
            return "Cтрока не является математической операцией.";
        }

        }

        String myXString = input.substring(0, myIndex);
        String myYString = input.substring(myIndex + 1);
        int  myX = myFunction.value(myXString);
        if (myX>0) {
            int  myY = myFunction.value(myYString);
            if (myY>0 ) {
                if (codeSymbol == 43) {
                    System.out.println(myX + myY);
                    input = String.valueOf(myX + myY);
                } else if (codeSymbol == 45)  {
                    System.out.println(myX - myY);
                    input = String.valueOf(myX - myY);
                } else if (codeSymbol == 42) {
                    System.out.println(myX * myY);
                    input = String.valueOf(myX * myY);
                } else if (codeSymbol == 47) {
                    System.out.println(myX / myY);
                    input = String.valueOf(myX / myY);
                }
            }

        }

        return input;
    }

}

class Function {

    int value(String input) throws NumberFormatException {
        int i;
        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            return 0;
        }
        if (i<10 ) {
            if (0<i) {
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

