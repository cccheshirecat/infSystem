package inf;

import java.io.*;
import java.util.Scanner;

public class View {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis =new FileInputStream("out.txt");
        FileOutputStream fos=new FileOutputStream("out.txt");
        ControllerStudent controllerStudent=new ControllerStudent(fis, fos);
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
