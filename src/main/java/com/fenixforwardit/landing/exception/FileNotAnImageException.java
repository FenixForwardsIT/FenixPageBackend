package com.fenixforwardit.landing.exception;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Walter Finkbeiner
 */
public class FileNotAnImageException extends Throwable {
    public FileNotAnImageException(MultipartFile file) {
        super("The file that was intended to be uploaded is not an image. It's of type " + file.getContentType());
    }
}
