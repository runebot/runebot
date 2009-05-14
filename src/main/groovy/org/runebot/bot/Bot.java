package org.runebot.bot;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Enumeration;
import java.util.Iterator;
import java.applet.Applet;
import java.applet.AppletStub;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.net.URLClassLoader;
import java.net.URL;
import java.net.MalformedURLException;
import java.awt.Image;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.runebot.gui.BotTab;

public class Bot implements AppletStub {
    private static final AtomicInteger numberOfBots = new AtomicInteger(0);

    private final int botNumber = numberOfBots.incrementAndGet();
    private final String name = "Bot #" + botNumber;
    private final ThreadGroup threadGroup = new ThreadGroup(name);
    private final Logger log = LoggerFactory.getLogger(Bot.class);

    public void suspend() {
        log.info("Bot {} suspended", new String[]{this.toString()});
    }

    public String getName() {
        return name;
    }

    public Applet getApplet() throws Exception {
        log.info("Getting applet...");
        URLClassLoader loader = new URLClassLoader(new URL[]{new URL("http://world37.runescape.com/runescape.jar")});
        Class<?> clientClass = loader.loadClass("client");
        Applet applet = (Applet) clientClass.newInstance();
        applet.setStub(this);
        return applet;
    }

    public boolean isActive() {
        return false;
    }

    public URL getDocumentBase() {
        try {
            return new URL("http://world37.runescape.com/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public URL getCodeBase() {
        try {
            return new URL("http://world37.runescape.com/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getParameter(String name) {
        return BotTab.params.get(name);
    }

    public AppletContext getAppletContext() {
        return null;
    }

    public void appletResize(int width, int height) {
    }
}
