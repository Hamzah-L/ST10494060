package poe_part1;

import javax.swing.JOptionPane;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

public class Messages {

    private int totalMessagesSent = 0;
    private static final String NumberPattern = "^\\+27\\d{1,3}\\d{1,10}$";
    private List<String> sentMessages = new ArrayList<>();

    // Method to check if message ID is valid (not more than 10 characters)
    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10;
    }

    // Method to check if recipient cell number is valid
    public int checkRecipientCell(String recipientNumber) {
        if (recipientNumber == null || !recipientNumber.matches(NumberPattern)) {
            return -1; // Invalid format
        }
        return recipientNumber.length(); // Return length if valid
    }

    // Method to create and return message hash
    public String createMessageHash(String messageID, int messageCount, String message) {
        String str = message.trim();
        int pos1 = str.indexOf(" ");
        int pos2 = str.lastIndexOf(" ");
        String firstWord = pos1 != -1 ? str.substring(0, pos1) : str;
        String lastWord = pos2 != -1 ? str.substring(pos2 + 1) : str;

        return (messageID.substring(0, Math.min(2, messageID.length())) + ":" +
                messageCount + ":" + firstWord + lastWord).toUpperCase();
    }

    // Method to handle message sending options
    public String sendMessage(String messageID, String messageHash, String recipient, String message, JSONArray storedMessages) {
        String option = JOptionPane.showInputDialog(null,
            "Message completed. Choose an option:\n1) Send Message\n2) Disregard Message\n3) Store Message for later");

        if (option != null) {
            switch (option) {
                case "1":
                    sentMessages.add("ID: " + messageID + " | To: " + recipient + " | Message: " + message);
                    totalMessagesSent++;
                    return "Message successfully sent.";
                case "2":
                    return "Deleted successfully.";
                case "3":
                    JSONObject messageObj = new JSONObject();
                    messageObj.put("messageID", messageID);
                    messageObj.put("messageHash", messageHash);
                    messageObj.put("recipient", recipient);
                    messageObj.put("message", message);
                    storedMessages.put(messageObj);
                    return "Message successfully stored.";
                default:
                    return "Invalid option.";
            }
        }
        return "No option selected.";
    }

    // Method to return list of all sent messages
    public String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent.";
        }
        StringBuilder sb = new StringBuilder("Sent Messages:\n");
        for (String msg : sentMessages) {
            sb.append(msg).append("\n");
        }
        return sb.toString();
    }

    // Optional: Return the total number of messages sent
    public int getTotalMessagesSent() {
        return totalMessagesSent;
    }
}
