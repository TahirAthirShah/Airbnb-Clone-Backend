package com.airbnb.controller;

import com.airbnb.dto.BookingDto;
import com.airbnb.entity.Booking;
import com.airbnb.entity.Property;
import com.airbnb.entity.PropertyUser;
import com.airbnb.repository.BookingRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.service.BucketService;
import com.airbnb.service.PDFService;
import com.airbnb.service.SmsService;
import jakarta.annotation.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {
    private BookingRepository bookingRepository;
    private PropertyRepository propertyRepository;
    private PDFService pdfService;
    private BucketService bucketService;

    private SmsService smsService;
    public BookingController(BookingRepository bookingRepository, PropertyRepository propertyRepository, PDFService pdfService, BucketService bucketService, SmsService smsService) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.pdfService = pdfService;
        this.bucketService = bucketService;
        this.smsService = smsService;
    }
    @PostMapping("/createBooking/{propertyId}")
    public ResponseEntity<?> createBooking(@AuthenticationPrincipal PropertyUser user,
                                                 @RequestBody Booking booking,
                                                 @PathVariable long propertyId) throws IOException {
        booking.setPropertyUser(user);
        Optional<Property> opProperty = propertyRepository.findById(propertyId);
        Property property = opProperty.get();
        int propertyPrice = property.getNightlyPrice();
        int totolNights = booking.getTotalNights();
        int totalPrice = propertyPrice*totolNights;
        booking.setProperty(property);
        booking.setTotalPrice(totalPrice);

        Booking createdBooking = bookingRepository.save(booking);

        BookingDto dto = new BookingDto();
        dto.setBookingId(createdBooking.getId());
        dto.setGuestName(createdBooking.getGuestName());
        dto.setTotalNights(createdBooking.getTotalNights());
        dto.setTotalPrice(createdBooking.getTotalPrice());

        //Create PDF with Booking Confirmation
        boolean b = pdfService.generatePDF("/Users/ankitkamal/Downloads/testing/" + "Booking_Confirmation_id"
                + createdBooking.getId() + ".pdf", dto);

        if(b){
            //Upload your  file into bucket
            MultipartFile file = BookingController.convert("/Users/ankitkamal/Downloads/testing/" + "Booking_Confirmation_id"
                    + createdBooking.getId() + ".pdf");
            String uploadedFileUrl = bucketService.uploadFile(file, "myawsbucket7079");
            System.out.println(uploadedFileUrl);
            smsService.sendSms("+916393148839","Your booking is confirmed "+uploadedFileUrl);

        }else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }
    public static MultipartFile convert(String filePath) throws IOException {

        // Load the file from the specified path
        File file = new File(filePath);

        // Read the file content into a byte array
        byte[] fileContent = Files.readAllBytes(file.toPath());

        // Convert byte array to a Resource (ByteArrayResource)
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        // Create MultipartFile from Resource
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return file.getName();
            }

            @Override
            public String getOriginalFilename() {
                return file.getName();
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return fileContent.length == 0;
            }

            @Override
            public long getSize() {
                return fileContent.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return fileContent;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return resource.getInputStream();
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                Files.write(dest.toPath(), fileContent);
            }
        };
        return multipartFile;
    }
}

