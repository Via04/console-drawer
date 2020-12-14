package com.via04.console.figure;

import com.via04.console.writer.FileWriter;

import java.io.BufferedWriter;
import java.io.IOException;

//Определяем фигуру круг как потомок абстрактной фигуры
public class Circle implements AbstractFigure {
    private int radius;

    //Пустой конструктор просто позволит нам создать экземпляр класса,
    //но тем не менее, радиус можно будет установить через сеттер
    public Circle() { }

    //Конструктор сразу с установкой радиуса
    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() throws IOException {
        // через синглтон получаем инстанс райтера в файл
        BufferedWriter fileWriter = FileWriter.INSTANCE.getBufferedWriter();
        // логика для рисования окружности; запись ведется в консоль и дублируется в файл
        for(int y = 0; y < this.radius * 2; y++) {
            for(int x = 0; x < this.radius * 2; x++) {
                if(isInCircle(x, y)) {
                    System.out.print('*');
                    fileWriter.write('*');
                }
                else {
                    System.out.print(' ');
                    fileWriter.write(' ');
                }
            }
            System.out.println();
            fileWriter.newLine();
        }
        System.out.println();
        fileWriter.newLine();
    }

    //Вспомогательные методы
    private boolean isInCircle(int x, int y) {
        return Math.pow(x - this.radius, 2) + Math.pow(y - this.radius, 2) <= Math.pow(this.radius, 2);
    }

    //Стандартные геттеры, сеттеры
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
