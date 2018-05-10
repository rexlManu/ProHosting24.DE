/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.bootstrap;

import de.rexlmanu.teamspeaksupport.TeamspeakSupport;

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

public final class BootstrapTeamspeakSupportLaunch {

    public static void main(String[] args) {
        final TeamspeakSupport teamspeakSupport = new TeamspeakSupport();
        teamspeakSupport.onLaunch();
        Runtime.getRuntime().addShutdownHook(new Thread(teamspeakSupport::onShutdown));
    }
}
