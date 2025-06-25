package poe_part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskReportTest {

    String[] sentMessages;
    String[] messageIDs;
    String[] recipients;
    String[] messageHashes;
    int sentIndex;

    @BeforeEach
    public void setUp() {
        sentMessages = new String[10];
        messageIDs = new String[10];
        recipients = new String[10];
        messageHashes = new String[10];
        sentIndex = 3;

        // Simulate data
        sentMessages[0] = "Did you get the cake?";
        recipients[0] = "+27834557896";
        messageIDs[0] = "0000000001";
        messageHashes[0] = "00:1:DIDCAKE";

        sentMessages[1] = "Where are you? You are late! I have asked you to be on time.";
        recipients[1] = "+27838884567";
        messageIDs[1] = "0000000002";
        messageHashes[1] = "00:2:WHERETIME";

        sentMessages[2] = "It is dinner time !";
        recipients[2] = "0838884567";
        messageIDs[2] = "0000000003";
        messageHashes[2] = "00:3:ITTIME";
    }

    @Test
    public void testSentMessagesPopulated() {
        assertEquals("Did you get the cake?", sentMessages[0]);
        assertEquals("It is dinner time !", sentMessages[2]);
        assertEquals(3, sentIndex);
    }

    @Test
    public void testLongestMessage() {
        String longest = "";
        for (int i = 0; i < sentIndex; i++) {
            if (sentMessages[i] != null && sentMessages[i].length() > longest.length()) {
                longest = sentMessages[i];
            }
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchByMessageID() {
        String searchID = "0000000003";
        String found = null;
        for (int i = 0; i < sentIndex; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(searchID)) {
                found = sentMessages[i];
                break;
            }
        }
        assertEquals("It is dinner time !", found);
    }

    @Test
    public void testSearchByRecipient() {
        String searchRecipient = "+27838884567";
        String[] found = new String[10];
        int count = 0;
        for (int i = 0; i < sentIndex; i++) {
            if (recipients[i] != null && recipients[i].equals(searchRecipient)) {
                found[count++] = sentMessages[i];
            }
        }
        assertEquals(1, count);
        assertEquals("Where are you? You are late! I have asked you to be on time.", found[0]);
    }

    @Test
    public void testDeleteByMessageHash() {
        String hashToDelete = "00:2:WHERETIME";
        boolean deleted = false;
        for (int i = 0; i < sentIndex; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hashToDelete)) {
                sentMessages[i] = null;
                recipients[i] = null;
                messageHashes[i] = null;
                messageIDs[i] = null;
                deleted = true;
                break;
            }
        }
        assertTrue(deleted);
        assertNull(sentMessages[1]);
    }

    @Test
    public void testMessageReportIncludesHashAndRecipient() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < sentIndex; i++) {
            if (sentMessages[i] != null) {
                report.append("Message ID: ").append(messageIDs[i])
                        .append(" Hash: ").append(messageHashes[i])
                        .append(" To: ").append(recipients[i])
                        .append(" Msg: ").append(sentMessages[i])
                        .append("\n");
            }
        }

        String output = report.toString();
        assertTrue(output.contains("00:1:DIDCAKE"));
        assertTrue(output.contains("To: +27834557896"));
        assertTrue(output.contains("Msg: Did you get the cake?"));
    }
}
