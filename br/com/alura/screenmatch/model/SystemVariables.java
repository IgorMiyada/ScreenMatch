package br.com.alura.screenmatch.model;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SystemVariables {
    public static final String FOLDER_PATH = "C:\\___\\___\\___\\ScreenMatchUsers\\";

    public static  final Path USERS_DATA = Paths.get("usersData.json");

    private static  final String apiKey = "d4c31266";

    public static String getFolderPath() {
        return FOLDER_PATH;
    }

    public static Path getUsersData() {
        return USERS_DATA;
    }

    public static String getApiKey() {
        return apiKey;
    }
}
