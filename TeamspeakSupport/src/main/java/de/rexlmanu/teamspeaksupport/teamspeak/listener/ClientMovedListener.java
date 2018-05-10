/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.teamspeak.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.rexlmanu.teamspeaksupport.TeamspeakSupport;
import de.rexlmanu.teamspeaksupport.support.SupportSession;
import de.rexlmanu.teamspeaksupport.teamspeak.TeamspeakManager;
import de.rexlmanu.teamspeaksupport.utils.TSUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public final class ClientMovedListener extends Listener {


    public ClientMovedListener(TS3Api ts3Api, TeamspeakSupport teamspeakSupport) {
        super(ts3Api, teamspeakSupport);
    }

    @Override
    public void onClientMoved(ClientMovedEvent e) {
        final int targetChannelId = e.getTargetChannelId();
        final int clientId = e.getClientId();

        if (TextMessageListener.isGetSupport(clientId)) {
            final SupportSession support = TextMessageListener.getSupport(clientId);
            getTs3Api().moveClient(clientId, support.getChannelId());
            getTs3Api().sendPrivateMessage(clientId, "Bitte verlasse den Support mit !abbrechen.");
        } else if (getTeamspeakSupport().getData().getChannelId() == targetChannelId) {

            final String hh = new SimpleDateFormat("HH").format(new Date(System.currentTimeMillis()));
            final Integer hour = Integer.valueOf(hh);

            if (hour <= 12) {
                getTs3Api().sendPrivateMessage(clientId, "Der Support ist nur zwischen 12 Uhr und 24 Uhr geöffnet.");
                getTs3Api().kickClientFromChannel(clientId);
                return;
            }
            final List<Client> supporters = getSupporters();

            if (supporters.isEmpty()) {
                getTs3Api().sendPrivateMessage(clientId, "Es sind zurzeit keine Supporter online.");
                getTs3Api().kickClientFromChannel(clientId);
                return;
            }

            if (TSUtils.hasGroup(clientId, getTeamspeakSupport().getData().getGroupId(), getTs3Api())) {
                getTs3Api().sendPrivateMessage(clientId, "Bitte gehe als Supporter nicht in den Supportwarteraum.");
                getTs3Api().kickClientFromChannel(clientId);
                return;
            }

            TeamspeakManager.sendReasons(getTs3Api(), clientId);
            getTs3Api().sendPrivateMessage(clientId, "Bitte tippe die ID mit deinem Verlangen in den Chat.");

        }

    }

    private List<Client> getSupporters() {
        List<Client> clients = new ArrayList<>();
        for (Client client : getTs3Api().getClients()) {
            for (int i : client.getServerGroups()) {
                if (i == getTeamspeakSupport().getData().getGroupId()) {
                    clients.add(client);
                }
            }
        }
        return clients;
    }
}
