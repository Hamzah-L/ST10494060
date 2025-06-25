package poe_part1;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessagesTest {
    private Messages messages;
    private JSONArray storedMessages;

    
    void setUp() {
        messages = new Messages();
        storedMessages = new JSONArray();
    }

    @Test
    void testCheckMessageID_Valid() {
        assertTrue(messages.checkMessageID("1234567890"));  // Exactly 10 chars
        assertTrue(messages.checkMessageID("123"));        // Less than 10 chars
    }

    @Test
    void testCheckMessageID_Invalid() {
        assertFalse(messages.checkMessageID("12345678901")); // More than 10 chars
        assertFalse(messages.checkMessageID(null));         // Null input
    }

    @Test
    void testCheckRecipientCell_Valid() {
        assertEquals(12, messages.checkRecipientCell("+27718693002")); // Valid SA number
        assertEquals(11, messages.checkRecipientCell("+27718693002"));  // Valid shorter number
    }

    @Test
    void testCheckRecipientCell_Invalid() {
        assertEquals(-1, messages.checkRecipientCell("27123456789"));  // Missing +
        assertEquals(-1, messages.checkRecipientCell("+28123456789")); // Wrong country code
        assertEquals(-1, messages.checkRecipientCell(null));           // Null input
    }

    @Test
    void testCreateMessageHash() {
        String hash = messages.createMessageHash("MSG123", 1, "Hello World");
        assertEquals("MS:1:HelloWorld", hash);

        hash = messages.createMessageHash("M", 2, "SingleWord");
        assertEquals("M:2:SingleWord", hash);

        hash = messages.createMessageHash("LONGID12345", 3, "  Trim  Spaces  ");
        assertEquals("LO:3:TrimSpaces", hash);
    }

    @Test
    void testSendMessage_SendOption() {
        String result = messages.sendMessage("ID123", "HASH123", "+27123456789", "Test message", storedMessages);
        assertEquals("Message successfully sent.", result);
        assertEquals(1, messages.getTotalMessagesSent());
        assertFalse(messages.printMessages().contains("No messages have been sent."));
    }

    @Test
    void testSendMessage_StoreOption() {
        String result = messages.sendMessage("ID123", "HASH123", "+27123456789", "Test message", storedMessages);
        assertEquals(1, storedMessages.length());
        JSONObject stored = storedMessages.getJSONObject(0);
        assertEquals("ID123", stored.getString("messageID"));
        assertEquals("Test message", stored.getString("message"));
    }

    @Test
    void testSendMessage_DisregardOption() {
        // This would normally require mocking JOptionPane, but we're testing the return value
        String result = messages.sendMessage("ID123", "HASH123", "+27123456789", "Test message", storedMessages);
        assertEquals("Deleted successfully.", result);
        assertEquals(0, messages.getTotalMessagesSent());
    }

    @Test
    void testPrintMessages_Empty() {
        assertEquals("No messages have been sent.", messages.printMessages());
    }

    @Test
    void testPrintMessages_WithContent() {
        messages.sendMessage("ID1", "HASH1", "+27123456789", "Message 1", storedMessages);
        messages.sendMessage("ID2", "HASH2", "+27123456780", "Message 2", storedMessages);
        
        String output = messages.printMessages();
        assertTrue(output.contains("ID1"));
        assertTrue(output.contains("ID2"));
        assertTrue(output.contains("Message 1"));
        assertTrue(output.contains("Message 2"));
    }

    @Test
    void testGetTotalMessagesSent() {
        assertEquals(0, messages.getTotalMessagesSent());
        messages.sendMessage("ID1", "HASH1", "+27123456789", "Message 1", storedMessages);
        assertEquals(1, messages.getTotalMessagesSent());
        messages.sendMessage("ID2", "HASH2", "+27123456780", "Message 2", storedMessages);
        assertEquals(2, messages.getTotalMessagesSent());
    }
}