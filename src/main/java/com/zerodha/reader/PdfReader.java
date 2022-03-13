package com.zerodha.reader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class PdfReader {
    public void readLocalPdfFile () {
        try {
            PDDocument document = PDDocument.load(new File("C:\\Users\\Microsoft\\Downloads" +
                    "\\psycology\\2020-06-01-contract-notes_PA1326.pdf"), "AIMPT3847Q");
            System.out.println(document.getNumberOfPages());
            PDFTextStripper stripper = new PDFTextStripper();
            String pdfFileInText = stripper.getText(document);
            String[] lines = pdfFileInText.split("\r\n|\r|\n");
            Optional<String> str = Arrays.asList(lines).stream()
                    .filter(s -> s.contains("Net amount receivable by Client / (payable by Client)"))
                    .findFirst();
            String last = Stream.of(str.get().split(" ")).reduce((first, second) -> second)
                    .orElse(null);
            System.out.println(last);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
