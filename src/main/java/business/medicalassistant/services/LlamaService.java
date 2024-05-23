package business.medicalassistant.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LlamaService {

    private static final String API_URL = "http://localhost:11434/api/chat";
    private static final Gson gson = new Gson();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private JsonArray messagesArray = new JsonArray();

    public LlamaService() {
        // Adăugăm mesajul de sistem o singură dată la inițializare
        JsonObject systemMessageObject = new JsonObject();
        systemMessageObject.addProperty("role", "system");
        systemMessageObject.addProperty("content", "Ești un asistent medical. Ajută utilizatorii cu informații medicale și programări. Dacă utilizatorul dorește să facă o programare, cere detalii precum numele și ora programării.");
        messagesArray.add(systemMessageObject);
    }

    public String sendMessage(String model, String userMessage) throws IOException, InterruptedException {
        JsonObject messageObject = new JsonObject();
        messageObject.addProperty("model", model);
        messageObject.addProperty("stream", false);

        // Adăugăm mesajul utilizatorului la array-ul de mesaje
        JsonObject userMessageObject = new JsonObject();
        userMessageObject.addProperty("role", "user");
        userMessageObject.addProperty("content", userMessage);
        messagesArray.add(userMessageObject);

        messageObject.add("messages", messagesArray);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(messageObject)))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject responseObject = gson.fromJson(response.body(), JsonObject.class);

        // Extragem răspunsul asistentului și îl adăugăm la array-ul de mesaje
        String assistantMessage = responseObject.getAsJsonObject("message").get("content").getAsString();
        JsonObject assistantMessageObject = new JsonObject();
        assistantMessageObject.addProperty("role", "assistant");
        assistantMessageObject.addProperty("content", assistantMessage);
        messagesArray.add(assistantMessageObject);

        return assistantMessage;
    }
}
