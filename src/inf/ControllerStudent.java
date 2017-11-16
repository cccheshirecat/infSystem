package inf;

import java.util.Scanner;

public class ControllerStudent {
    public void menu(){
        System.out.println("Введите:\n 1 - если хотите получить данные студента по его имени и фамилии\n" +
                "2 - если хотите добваить нового студента \n" +
                "3 - если хотите изменить данные студента по его имени и фамилии\n");
        Scanner scanner=new Scanner(System.in);
        int cases=scanner.nextInt();
        switch (cases){
            case 1: getStudent();
            case 2:addStudent();
            case 3:setStudent();
        }
    }
    public void getStudent(){
        
    }
    public void addStudent(){

    }
    public void setStudent(){

    }

}
