package com.via04.console;

import com.via04.console.event.EventCallback;
import com.via04.console.event.InputKey;
import com.via04.console.factory.FigureFactory;
import com.via04.console.factory.SystemFactory;
import com.via04.console.figure.AbstractFigure;
import com.via04.console.system.AbstractSystem;
import com.via04.console.writer.FileWriter;

import javax.naming.NameNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        //Создаем инстанс для записи в файл, при этом функция getBufferedWriter
        //будет всегда выдавать инстанс, созданный здесь
        BufferedWriter bufferedWriter = FileWriter.INSTANCE.setBufferedWriter("out.txt");
        AbstractFigure figure;
        AbstractSystem system;
        SystemFactory systemFactory = new SystemFactory();
        InputKey registrator = new InputKey(new EventCallback() {
            //Мы передаем собственное действие в регистратор, которое будет совершаться при вызове
            //clickButton. Особенность в том, что мы просто написали, что должно совершаться какое-то действие
            //но при этом можем не определять какое, а сделать это потом в любом месте кода
            //такой подход называется callback, и без интерфейса его не реализовать (ну точней можно, но тогда при реализации нельзя будет использовать наследование других классов),
            //я познакомился с ним, когда делал приложения на андроид,
            //там многие функции взаимодействия с пользователем реализованы через callback
            @Override
            public boolean execute(String... args) {
                boolean b = false;
                for (String s: args) {
                    //В данном случае просто печатаем переданные параметры
                    System.out.println(s);
                    //и если q на конце, то выдаем true
                    if (s.endsWith("q")) {
                        b = true;
                    }
                }
                return b;
            }
        });
        //Создаем инстанс для factory
        FigureFactory factory = new FigureFactory();
        try {
            //Так как figure старший класс для всех классов factory, то здесь спокойно работает приведение типов
            //При этом factory создаст объект созданный по запрошенным параметрам
            figure = factory.create("circle", 10);
            //Внутри draw вызывается тот же инстанс, что был создан вначале этого файла
            //и хотя имя файла или ссылка на райтер не передается, ее можно легко получить с помощью
            //getBufferedWriter внутри самой функции draw
            figure.draw();
            figure = factory.create("rectangle", 15, 5);
            figure.draw();
            bufferedWriter.close();
        } catch (NameNotFoundException e) {
            System.out.println("no such class in a factory");
        } catch (IOException e) {
            System.out.println("Problem with writing to file");
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("input smth: ");
        boolean tmp = true;
        while(tmp) {
            tmp = !registrator.clickButton(scanner.nextLine());
        }
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            try {
                system = systemFactory.create("WindowsSystem");
                system.showInfo();
            } catch (NameNotFoundException e) {
                System.out.println("No name found in SystemFactory");
            }
        }
        else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            try {
                system = systemFactory.create("LinuxSystem");
                system.showInfo();
            } catch (NameNotFoundException e) {
                System.out.println("name LinuxSystem is not found in SystemFactory");
            }
        }
        else {
            System.out.println("This os is not implemented");
        }
    }
}
