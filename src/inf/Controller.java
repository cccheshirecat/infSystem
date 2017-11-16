package inf;

import java.io.InputStream;
import java.util.Scanner;

public class Controller{
    public static void main(String[] args){
        ControllerStudent controllerStudent=new ControllerStudent();
        ControllerGroup controllerGroup=new ControllerGroup();
        System.out.println("Введите: \n1 - для работы с информацией о студентах\n 2 - для работы с информацией о группах");
        Scanner scanner=new Scanner(System.in);
        int cases=scanner.nextInt();
        switch (cases){
            case 1: controllerStudent.menu();
            case 2: controllerGroup.menu();
        }

    }
}
