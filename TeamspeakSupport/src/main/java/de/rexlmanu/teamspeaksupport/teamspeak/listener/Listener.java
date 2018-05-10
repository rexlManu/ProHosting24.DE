/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.teamspeak.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.TS3Event;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import de.rexlmanu.teamspeaksupport.TeamspeakSupport;
import lombok.Getter;
import lombok.Setter;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 14:01                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public class Listener extends TS3EventAdapter implements IListener {

    @Getter
    private TS3Api ts3Api;
    @Getter
    @Setter
    private TeamspeakSupport teamspeakSupport;

    public Listener(TS3Api ts3Api, TeamspeakSupport teamspeakSupport) {
        this.ts3Api = ts3Api;
        this.teamspeakSupport = teamspeakSupport;
        register();
    }


    @Override
    public void register() {
        ts3Api.addTS3Listeners(this);
    }

    @Override
    public void unregister() {
        ts3Api.removeTS3Listeners(this);
    }

    @Override
    public void reload() {
        unregister();
        register();
    }
}
