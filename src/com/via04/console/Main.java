package com.via04.console;

import com.via04.console.factory.FigureFactory;
import com.via04.console.figure.AbstractFigure;
import com.via04.console.writer.FileWriter;

import javax.naming.NameNotFoundException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Создаем инстанс для записи в файл, при этом функция getBufferedWriter
        //будет всегда выдавать инстанс, созданный здесь
        BufferedWriter bufferedWriter = FileWriter.INSTANCE.setBufferedWriter("out.txt");
        AbstractFigure figure;
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
            System.out.println("Problem with wirting to file");
            e.printStackTrace();
        }
    }
}
