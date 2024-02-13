package com.moarti.less4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class NnewMane {
    public static void main(String[] args) {
        // URL страницы для скрапинга
        String url = "https://www.geeksforgeeks.org/find-given-string-can-represented-substring-iterating-substring-n-times/";

        try {
            // Получаем HTML-код страницы
            Document document = Jsoup.connect(url).get();

            // Находим все ссики на странице
            Elements links = document.select("a[href]");

            // Выводим найденные ссылки
            for (Element link : links) {
                System.out.println(link.attr("href"));
            }

            // Другие примеры скрапинга, например, получение текста заголовков h1
            Elements headings = document.select("h2");
            for (Element heading : headings) {
                System.out.println(heading.text());
            }

            System.out.println();

            Elements readB = document.select("#read-tab");
            for (Element readb : readB) {
                System.out.println(readb.text());
            }

            System.out.println();

            Elements pre = document.select("#post-133588 > div.text > pre");
            for (Element pree:
                 pre) {
                System.out.println(pree.text());
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
