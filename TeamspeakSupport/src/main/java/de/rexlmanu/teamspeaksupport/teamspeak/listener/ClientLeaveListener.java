/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.teamspeak.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import de.rexlmanu.teamspeaksupport.TeamspeakSupport;
import de.rexlmanu.teamspeaksupport.support.SupportSession;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 13:58                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class ClientLeaveListener extends Listener{


    public ClientLeaveListener(TS3Api ts3Api, TeamspeakSupport teamspeakSupport) {
        super(ts3Api, teamspeakSupport);
    }

    @Override
    public void onClientLeave(ClientLeaveEvent e) {
        final int clientId = e.getClientId();
        if (TextMessageListener.isGetSupport(clientId)) {
            final SupportSession support = TextMessageListener.getSupport(clientId);
            getTs3Api().deleteChannel(support.getChannelId());
        }
    }
}
