/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.teamspeak.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ChannelMovedEvent;
import de.rexlmanu.teamspeaksupport.TeamspeakSupport;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 15:09                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class ChannelMovedListener extends Listener {
    public ChannelMovedListener(TS3Api ts3Api, TeamspeakSupport teamspeakSupport) {
        super(ts3Api, teamspeakSupport);
    }

    @Override
    public void onChannelMoved(ChannelMovedEvent e) {
        System.out.println(e.getChannelId());
        System.out.println(e.getChannelOrder());
        System.out.println(e.getChannelParentId());
        System.out.println(e.getInvokerId());
        System.out.println(e.getInvokerName());
        System.out.println(e.getInvokerUniqueId());
    }
}
