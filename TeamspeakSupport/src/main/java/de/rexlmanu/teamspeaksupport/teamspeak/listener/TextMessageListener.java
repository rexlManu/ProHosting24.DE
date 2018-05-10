/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.teamspeak.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.rexlmanu.teamspeaksupport.TeamspeakSupport;
import de.rexlmanu.teamspeaksupport.support.SupportSession;
import de.rexlmanu.teamspeaksupport.support.SupportStatus;
import de.rexlmanu.teamspeaksupport.support.reasons.SupportReason;
import de.rexlmanu.teamspeaksupport.utils.TSUtils;

import java.util.ArrayList;
import java.util.HashMap;
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

public final class TextMessageListener extends Listener {

    public static List<SupportSession> supportSessions = new ArrayList<>();

    public TextMessageListener(TS3Api ts3Api, TeamspeakSupport teamspeakSupport) {
        super(ts3Api, teamspeakSupport);
    }

    @Override
    public void onTextMessage(TextMessageEvent e) {
        final int invokerId = e.getInvokerId();
        if (invokerId == getTs3Api().whoAmI().getId()) return;
        final String message = e.getMessage();

        if (message.equals("!abbrechen")) {
            if (TSUtils.hasGroup(invokerId, getTeamspeakSupport().getData().getGroupId(), getTs3Api())) {
                if (isGetSupport(invokerId)) {

                    SupportSession ss = null;

                    for (SupportSession supportSession : supportSessions) {
                        ss = supportSession;
                        final Client cc = TSUtils.getClientFromID(supportSession.getClientId(), getTs3Api());
                        getTs3Api().sendPrivateMessage(cc.getId(), "Du wurdest erfolgreich supportet.");
                        getTs3Api().sendPrivateMessage(invokerId, "Du hast erfolgreich den Support beendet.");
                        getTs3Api().deleteChannel(supportSession.getChannelId());
                    }

                    supportSessions.remove(ss);

                } else {
                    getTs3Api().sendPrivateMessage(invokerId, "Du supportest niemanden.");
                }
            } else {
                if (isGetSupport(invokerId)) {
                    final SupportSession support = getSupport(invokerId);
                    getTs3Api().deleteChannel(support.getChannelId());
                    supportSessions.remove(support);
                    getTs3Api().sendPrivateMessage(invokerId, "Du hast den Support abgebrochen.");
                    TSUtils.broadcast("Der User " + TSUtils.getClientFromID(invokerId, getTs3Api()).getNickname() + " hat denn Support abgebrochen.", getTeamspeakSupport().getData().getGroupId(), getTs3Api());
                } else {
                    getTs3Api().sendPrivateMessage(invokerId, "Du bekommst keinen Support zurzeit.");
                }
            }
        } else if (TSUtils.hasGroup(invokerId, getTeamspeakSupport().getData().getGroupId(), getTs3Api())) {
            final String[] args = message.split(" ");
            if (message.startsWith("!support")) {
                final String id = message.replace("!support ", "");
                try {
                    final int clientID = Integer.parseInt(id);
                    final SupportSession support = getSupport(clientID);
                    switch (support.getSupportStatus()) {
                        case END:
                            getTs3Api().sendPrivateMessage(invokerId, "Dieser Support ist bereits zuende.");
                            break;
                        case WAITING:
                            support.setSupporterId(invokerId);
                            getTs3Api().moveClient(invokerId, support.getChannelId());
                            getTs3Api().editChannel(support.getChannelId(), ChannelProperty.CHANNEL_NEEDED_TALK_POWER, "0");
                            getTs3Api().sendPrivateMessage(invokerId, "Du supportest jetzt " + TSUtils.getClientFromID(clientID, getTs3Api()).getNickname() + " mit dem Grund: " + support.getSupportReason().getName());
                            getTs3Api().sendPrivateMessage(clientID, "Du wirst jetzt supportet von " + TSUtils.getClientFromID(invokerId, getTs3Api()).getNickname() + ".");
                            break;
                        case SUPPORTING:
                            getTs3Api().sendPrivateMessage(invokerId, "Dieser Support wird bereits Supportet.");
                            break;
                    }

                } catch (Exception x) {
                    getTs3Api().sendPrivateMessage(invokerId, "Es konnte kein Support mit dieser ID gefunden werden.");
                }
            } else if (message.equalsIgnoreCase("!togglenotify")) {

            } else {
                getTs3Api().sendPrivateMessage(invokerId, "Commandübersicht:");
                getTs3Api().sendPrivateMessage(invokerId, "!support <ID>");

            }
        } else if (TSUtils.getClientFromID(invokerId, getTs3Api()).getChannelId() == getTeamspeakSupport().getData().getChannelId()) {


            if (isGetSupport(invokerId)) {
                getTs3Api().sendPrivateMessage(invokerId, "Du bekommst bereits Support.");
            } else {

                try {
                    int id = Integer.parseInt(message);
                    final SupportReason supportReason = getSupportReason(id);
                    if (supportReason == null) {
                        getTs3Api().sendPrivateMessage(invokerId, "Es konnte kein Grund mit dieser ID gefunden werden.");
                        return;
                    }

                    getTs3Api().sendPrivateMessage(invokerId, "Es wurden alle Supporter benachrichtigt.");

                    TSUtils.broadcast("----------------------------------------", getTeamspeakSupport().getData().getGroupId(), getTs3Api());
                    TSUtils.broadcast("Neue Supportanfrage", getTeamspeakSupport().getData().getGroupId(), getTs3Api());
                    TSUtils.broadcast("» User ● " + TSUtils.getClientFromID(invokerId, getTs3Api()).getNickname(), getTeamspeakSupport().getData().getGroupId(), getTs3Api());
                    TSUtils.broadcast("» Grund ● " + supportReason.getName(), getTeamspeakSupport().getData().getGroupId(), getTs3Api());
                    TSUtils.broadcast("Zum Supporten » !support " + invokerId, getTeamspeakSupport().getData().getGroupId(), getTs3Api());
                    TSUtils.broadcast("----------------------------------------", getTeamspeakSupport().getData().getGroupId(), getTs3Api());

                    final HashMap<ChannelProperty, String> map = new HashMap<>();

                    map.put(ChannelProperty.CHANNEL_FLAG_PERMANENT, "1");
                    map.put(ChannelProperty.CHANNEL_ORDER, String.valueOf(getTeamspeakSupport().getData().getChannelId()));
                    map.put(ChannelProperty.CHANNEL_MAXCLIENTS, "0");
                    map.put(ChannelProperty.CHANNEL_NEEDED_TALK_POWER, "9999");
                    map.put(ChannelProperty.CHANNEL_DESCRIPTION, "Supportchannel");

                    final int channel = getTs3Api().createChannel("Support - #" + TSUtils.generateToken(), map);
                    getTs3Api().moveClient(invokerId, channel);
                    final SupportSession supportSession = new SupportSession(invokerId, channel, -1, supportReason, SupportStatus.WAITING);
                    supportSessions.add(supportSession);
                } catch (Exception x) {
                    getTs3Api().sendPrivateMessage(invokerId, "Bitte verwende als ID nur Zahlen.");
                    return;
                }


            }

        } else {
            getTs3Api().sendPrivateMessage(invokerId, "Für Support gehe in den Supportwarteraum.");
        }
    }

    public static SupportReason getSupportReason(int id) {
        int i = 0;
        for (SupportReason supportReason : SupportReason.values()) {
            if (id == i) {
                return supportReason;
            }
            i++;
        }
        return null;
    }

    public static boolean isGetSupport(int clientId) {
        for (SupportSession supportSession : supportSessions) {
            if (supportSession.getClientId() == clientId) {
                return true;
            }
            if (supportSession.getSupporterId() == clientId) {
                return true;
            }
        }
        return false;
    }

    public static SupportSession getSupport(int clientId) {
        if (!isGetSupport(clientId)) return null;

        for (SupportSession supportSession : supportSessions) {
            if (supportSession.getClientId() == clientId) {
                return supportSession;
            }
        }
        return null;
    }
}
