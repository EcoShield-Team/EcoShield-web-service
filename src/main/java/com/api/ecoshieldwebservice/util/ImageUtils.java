package com.api.ecoshieldwebservice.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class ImageUtils {

    public static boolean isBlurry(MultipartFile file) {
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) return false;

            Raster raster = image.getData();
            double sum = 0, sumSq = 0;
            int w = raster.getWidth(), h = raster.getHeight();

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int val = raster.getSample(x, y, 0);
                    sum += val;
                    sumSq += val * val;
                }
            }

            double mean = sum / (w * h);
            double variance = sumSq / (w * h) - mean * mean;

            return variance < 150;
        } catch (Exception e) {
            System.out.println("Error al analizar nitidez: " + e.getMessage());
            return false;
        }
    }
}