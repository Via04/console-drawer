package com.via04.console.factory;

import javax.naming.NameNotFoundException;

//Сначала создадим интерфейс factory с параметром для большей гибкости кода
public interface AbstractFactory<T> {
    public T create(String classname, int ...args) throws NameNotFoundException;

}
