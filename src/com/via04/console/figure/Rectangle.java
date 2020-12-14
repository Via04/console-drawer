package com.via04.console.figure;

import com.via04.console.writer.FileWriter;

import java.io.BufferedWriter;
import java.io.IOException;

public class Rectangle implements AbstractFigure {
    private int width;
    private int height;

    //Пустой конструктор просто позволит нам создать экземпляр класса,
    //но тем не менее, параметры можно будет установить через сеттер
    public Rectangle() { }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    //Конструктор для квадрата
    public Rectangle(int side) {
        this.width = side;
        this.height = side;
    }

    @Override
    public void draw() throws IOException {
        // через синглтон получаем инстанс райтера в файл
        BufferedWriter fileWriter = FileWriter.INSTANCE.getBufferedWriter();
        // логика для рисования прямоугольника; запись ведется в консоль и дублируется в файл
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                System.out.print('*');
                fileWriter.write('*');
            }
            System.out.println();
            fileWriter.newLine();
        }
        System.out.println();
        fileWriter.newLine();
    }

    //Стандартные геттеры, сеттеры
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
