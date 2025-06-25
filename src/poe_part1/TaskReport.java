
package poe_part1;


public class TaskReport {
    public static void displayAllSentMessages(String[] sentMessages, String[] recipients, int sentIndex) {
        System.out.println("All Sent Messages:");
        for (int i = 0; i < sentIndex; i++) {
            if (sentMessages[i] != null) {
                System.out.println("Recipient: " + recipients[i]);
                System.out.println("Message: " + sentMessages[i]);
                System.out.println();
            }
        }
    }

    public static String getLongestMessage(String[] sentMessages, int sentIndex) {
        String longest = "";
        for (int i = 0; i < sentIndex; i++) {
            if (sentMessages[i] != null && sentMessages[i].length() > longest.length()) {
                longest = sentMessages[i];
            }
        }
        return longest;
    }

    public static String searchMessageByID(String searchID, String[] messageIDs, String[] sentMessages, String[] recipients, int idIndex) {
        for (int i = 0; i < idIndex; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(searchID)) {
                return "Recipient: " + recipients[i] + "\nMessage: " + sentMessages[i];
            }
        }
        return "Message ID not found.";
    }

    public static String[] searchMessagesByRecipient(String recipientSearch, String[] sentMessages, String[] recipients, int recipientIndex) {
        String[] found = new String[recipientIndex];
        int count = 0;
        for (int i = 0; i < recipientIndex; i++) {
            if (recipients[i] != null && recipients[i].equals(recipientSearch)) {
                found[count++] = sentMessages[i];
            }
        }
        return found;
    }

    public static boolean deleteMessageByHash(String hashToDelete, String[] messageHashes, String[] sentMessages, String[] recipients, String[] messageIDs, int hashIndex) {
        for (int i = 0; i < hashIndex; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hashToDelete)) {
                messageHashes[i] = null;
                sentMessages[i] = null;
                recipients[i] = null;
                messageIDs[i] = null;
                return true;
            }
        }
        return false;
    }

    public static String buildMessageReport(String[] messageIDs, String[] messageHashes, String[] recipients, String[] sentMessages, int sentIndex) {
        StringBuilder report = new StringBuilder("Sent Message Report:\n");
        for (int i = 0; i < sentIndex; i++) {
            if (sentMessages[i] != null) {
                report.append("Message ID: ").append(messageIDs[i])
                        .append("\nHash: ").append(messageHashes[i])
                        .append("\nRecipient: ").append(recipients[i])
                        .append("\nMessage: ").append(sentMessages[i])
                        .append("\n---------------------\n");
            }
        }
        return report.toString();
    }
}
