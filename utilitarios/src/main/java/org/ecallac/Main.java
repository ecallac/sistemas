package org.ecallac;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String folderPath = "C:\\Users\\efrain.calla\\Downloads\\nude\\filke"; // 📂 Cambia esto a tu carpeta

        try {
            Files.list(Paths.get(folderPath))
                    .filter(Files::isRegularFile) // Solo archivos, no carpetas
                    .forEach(path -> {
                        File file = path.toFile();
                        System.out.println(file.getName());
                        String newName = file.getName().replace('a', 'y'); // 🔄 Reemplaza '_' por '-'
//                        System.out.println(newName);
                        if (!file.getName().equals(newName)) { // Solo renombrar si hay cambios
                            File newFile = new File(file.getParent(), newName);
                            boolean renamed = file.renameTo(newFile);

                            if (renamed) {
                                System.out.println("Renombrado: " + file.getName() + " → " + newName);
                            } else {
                                System.out.println("❌ Error renombrando: " + file.getName());
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}