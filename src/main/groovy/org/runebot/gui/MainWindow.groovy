package org.runebot.gui

import com.google.inject.Guice
import com.google.inject.Inject
import com.jidesoft.swing.JideTabbedPane
import org.runebot.bot.Bot
import org.runebot.gui.BotTabManager
import griffon.builder.jide.JideBuilder
import java.awt.BorderLayout
import java.awt.event.ContainerEvent
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JTabbedPane
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MainWindow {
    final Logger log = LoggerFactory.getLogger(MainWindow.class)

    @Inject BotTabManager botTabManager

    JideTabbedPane tabPane
    def tabs = [:]

    void createAndShow() {
        new JideBuilder().edt {
            frame(
                    title: 'ByBot',
                    show: true,
                    pack: true,
                    defaultCloseOperation: JFrame.EXIT_ON_CLOSE,
                    resizable: false) {

                borderLayout()

                menuBar {
                    menu(text: "File", mnemonic: 'F') {
                        menuItem(text: "New Bot", mnemonic: 'N', actionPerformed: {
                            doOutside {
                                def botTab
                                edt {
                                    tabPane.add(botTab = Guice.createInjector().getInstance(BotTab.class))
                                }
                                botTabManager.addBotTab(botTab)
                            }
                        })
                        menuItem(text: "Exit", mnemonic: 'X', actionPerformed: {
                            dispose()
                        })
                    }
                }

                tabPane = jideTabbedPane(
                        tabColorProvider: JideTabbedPane.ONENOTE_COLOR_PROVIDER,
                        tabShape: JideTabbedPane.SHAPE_ECLIPSE3X,
                        tabPlacement: JTabbedPane.TOP,
                        showCloseButton: true,
                        showCloseButtonOnTab: true,
                        constraints: BorderLayout.NORTH,
                        componentRemoved: {ContainerEvent ci ->
                            if (ci.getChild() instanceof JPanel) {
                                def bot = tabs[ci.getChild()]
                                if (bot)
                                    bot.suspend()
                            }
                        }) {

                    panel(name: 'Home', preferredSize: [765, 503]) {
                        label("$botTabManager")
                    }
                }
                label(preferredSize: [765, 16], constraints: BorderLayout.SOUTH, text: "Status")
            }
        }
    }
}
