/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.commands;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 13:37                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class HelpCommand extends Command {

    public HelpCommand() {
        super("help", "Eine Übersicht mit allen Commands.");
    }

    @Override
    public void perform(String[] args) {
        for (Command command : getTeamspeakSupport().getCommandHandler().getCommands()) {
            System.out.println(command.getCommandLabel() + " - " + command.getDescription());
        }
    }
}
