/*
 * © Copyright - Emmanuel Lampe aka. rexlManu 2018.
 */
package de.rexlmanu.teamspeaksupport.handler;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.Getter;

/******************************************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Emmanuel Lampe 2018                                       
 *    Erstellt: 10.05.2018 / 13:45                           
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Emmanuel Lampe. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Emmanuel Lampe.  
 ******************************************************************************************/

public final class JsonHandler {

    @Getter
    private JsonObject jsonObject;

    public JsonHandler(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonElement getJsonElement(String key) {
        return this.jsonObject.get(key);
    }

    public void addJsonElement(String key, JsonElement jsonElement) {
        this.jsonObject.add(key, jsonElement);
    }

    public String getString(String key) {
        return this.jsonObject.get(key).getAsString();
    }

    public Number getNumber(String key) {
        return this.getJsonElement(key).getAsNumber();
    }

    public Boolean getBoolean(String key) {
        return this.getJsonElement(key).getAsBoolean();
    }

    public Character getChar(String key) {
        return this.getJsonElement(key).getAsCharacter();
    }

    public void setString(String key, String value) {
        this.addJsonElement(key, new JsonPrimitive(value));
    }

    public void setBoolean(String key, Boolean value) {
        this.addJsonElement(key, new JsonPrimitive(value));
    }

    public void setNumber(String key, Number value) {
        this.addJsonElement(key, new JsonPrimitive(value));
    }

    public void setChar(String key, Character value) {
        this.addJsonElement(key, new JsonPrimitive(value));
    }

}
