package com.project;

import com.birdbraintechnologies.Finch;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Finch finch = new Finch();

        GUISystem guiSystem = GUISystem.getInstance(finch, "Finch");
        guiSystem.launch();

        finch.quit();
        System.exit(0);
    }
}
