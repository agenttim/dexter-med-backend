package ru.dextermed.dextermedbackend.controller.ocr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dextermed.dextermedbackend.service.ocr.TextExtractorService;
import ru.dextermed.dextermedbackend.service.ocr.TextExtractorService2;

@RestController
public class OCRController2 {

    private final TextExtractorService2 textExtractor;

    @Autowired
    public OCRController2(TextExtractorService2 textExtractor) {
        this.textExtractor = textExtractor;
    }

    /*@PostMapping("/extract-save-text")
    public String extractTextAndSave(@RequestParam("imagePath") String imagePath,
                                     @RequestParam("outputPath") String outputPath) {
        textExtractor.extractAndSaveText(imagePath, outputPath);
        return "Результат успешно записан в файл: " + outputPath;
    }*/

    @PostMapping("/extract-text")
    public void extractText(@RequestParam("imagePath") String imagePath) {
        textExtractor.textProcess(imagePath);
    }

    /*@PostMapping("/extract-text-from-pdf")
    public String extractTextFromPDF(@RequestParam("pdfPath") String pdfPath) {
        return textExtractor.extractTextFromPDF(pdfPath);
    }

    @PostMapping("/extract-text-from-pdf-save")
    public String extractTextFromPDF(@RequestParam("pdfPath") String pdfPath, @RequestParam("outPath") String outPath) {
        return textExtractor.extractTextFromPDF(pdfPath, outPath);
    }*/
}
