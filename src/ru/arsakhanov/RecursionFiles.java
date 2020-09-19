package ru.arsakhanov;

import java.io.File;

public class RecursionFiles {

    /**
     * Метод выполняет рекурсию обхода всех каталогов и файлов в указанном каталоге
     *
     * @param folder здесь мы передаем через параметр путь к каталогу
     * @param deep   переменная для обработки глубины рекурсии, служит для того, чтобы увеличивать пробелы в рекурсии для подкаталогов
     */
    public void recursionDirectory(File folder, int deep) {

        File[] folderEntries = folder.listFiles(); //переда в массив файлы и папки в каталоге
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                System.out.println(getSpaces(deep) + entry.getName() + "\t Dir");
                recursionDirectory(entry, deep + 1);
            } else System.out.println(getSpaces(deep) + entry.getName() + "\t File");
        }
    }

    /**
     * @param a задает глубины рекурсии, если мы идет вглубь, то эта переменная увеличивается на 1 и увеличивается количество пробелов
     *          иначе количество пробелов уменьшается
     * @return возвращает нам строку sb хранящая пробелы для глубины рекурсии
     */
    private static String getSpaces(int a) {
        StringBuilder sb = new StringBuilder();
        while (a > 0) {
            sb.append(" ");
            a--;
        }
        return sb.toString();
    }
}
