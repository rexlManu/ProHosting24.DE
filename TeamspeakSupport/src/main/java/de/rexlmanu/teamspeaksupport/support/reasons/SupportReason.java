/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.support.reasons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 15:54                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

@AllArgsConstructor
@Getter
public enum SupportReason {

    Allgemein("Allgemein", "Bei Allgemeinen Fragen zu unseren Produkten."),
    Buchhaltung("Buchhaltung", "Bei Buchaltung Problemen oder Fragen."),
    Domain("Domain", "Bei Domain Fragen zu unseren Produkten."),
    vServer("vServer", "Bei vServer Fragen zu unseren Produkten."),
    Teamspeak("Teamspeak", "Bei Teamspeak Fragen zu unseren Produkten."),
    Webspace("Webspace", "Bei Webspace Fragen zu unseren Produkten."),
    Partnerschaft("Partnerschaft", "Bei Partnerschaft Fragen zu unseren Programm."),
    Sonstiges("Sonstiges", "Bei Sonstigen Fragen zu unseren Produkten.");

    private String name;
    private String description;

}
