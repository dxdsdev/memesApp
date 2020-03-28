/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowlabs.memesapp.service;

import com.crowlabs.memesapp.model.Meme;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author croweloper
 */
public class MemesService {

    public static void verMemes() throws IOException {
        ArrayList<String> options = new ArrayList<>();
        options.add(" 1. ver otro meme");
        options.add(" 2. abrir meme en chrome");
        options.add(" 3. favoritos");
        options.add(" 4. volver al menu");
        int op = -1;
        do {
            Meme meme = getMemeFromAPI();
            Object input = JOptionPane.showInputDialog(null, "Opciones: ", meme.getTitle() + " subreddit: " + meme.getSubreddit(), JOptionPane.INFORMATION_MESSAGE, getImageFromURL(meme.getUrl()), options.toArray(), options.get(0));
            op = options.indexOf(input);
            //abre el meme en el navegador chrome
            if (op == 1) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start chrome " + meme.getPostLink()});
            }
        } while (op != 3);
    }

    private static Icon getImageFromURL(String url) throws IOException, MalformedURLException {
        return new ImageIcon(ImageIO.read(new URL(url)).getScaledInstance(800, 800, BufferedImage.SCALE_SMOOTH));
    }

    private static Meme getMemeFromAPI() {
        Unirest.setTimeouts(0, 0);
        Meme meme = new Meme();
        try {
            HttpResponse<String> response = Unirest.get("https://meme-api.herokuapp.com/gimme")
                    .asString();
            meme = new Gson().fromJson(response.getBody(), Meme.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return meme;
    }
}
