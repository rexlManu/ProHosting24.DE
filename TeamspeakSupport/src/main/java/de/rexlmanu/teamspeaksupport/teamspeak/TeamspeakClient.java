/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.teamspeak;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.reconnect.ConnectionHandler;
import com.github.theholywaffle.teamspeak3.api.reconnect.ReconnectStrategy;
import de.rexlmanu.teamspeaksupport.TeamspeakSupport;
import de.rexlmanu.teamspeaksupport.handler.ConfigHandler;
import de.rexlmanu.teamspeaksupport.handler.JsonHandler;
import lombok.Getter;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 14:09                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class TeamspeakClient implements ConnectionHandler {

    @Getter
    private TS3Api ts3Api;
    @Getter
    private TS3Query ts3Query;
    @Getter
    private TS3Config ts3Config;

    public TeamspeakClient() {
    }

    public void configureConfig(int port, String ip) {
        this.ts3Config = new TS3Config();
        ts3Config.setEnableCommunicationsLogging(true);
//        ts3Config.setQueryPort(port);
        ts3Config.setFloodRate(TS3Query.FloodRate.UNLIMITED);
        ts3Config.setReconnectStrategy(ReconnectStrategy.disconnect());
        ts3Config.setHost(ip);
        ts3Config.setCommandTimeout(10);
        ts3Config.setConnectionHandler(this);
    }

    public void connect(String login, String pw, int serverID) {
        this.ts3Query = new TS3Query(ts3Config);
        this.ts3Query.connect();
        this.ts3Api = this.ts3Query.getApi();
        this.ts3Api.login(login, pw);
        ts3Api.selectVirtualServerById(serverID);
        this.ts3Api.setNickname("TSSupport Bot");
        ts3Api.sendChannelMessage("TSSupport Bot connected. Developed & Maintenanced by rexlManu.");
    }

    public void disconnect() {
        this.ts3Query.exit();
    }

    @Override
    public void onConnect(TS3Query ts3Query) {
        TeamspeakSupport.getLogger().info("Teamspeaksupport connected.");
    }

    @Override
    public void onDisconnect(TS3Query ts3Query) {
        TeamspeakSupport.getLogger().info("Teamspeaksupport disconnected.");
    }
}
