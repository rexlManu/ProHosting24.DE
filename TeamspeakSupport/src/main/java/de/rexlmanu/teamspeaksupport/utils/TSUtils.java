/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.utils;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Random;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 15:45                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class TSUtils {

    public static String generateToken() {
        char[] chars = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        final Random random = new Random();
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(chars[random.nextInt(chars.length)]);
        }
        return stringBuilder.toString();
    }

    public static Client getClientFromID(int clientId, TS3Api ts3Api) {
        for (Client client : ts3Api.getClients()) {
            if (client.getId() == clientId) {
                return client;
            }
        }
        return null;
    }

    public static boolean hasGroup(int clientId, int groupId, TS3Api ts3Api) {
        for (int i : getClientFromID(clientId, ts3Api).getServerGroups()) {
            if (groupId == i) {
                return true;
            }
        }
        return false;
    }

    public static void broadcast(String message, int groupID, TS3Api ts3Api) {
        for (Client client : ts3Api.getClients()) {
            for (int i : client.getServerGroups()) {
                if (i == groupID) {
                    ts3Api.sendPrivateMessage(client.getId(), message);
                }
            }
        }
    }
}
