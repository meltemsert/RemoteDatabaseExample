package com.amigoscode;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.StatementException;

public class RemoteDatabaseExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11686937";
        String username = "sql11686937";
        String password = "F1QmeBEhKh";

        // JDBI bağlantısı oluştur
        Jdbi jdbi = Jdbi.create(jdbcUrl, username, password);
        System.out.println("Successfully connected to the database");

        // CRUD işlemlerini gerçekleştir

        // Tablo oluştur
        jdbi.useHandle(handle -> handle.execute("CREATE TABLE IF NOT EXISTS ad_soyad (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), surname VARCHAR(255))"));
        System.out.println("Table Created");

        // Veri ekle
        jdbi.useHandle(handle -> handle.execute("INSERT INTO ad_soyad (name, surname) VALUES (?, ?)", "Polat", "Sert"));

        // Verileri sorgula
        jdbi.useHandle(handle -> handle.createQuery("SELECT * FROM ad_soyad")
                .mapToMap()
                .forEach(row -> System.out.println("Name: " + row.get("name") + ", Surname: " + row.get("surname"))));

        // Bir veriyi sil
        jdbi.useHandle(handle -> handle.execute("DELETE FROM ad_soyad WHERE name = ?", "Emine"));

//        try (Handle handle = jdbi.open()) {
//
//            // Tablo oluştur
//            handle.execute("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, " +
//                    " name VARCHAR(100), surname VARCHAR(100))");
//            System.out.println("Table Created");
//
//            // Veri ekle
//            handle.execute("INSERT INTO users (name, surname) VALUES (?,?)", "Meltem", "Sert");
//
//            // Verileri sorgula
//            String result = handle.createQuery("SELECT name FROM users WHERE id = :id")
//                    .bind("id", 1)
//                    .mapTo(String.class)
//                    .one();
//            System.out.println("Result: " + result);
//        } catch (StatementException e) {
//            e.printStackTrace();
//        }
//    }
    }
}