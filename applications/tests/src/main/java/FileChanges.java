import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class FileChanges {
    public static void main(String[] args) {
        String rutaCarpeta = "D:\\proyectos\\dic\\Nueva carpeta\\nude\\conocidas\\yoselin\\nude";
        String prefijo = "yos_n";
        int inicioCorrelativo = 0;

        renameFiles(rutaCarpeta, prefijo, inicioCorrelativo);
    }

    public static File createFile(String directoryPath, String fileName, String content) throws IOException {
        File directory = new File(directoryPath);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("No se pudo crear la carpeta: " + directoryPath);
        }

        File file = new File(directory, fileName);
        if (file.exists()) {
            throw new IOException("El archivo ya existe: " + file.getAbsolutePath());
        }

        Path path = Paths.get(file.getAbsolutePath());
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));
        return file;
    }

    public static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("El archivo no existe: " + filePath);
        }

        Path path = Paths.get(file.getAbsolutePath());
        byte[] content = Files.readAllBytes(path);
        return new String(content, StandardCharsets.UTF_8);
    }

    public static File updateFile(String filePath, String newContent) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("El archivo no existe: " + filePath);
        }

        Path path = Paths.get(file.getAbsolutePath());
        Files.write(path, newContent.getBytes(StandardCharsets.UTF_8));
        return file;
    }

    public static boolean deleteFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("El archivo no existe: " + filePath);
        }

        return file.delete();
    }

    public static void renameFiles(String folderPath, String prefix, int initialSequence) {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("La ruta no es valida");
            return;
        }

        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("No se pudieron listar los archivos de la carpeta");
            return;
        }

        AtomicInteger sequence = new AtomicInteger(initialSequence);

        Arrays.stream(files)
                .filter(File::isFile)
                .forEach(file -> {
                    String name = file.getName();
                    int dotIndex = name.lastIndexOf('.');
                    String extension = dotIndex == -1 ? "" : name.substring(dotIndex);
                    int number = sequence.getAndIncrement();

                    String newName = String.format("%s_%03d%s", prefix, number, extension);
                    File renamedFile = new File(file.getParent(), newName);

                    if (file.renameTo(renamedFile)) {
                        System.out.println("Renombrado: " + newName);
                    } else {
                        System.out.println("Error al renombrar: " + name);
                    }
                });
    }
}
