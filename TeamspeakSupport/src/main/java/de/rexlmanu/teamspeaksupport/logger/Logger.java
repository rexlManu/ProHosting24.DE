/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.logger;


import lombok.Getter;
import lombok.Setter;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 13:07                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class Logger {

    @Getter
    @Setter
    private LogLevel logLevel;

    public Logger(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void log(String message, LogLevel logLevel) {
        if (logLevel.getPerioty() <= this.logLevel.getPerioty())
            System.out.println("[" + logLevel.getInput() + "] " + message);
    }

    public void log(String message) {
        this.log(message, LogLevel.INFO);
    }

    public void debug(String message) {
        this.log(message, LogLevel.DEBUG);
    }

    public void info(String message) {
        this.log(message, LogLevel.INFO);
    }

    public void warning(String message) {
        this.log(message, LogLevel.WARNING);
    }

    public void error(String message) {
        this.log(message, LogLevel.ERROR);
    }
}
