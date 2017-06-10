package com.gmail.kolesnyk.zakhar.controller;


import com.gmail.kolesnyk.zakhar.model.Model;

import java.io.File;
import java.io.IOException;

public class Controller {
    private static Model model = new Model();

    static public String loadTxtFile(File file) {
        return model.parseFileFor13Digits(file);
    }
    static public String loadXlsFile(File file) {
        return model.setNameOutput(file);
    }
    static public String moveResultToXLS() throws IOException {
        return model.writeXLSFile();
    }
}
