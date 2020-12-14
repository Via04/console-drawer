package com.via04.console.factory;

import com.via04.console.figure.AbstractFigure;
import com.via04.console.figure.Circle;
import com.via04.console.figure.Rectangle;

import javax.naming.NameNotFoundException;

//Теперь делаем реализацию этого интрефейса
public class FigureFactory implements AbstractFactory<AbstractFigure>{

    //Так как может быть что передаваемого класса(classname) не существует, метод create выбрасывает
    //исключение NameNotFound
    @Override
    public AbstractFigure create(String classname, int... args) throws NameNotFoundException {
        //В переменную args записываются аргументы для конструктора
        int args_num = args.length;
        //Смотрим какой класс хотят получить от factory
        if("Circle".equalsIgnoreCase(classname)) {
            //Но так как конструкторы разные, нужно рассматривать все варианты
            if (args_num == 0) {
                return new Circle();
            }
            else if (args_num == 1) {
                return new Circle(args[0]);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        else if("Rectangle".equalsIgnoreCase(classname)) {
            if (args_num == 0) {
                return new Rectangle();
            }
            else if(args_num == 1) {
                return new Rectangle(args[0]);
            }
            else if(args_num == 2) {
                return new Rectangle(args[0], args[1]);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new NameNotFoundException();
        }
    }
    public FigureFactory() {}
}
