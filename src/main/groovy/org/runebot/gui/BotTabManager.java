package org.runebot.gui;

import com.google.inject.Singleton;
import java.util.concurrent.ConcurrentHashMap;
import org.runebot.bot.Bot;

@Singleton
public class BotTabManager {
    private final ConcurrentHashMap<Bot, BotTab> tabs = new ConcurrentHashMap<Bot, BotTab>();

    public void addBotTab(BotTab tab) {
        tabs.put(tab.bot, tab);
    }

    public BotTab getBotTab(Bot bot) {
        return tabs.get(bot);
    }
}
