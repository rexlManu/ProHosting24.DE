/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.handler;

import de.rexlmanu.teamspeaksupport.TeamspeakSupport;
import de.rexlmanu.teamspeaksupport.commands.Command;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 13:20                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class CommandHandler {

    @Getter
    private List<Command> commands;
    private TeamspeakSupport teamspeakSupport;

    public CommandHandler(TeamspeakSupport teamspeakSupport) {
        this.teamspeakSupport = teamspeakSupport;
        this.commands = new ArrayList<>();
    }

    public void handleInput(String[] args) {
        boolean commandFound = false;
        for (Command command : commands) {
            if (args[0].equalsIgnoreCase(command.getCommandLabel())) {
                command.perform(args);
                commandFound = true;
            }
        }
        if (!commandFound) {
            TeamspeakSupport.getLogger().info("Bitte tippe 'help' ein um alle Commands zusehen.");
        }
    }

    public void registerCommand(Command command) {
        command.setTeamspeakSupport(teamspeakSupport);
        commands.add(command);
    }
}
