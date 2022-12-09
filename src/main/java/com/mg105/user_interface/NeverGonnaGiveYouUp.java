package com.mg105.user_interface;

import com.mg105.interface_adapters.Web;

import java.awt.*;
import java.net.URI;

/**
 * This Java class have the method that pop up a browser and going to the YouTube URL that play Never Gonna Give You Up song by Rick Astley.
 */
public class NeverGonnaGiveYouUp implements Web {


    /**
     * method for pop up a browser and going to the YouTube URL that play Never Gonna Give You Up song by Rick Astley.
     * @throws Exception Exception
     */
    @Override
    public void neverGonnaGiveYouUp() throws Exception {
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
    }


}
