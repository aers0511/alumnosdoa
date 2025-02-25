package com.alumnos.daoalumnos.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    // Cargar dotenv una sola vez (Singleton)
    private static final Dotenv dotenv = Dotenv.load();

    // Exponer las variables como constantes o métodos
    public static final String DB_URL = dotenv.get("DB_URL");
    public static final String DB_USERNAME = dotenv.get("DB_USERNAME");
    public static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    // Método auxiliar para obtener cualquier variable
    public static String get(String key) {
        return dotenv.get(key);
    }
}