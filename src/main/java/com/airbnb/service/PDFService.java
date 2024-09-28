package com.airbnb.service;

import com.airbnb.dto.BookingDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class PDFService {

    public boolean generatePDF(String filename, BookingDto dto){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filename));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk bookingConfirmation = new Chunk("Booking Confirmation", font);
            Chunk guestName = new Chunk("Guest Name: " +dto.getGuestName(), font);
            Chunk totalNights = new Chunk("Total Nights: "+dto.getTotalNights(), font);
            Chunk totalPrice = new Chunk("Total Price: "+ dto.getTotalPrice(), font);

            document.add(bookingConfirmation);
            document.add(new Paragraph("\n"));
            document.add(guestName);
            document.add(new Paragraph("\n"));
            document.add(totalNights);
            document.add(new Paragraph("\n"));
            document.add(totalPrice);
            document.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
