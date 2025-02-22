package com.ariel.dev22.e_commerce_backend.infra.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileConverter {
    public static byte[] loadImage(String imagePath) {
        try {
            Path path = Paths.get(imagePath);

            if (!Files.exists(path)) {
                throw new RuntimeException("Imagem não encontrada: " + path);
            }

            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
