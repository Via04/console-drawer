package com.via04.console.factory;

import com.via04.console.system.AbstractSystem;
import com.via04.console.system.LinuxSystem;
import com.via04.console.system.WindowsSystem;

import javax.naming.NameNotFoundException;

public class SystemFactory implements AbstractFactory<AbstractSystem> {
    @Override
    public AbstractSystem create(String classname, int... args) throws NameNotFoundException {
        if (args.length > 0) {
            throw new IllegalArgumentException();
        }
        if("WindowsSystem".equals(classname)) {
            return new WindowsSystem();
        }
        else if("LinuxSystem".equals(classname)) {
            return new LinuxSystem();
        }
        else {
            throw new NameNotFoundException();
        }
    }
}
