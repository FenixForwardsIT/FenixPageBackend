package com.fenixforwardit.landing.controller;

import com.fenixforwardit.landing.exception.FileNotAnImageException;
import com.fenixforwardit.landing.model.UploadImageResponse;
import com.fenixforwardit.landing.service.ImageStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Walter Finkbeiner
 */
@RestController
public class UploadImageController {

    private static final Logger logger = LoggerFactory.getLogger(UploadImageController.class);

    @Autowired
    private ImageStorageService imageStorageService;

    @PostMapping("/uploadImage")
    public UploadImageResponse uploadImage(@RequestParam("image") MultipartFile image) throws FileNotAnImageException {
        if ((image.getContentType().equals("image/jpeg") || image.getContentType().equals("image/png"))) {
            throw new FileNotAnImageException(image);
        }
        String imageName = imageStorageService.storeImage(image);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(imageName)
                .toUriString();

        return new UploadImageResponse(imageName, fileDownloadUri,
                image.getContentType(), image.getSize());
    }
}
