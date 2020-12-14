package com.via04.console.writer;
import java.io.BufferedWriter;
import java.io.IOException;

//Для того чтобы лишний раз не переоткрывать файл для записи и легко получать инстанс для записи
//создадим синглтон записи в файл
//К тому же при такой "ленивой" реализации, файл откроется только тогда, когда он понадобится программе,
//а значит не будет лишних операций записи
public enum FileWriter {
    INSTANCE;

    private BufferedWriter writer = null;

    //Установка файла для записи, создание райтера
    public BufferedWriter setBufferedWriter(String filename) {
        try {
            if (this.writer == null) {
                this.writer = new BufferedWriter(new java.io.FileWriter(filename));
            }
        } catch (IOException e) {
            System.out.println("Problem with writing to file");
        }
        return this.writer;
    }

    //Метод для получения райтера, благодаря использованию синглтона инстанс после первоначальной инициализации
    //будет один и тот же, что экономит много ресурсов и кода
    public BufferedWriter getBufferedWriter() throws NullPointerException {
        if (this.writer != null) {
            return this.writer;
        }
        else {
            throw new NullPointerException("BufferedWriter not initialized");
        }
    }

    //стандартное закрытие файла
    public void close() {
        try {
            this.writer.close();
        }
        catch (IOException e) {
            System.out.println("Can't close file");
        }
    }
}
