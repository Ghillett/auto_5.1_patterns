package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        LocalDate today = LocalDate.now();
        today = today.plusDays(shift);
        String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(today);
        return date;
    }

    public static String generateCity(String locale) {
        String[] cities = new String[]{"Казань", "Москва", "Ростов-на-Дону", "Владивосток", "Майкоп"};

        int maxCities = 4;
        int cityNumber = (int) (Math.random() * ++maxCities);

        String city = cities[cityNumber];
        return city;
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().firstName() + " " + faker.name().lastName();
        return name;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(generateCity("ru"), generateName("ru"), generatePhone("ru"));

            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
