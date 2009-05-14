package org.runebot.gui;

import java.applet.Applet;
import java.awt.Dimension;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JPanel;
import com.google.inject.Inject;
import org.runebot.bot.Bot
import java.applet.AppletStub
import java.applet.AppletContext
import java.applet.AudioClip
import java.awt.Image
import org.slf4j.LoggerFactory
import groovy.swing.SwingBuilder
import java.awt.BorderLayout
import org.slf4j.Logger

class BotTab extends JPanel {
    public final Bot bot
    final Logger log = LoggerFactory.getLogger(BotTab.class)
    private Applet applet

    public static Map<String, String> params = ["height": "762",
                                "width": "503",
                                "worldid": "37",
                                "members": "0",
                                "modewhat": "0",
                                "modewhere": "0",
                                "safemode": "1",
                                "lang": "0"]

    @Inject
    public BotTab(Bot bot) {
        super();
        this.bot = bot;
        setName bot.name;
        setPreferredSize(new Dimension(765, 503));
        setLayout(new BorderLayout())
      
        Thread.start {
            applet = bot.getApplet();
            new SwingBuilder().edt {
                applet.init()
                applet.start()
                applet.size = new Dimension(765, 503)
                add applet, BorderLayout.NORTH
                log.info "Loaded applet."
            }
        }
    }
}