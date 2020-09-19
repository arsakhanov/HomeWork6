package ru.arsakhanov;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * класс для создания сервера на локальном хосте
 */
public class Server {

    private static final File dir = new File("E://InnoProjects/HomeWork6");
    RecursionFiles recursionFiles = new RecursionFiles();
    private static final String NOT_FOUND = "No response for POST request";

    /**
     * Метод выполяет функцию сервера на локальном хосте
     * ждем пока придет запрос и выдает резултат
     * если запрос был Get, то выводит на консоль полную директорию проекта
     * если же запрос был Post, то выводит на консоль сообщение 404 Not Found
     */
    public void startServer() {
        try (ServerSocket server = new ServerSocket(8040)) {
            System.out.println("Server is started");
            while (true) {
                Socket clientCocket = server.accept();
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientCocket.getInputStream()));
                     PrintWriter out = new PrintWriter((clientCocket.getOutputStream()))) {

                    String url = getUrlRequest(in);
                    if (url.equals("GET")) {
                        String showDirectory = sendRequest(recursionFiles.recursionDirectory(dir));
                        out.println(showDirectory);
                        out.flush();
                    } else {
                        String error = sendRequest(NOT_FOUND);
                        out.println(error);
                        out.flush();
                    }
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
    private String getUrlRequest(BufferedReader input) throws IOException {
        String reader = input.readLine();
        return reader.split(" ")[0];
    }

    /**
     * Метод возвращает строку с правильным опианием http ответа
     *
     * @param response параметр типа String - указывается что именно нужно передать для ответа на запрос
     * @return возвращает строку с описанием http ответа и параметром текста для ответа
     */
    public String sendRequest(String response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1 404 Not Found\n");
        stringBuilder.append("Content-Type: text/html; charset=utf-8\n\n");
        stringBuilder.append(response);
        return new String(stringBuilder);
    }

}
//