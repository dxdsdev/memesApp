/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowlabs.memesapp;

import com.crowlabs.memesapp.service.MemesService;
import java.io.IOException;

/**
 *
 * @author croweloper
 */
public class Inicio {
    public static void main(String[] args) throws IOException {
        MemesService.verMemes();
    }
}
