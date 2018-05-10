/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.commands;

import de.rexlmanu.teamspeaksupport.TeamspeakSupport;
import lombok.Getter;
import lombok.Setter;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 13:21                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public class Command implements ICommand {

    @Getter
    @Setter
    private String commandLabel;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private TeamspeakSupport teamspeakSupport;

    public Command(String commandLabel, String description) {
        this.commandLabel = commandLabel;
        this.description = description;
    }

    public Command(String commandLabel) {
        this.commandLabel = commandLabel;
        this.description = "Keine Beschreibung wurde festgelegt.";
    }

    @Override
    public void perform(String[] args) {

    }
}
