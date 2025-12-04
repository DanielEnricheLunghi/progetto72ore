/*******************************************************
 ** MetadataUtils.java
 ** -----------------------------------------------------
 ** Classe di utilità per gestire la conversione del campo
 ** "metadata" tra JSON string ↔ Map<String,Object>.
 **
 ** Obiettivi:
 ** - Centralizzare la logica di serializzazione/deserializzazione
 ** - Evitare dipendenze dirette da Hibernate su tipi JSON
 ** - Rendere l'entità più pulita e riutilizzabile
 *******************************************************/

package com.condominio.ConvivoApp.condominio.util;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import java.util.Map;

public class MetadataUtils {

    private static final ObjectMapper mapper = new ObjectMapper(); // Singleton ObjectMapper

    //* Converte una stringa JSON in una Map
    public static Map<String, Object> parse(String json) {
        try {
            return mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            throw new IllegalStateException("Errore nel parse di metadata JSON", e);
        }
    }

    //* Converte una Map in stringa JSON
    public static String serialize(Map<String, Object> map) {
        try {
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            throw new IllegalStateException("Errore nella serializzazione di metadata JSON", e);
        }
    }
}