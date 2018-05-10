/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.teamspeak;

import com.github.theholywaffle.teamspeak3.TS3Api;
import de.rexlmanu.teamspeaksupport.support.reasons.SupportReason;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 15:56                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class TeamspeakManager {

    public static void sendReasons(TS3Api ts3Api, int client) {
        int i = 0;
        for (SupportReason supportReason : SupportReason.values()) {
            ts3Api.sendPrivateMessage(client, "ID: " + i + " - " + supportReason.getName() + " - " + supportReason.getDescription());
            i++;
        }
    }
}
