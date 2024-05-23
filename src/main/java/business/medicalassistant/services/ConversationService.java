package business.medicalassistant.services;

import java.io.IOException;
import java.util.Scanner;

public class ConversationService {

    private final LlamaService llamaService;
    private final Scanner scanner;

    public ConversationService() {
        this.llamaService = new LlamaService();
        this.scanner = new Scanner(System.in);
    }

    public void startConversation(String model) {
        while (true) {
            System.out.print("Utilizator: ");
            String userMessage = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userMessage)) {
                break;
            }

            try {
                String response = llamaService.sendMessage(model, userMessage);
                System.out.println("Asistent: " + response);

                // Permitem modelului să ceară detaliile pentru programare dacă detectează intenția
                if (response.toLowerCase().contains("care este numele tău") || response.toLowerCase().contains("la ce oră dorești să programezi întâlnirea")) {
                    handleAppointment(response);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleAppointment(String assistantMessage) {
        if (assistantMessage.toLowerCase().contains("care este numele tău")) {
            System.out.print("Asistent: Care este numele tău? ");
            String name = scanner.nextLine();
            try {
                String response = llamaService.sendMessage("llama3", name);
                System.out.println("Asistent: " + response);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (assistantMessage.toLowerCase().contains("la ce oră dorești să programezi întâlnirea")) {
            System.out.print("Asistent: La ce oră dorești să programezi întâlnirea? ");
            String time = scanner.nextLine();
            try {
                String response = llamaService.sendMessage("llama3", time);
                System.out.println("Asistent: " + response);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ConversationService service = new ConversationService();
        service.startConversation("llama3");
    }
}
