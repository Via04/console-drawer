package com.via04.console.system;

import com.via04.console.system.service.RuntimeHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsSystem implements AbstractSystem{
    @Override
    public void showInfo() throws IOException, InterruptedException {
        String cmd = "winver";
        RuntimeHelper.runRuntime(cmd);
    }
    public WindowsSystem() { }
}
