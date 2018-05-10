/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.handler;

import com.google.gson.JsonPrimitive;
import de.rexlmanu.teamspeaksupport.TeamspeakSupport;
import de.rexlmanu.teamspeaksupport.config.JsonConfiguration;
import lombok.Getter;

import java.io.File;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 13:49                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class ConfigHandler {

    @Getter
    private JsonConfiguration jsonConfiguration;
    @Getter
    private JsonHandler jsonHandler;

    public ConfigHandler(File directory) {
        directory.mkdir();
        this.jsonConfiguration = new JsonConfiguration(directory, "config");
        createDefault();
        this.jsonConfiguration.loadConfig();
        this.jsonHandler = new JsonHandler(this.jsonConfiguration.getJsonObject());
    }

    public void load() {
        this.jsonConfiguration.loadConfig();
        this.jsonHandler = new JsonHandler(this.jsonConfiguration.getJsonObject());
    }

    private void createDefault() {
        if (!this.jsonConfiguration.fileExist()) {
            TeamspeakSupport.getLogger().info("Configuration dont exist, creating new...");
            this.jsonHandler = new JsonHandler(this.jsonConfiguration.getJsonObject());
            this.jsonHandler.setString("teamspeakIP", "localhost");
            this.jsonHandler.setNumber("teamspeakPort", 9987);
            this.jsonHandler.setNumber("teamspeakID", 1);
            this.jsonHandler.setString("Loginname", "serveradmin");
            this.jsonHandler.setString("Passwort", "password");
            this.jsonHandler.setNumber("SupportgroupID", 0);
            this.jsonHandler.setNumber("SupportchannelID", 0);
            this.jsonHandler.setNumber("RightgroupID", 0);
            this.jsonHandler.setNumber("ChannelLimit", 5);
            this.jsonConfiguration.saveConfig();
        }
    }
}
