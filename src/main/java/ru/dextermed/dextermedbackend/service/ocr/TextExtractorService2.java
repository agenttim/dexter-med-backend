package ru.dextermed.dextermedbackend.service.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Component
public class TextExtractorService2 {
    ITesseract tesseract = new Tesseract();
    //ITesseract tesseract = new Tesseract1();
    List<String> tessConfigs = new ArrayList<>(List.of("all3"));


    public void textProcess(String imagePath) {

        try {
            tesseract.setLanguage("rus+eng");
            tesseract.setDatapath("D:/tessdata/new");
            tesseract.setConfigs(tessConfigs);

            String result = tesseract.doOCR(new File(imagePath));
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
