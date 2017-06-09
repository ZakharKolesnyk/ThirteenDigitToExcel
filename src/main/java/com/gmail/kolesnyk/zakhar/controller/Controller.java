package com.gmail.kolesnyk.zakhar.controller;


import com.gmail.kolesnyk.zakhar.model.Model;

import java.io.File;
import java.io.IOException;

public class Controller {
    private static Model model = new Model();

    static public void loadFile(File file) {
        model.parseFileFor13Digits(file);
    }
    static public void moveResultToXLS() throws IOException {
        model.writeXLSFile();
    }
}
