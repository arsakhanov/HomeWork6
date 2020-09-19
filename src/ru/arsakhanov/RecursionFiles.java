package ru.arsakhanov;

import java.io.File;

/**
 * Класс реализует метод, который возвращает
 * название папок и файло в указанной директории
 */
public class RecursionFiles {

    /**
     * Метод возвращает название папок и файлов в указанной директории
     *
     * @param folder путь к директории
     * @return возвращает название папок и файлов в текущей директории типа String
     */
    public String recursionDirectory(File folder) {
        File[] folderEntries = folder.listFiles(); //переда в массив файлы и папки в каталоге
        StringBuilder stringBuilder = new StringBuilder();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                stringBuilder.append(entry.getName()).append("\t Dir\n");
            } else stringBuilder.append(entry.getName()).append("\t File\n");
        }
        return new String(stringBuilder);
    }
}
