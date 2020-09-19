package ru.arsakhanov;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * класс для создания сервера на локальном хосте
 */
public class Server {

    private static final File dir = new File("E://InnoProjects/HomeWork6");
    RecursionFiles recursionFiles = new RecursionFiles();

    /**
     * Метод выполяет функцию сервера на локальном хосте
     * ждем пока придет запрос и выдает резултат
     * если запрос был Get, то выводит на консоль полную директорию проекта
     * если же запрос был Post, то выводит на консоль сообщение 404 Not Found
     */
    public void startServer() {
        try (ServerSocket server = new ServerSocket(8040)) {
            System.out.println("Server is started");
            Socket clientCocket = server.accept();
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientCocket.getInputStream()))) {
                String url = getUrlRequest(in);
                if (url.equals("POST")) {
                    sendRequest();
                } else {
                    recursionFiles.recursionDirectory(dir, 0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод считывает строку из запроса в адресной строке
     *
     * @param input запрос, сделанный в строке в браузере
     * @return возращает первое слово в адресной строке,
     * а именно GET или POST
     */
    private String getUrlRequest(BufferedReader input) {
        var reader = new Scanner(input).useDelimiter("\r\n");
        var line = reader.next();
        return line.split(" ")[0];
    }

    /**
     * метод, который просто выводит на консоль "ошибку"
     * для POST запроса
     */
    private void sendRequest() {
        System.out.println("HTTP/1.1 404 Not Found");
        System.out.println("Content-Type: text/html; charset=utf-8");
        System.out.println("No response for POST request");
    }

}

