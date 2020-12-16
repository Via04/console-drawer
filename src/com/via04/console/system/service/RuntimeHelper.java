package com.via04.console.system.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RuntimeHelper {
    public static ArrayList<String> runRuntime(String cmd) throws IOException, InterruptedException {
        ArrayList<String> ans = new ArrayList<>();
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd);
        process.waitFor();
        BufferedReader bf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String s = "";
        while ((s= bf.readLine()) != null) {
            System.out.println(s);
            ans.add(s);
        }
        return ans;
    }
}
