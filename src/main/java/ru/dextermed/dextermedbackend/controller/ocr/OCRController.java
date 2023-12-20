package ru.dextermed.dextermedbackend.controller.ocr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dextermed.dextermedbackend.service.ocr.TextExtractorService;

@RestController
@RequestMapping("/api/ocr")
public class OCRController {

    private final TextExtractorService textExtractor;

    @Autowired
    public OCRController(TextExtractorService textExtractor) {
        this.textExtractor = textExtractor;
    }

    @PostMapping("/extract-save-text")
    public String extractTextAndSave(@RequestParam("imagePath") String imagePath,
                                     @RequestParam("outputPath") String outputPath) {
        textExtractor.extractAndSaveText(imagePath, outputPath);
        return "Результат успешно записан в файл: " + outputPath;
    }

    @PostMapping("/extract-text")
    public String extractText(@RequestParam("imagePath") String imagePath) {
        return textExtractor.extractText(imagePath);
    }

    @PostMapping("/extract-text-from-pdf")
    public String extractTextFromPDF(@RequestParam("pdfPath") String pdfPath) {
        return textExtractor.extractTextFromPDF(pdfPath);
    }

    @PostMapping("/extract-text-from-pdf-save")
    public String extractTextFromPDF(@RequestParam("pdfPath") String pdfPath, @RequestParam("outPath") String outPath) {
        return textExtractor.extractTextFromPDF(pdfPath, outPath);
    }
}
