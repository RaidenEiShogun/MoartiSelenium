package com.moarti.less4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Main22{
    public static void main(String[] args) {
        String url = "https://www.geeksforgeeks.org/find-given-string-can-represented-substring-iterating-substring-n-times/";
        try{
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");

            for (Element link:
                 links) {
                System.out.println(link.attr("href"));
            }

            //элемент
            Elements headings = doc.select("h2");
            for (Element h2 : headings) {
                System.out.println(h2.text());
            }
            System.out.println();

            Elements readB = doc.select("#read-tab");
            for (Element readb : readB) {
                System.out.println(readb.text());
            }

            System.out.println();

            Elements pre = doc.select("#post-133588 > div.text > pre");
            for (Element pree:
                    pre) {
                System.out.println(pree.text());
            }

            System.out.println();

            //айди
            Elements coursesButt = doc.select("#nav_tab_courses");
            for (Element txtB:
                    coursesButt) {
                System.out.println(txtB.text().toUpperCase());
            }
            System.out.println();

            //через селектор
            Elements tutorials = doc.select("#related-tutorials > div > div.new-top-bar.top-bar-title");
            for (Element tutorial:
                    tutorials) {
                System.out.println(tutorial.text().isBlank());
            }


        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
