package com.mg105;

/**
 * Our 'main' class.
 */
public class Launcher {
    /**
     * The real main method.  All it does is start JavaFX on our Application class.
     * <p>
     * Note that this is separate from Application because of how JavaFX decides to do dependencies.
     * See <a href="https://github.com/nus-cs2103-AY2021S1/forum/issues/128">this GitHub issue</a>.
     *
     * @param args The commandline arguments.  They are passed to JavaFX.
     */
    public static void main(String[] args) {
        javafx.application.Application.launch(Application.class, args);
    }
}
