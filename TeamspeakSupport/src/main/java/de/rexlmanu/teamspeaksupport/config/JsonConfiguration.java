/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.rexlmanu.teamspeaksupport.handler.JsonHandler;
import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 13:40                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class JsonConfiguration implements IConfig {

    @Getter
    private JsonObject jsonObject;
    private File source;

    public JsonConfiguration(File directory, String name) {
        this.source = new File(directory, name + ".json");
        this.jsonObject = new JsonObject();
    }

    public boolean fileExist() {
        return source.exists();
    }

    @Override
    public void loadConfig() {
        try {
            this.jsonObject = new JsonParser().parse(new String(Files.readAllBytes(source.toPath()))).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveConfig() {
        try {
            final FileWriter fileWriter = new FileWriter(source);
            fileWriter.write(convertToString(jsonObject));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertToString(JsonObject jsonObject) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject);
    }

    @Override
    public void reloadConfig() {
        loadConfig();
    }
}
