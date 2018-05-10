/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport;

import com.github.theholywaffle.teamspeak3.TS3Api;
import de.rexlmanu.teamspeaksupport.commands.HelpCommand;
import de.rexlmanu.teamspeaksupport.commands.StopCommand;
import de.rexlmanu.teamspeaksupport.handler.CommandHandler;
import de.rexlmanu.teamspeaksupport.handler.ConfigHandler;
import de.rexlmanu.teamspeaksupport.logger.LogLevel;
import de.rexlmanu.teamspeaksupport.logger.Logger;
import de.rexlmanu.teamspeaksupport.teamspeak.Data;
import de.rexlmanu.teamspeaksupport.teamspeak.TeamspeakClient;
import de.rexlmanu.teamspeaksupport.teamspeak.listener.ChannelMovedListener;
import de.rexlmanu.teamspeaksupport.teamspeak.listener.ClientLeaveListener;
import de.rexlmanu.teamspeaksupport.teamspeak.listener.ClientMovedListener;
import de.rexlmanu.teamspeaksupport.teamspeak.listener.TextMessageListener;
import lombok.Getter;

import java.io.*;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 13:05                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class TeamspeakSupport {

    @Getter
    private static Logger logger;
    @Getter
    private CommandHandler commandHandler;
    @Getter
    private ConfigHandler configHandler;
    @Getter
    private TeamspeakClient teamspeakClient;
    @Getter
    private Data data;

    public TeamspeakSupport() {
        logger = new Logger(LogLevel.DEBUG);
    }

    public void onLaunch() {
        printHeader();

        this.commandHandler = new CommandHandler(this);
        this.configHandler = new ConfigHandler(new File("./tssupport/"));
        new Thread(this::registerCommandHook).start();
        registerCommands();

        this.teamspeakClient = new TeamspeakClient();
        this.teamspeakClient.configureConfig(configHandler.getJsonHandler().getNumber("teamspeakPort").intValue(), configHandler.getJsonHandler().getString("teamspeakIP"));
        this.teamspeakClient.connect(configHandler.getJsonHandler().getString("Loginname"), configHandler.getJsonHandler().getString("Passwort"), configHandler.getJsonHandler().getNumber("teamspeakID").intValue());
        registerListeners();

        this.data = new Data();
        this.data.setChannelId(this.configHandler.getJsonHandler().getNumber("SupportchannelID").intValue());
        this.data.setGroupId(this.configHandler.getJsonHandler().getNumber("SupportgroupID").intValue());
    }

    private void registerListeners() {
        final TS3Api ts3Api = this.teamspeakClient.getTs3Api();
        ts3Api.registerAllEvents();
        new ClientLeaveListener(ts3Api, this);
        new ClientMovedListener(ts3Api, this);
        new TextMessageListener(ts3Api, this);
        new ChannelMovedListener(ts3Api, this);
    }

    private void registerCommandHook() {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("")) continue;
                final String[] args = line.split(" ");
                this.commandHandler.handleInput(args);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerCommands() {
        this.commandHandler.registerCommand(new StopCommand());
        this.commandHandler.registerCommand(new HelpCommand());
    }

    public void onShutdown() {
        this.teamspeakClient.disconnect();
        logger.info("Closing TSSupport Bot...");
        logger.info("Have a nice day :)");
    }

    private void printHeader() {
        System.out.println("\n" +
                "  _______    _____    _____                                          _   \n" +
                " |__   __|  / ____|  / ____|                                        | |  \n" +
                "    | |    | (___   | (___    _   _   _ __    _ __     ___    _ __  | |_ \n" +
                "    | |     \\___ \\   \\___ \\  | | | | | '_ \\  | '_ \\   / _ \\  | '__| | __|\n" +
                "    | |     ____) |  ____) | | |_| | | |_) | | |_) | | (_) | | |    | |_ \n" +
                "    |_|    |_____/  |_____/   \\__,_| | .__/  | .__/   \\___/  |_|     \\__|\n" +
                "                                     | |     | |                         \n" +
                "                                     |_|     |_|                         " +
                "\nDeveloped & maintenanced by rexlManu - https://rexlManu.de");
        sleep(200);

    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
