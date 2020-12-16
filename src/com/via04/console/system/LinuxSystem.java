package com.via04.console.system;

import com.via04.console.system.service.RuntimeHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinuxSystem implements  AbstractSystem {

    @Override
    public void showInfo() throws IOException, InterruptedException {
        String cmd = "lshw";
        System.out.println("Keep in mind that to print system info program must be run as root");
        RuntimeHelper.runRuntime(cmd);
    }

    public LinuxSystem() { };
}
