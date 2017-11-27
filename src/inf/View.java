package inf;

import java.io.*;
import java.util.Scanner;

public class View {
    static View view;


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ControllerStudent controllerStudent = new ControllerStudent();
        ControllerGroup controllerGroup = new ControllerGroup();
        Scanner scanner = new Scanner(System.in);
        int cases;
        while (true) {
            System.out.println("Введите: \n1 - для работы с информацией о студентах\n2 - для работы с информацией о группах" +
                    "\n3 - для выхода");
            cases = scanner.nextInt();
            switch (cases) {
                case 1: {
                    controllerStudent.menu();
                    break;
                }
                case 2:
                    controllerGroup.menu();
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

}
