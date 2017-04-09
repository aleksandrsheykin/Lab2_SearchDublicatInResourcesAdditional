package com.company;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by admin on 08.04.2017.
 */
public class Searcher extends Thread {
    String res;

    public Searcher(String res) {
        this.res = res;
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            URL url = new URL(res);
            Scanner scanner = new Scanner(url.openStream());
            String word;

            while (scanner.hasNext()) {
                word = scanner.next();

                if (Pattern.compile("[a-zA-Z]+").matcher(word).matches()) {
                    System.out.println("File "+url+" has eng word");
                    return;
                }
                word = word.replaceAll("[^а-яА-Я]+", "");

                synchronized (Main.wordSetObject.wordSet) {
                    if (Main.wordSetObject.flStop.get()) { return; }
                    if (!Main.wordSetObject.wordSet.add(word)) {
                        System.out.println("word '"+word+"' repeated in file "+res);
                        Main.wordSetObject.flStop.set(true);
                        return;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File '"+res+"' not found");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("Url is bad '"+res);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not open '"+res);
            e.printStackTrace();
        }
    }

}
