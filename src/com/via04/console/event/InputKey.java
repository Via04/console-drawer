package com.via04.console.event;

public class InputKey {
    EventCallback action;

    public InputKey(EventCallback action) {
        this.action = action;
    }
    public boolean clickButton(String code) {
        boolean b;
        b = action.execute(code);
        return b;
    }
}
