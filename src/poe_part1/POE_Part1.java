package poe_part1;

import javax.swing.JOptionPane;
import org.json.JSONObject;
import org.json.JSONArray;

public class POE_Part1 {

    public static void main(String[] args) {
        {
//Register process
            //setting parameters for the username. Must contain a underscore and must be no more then five characters
            String username = "";
            String usernamePattern = "^(?=.{1,5}$)[A-Za-z0-9_]*_[A-Za-z0-9_]*$";

            while (!username.matches(usernamePattern)) {
                username = JOptionPane.showInputDialog("Please enter a username. Must contain a underscore and must be no more then five characters");
                if (username.matches(usernamePattern)) {
                    JOptionPane.showMessageDialog(null, "Username successfully captured");
                } else {
                    JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more then five characters in lenght");
                }
            }
            //prompt user for a password
            String password = "";

            //setting parameters for the Password. Must be at least be eight characters long, contain a capital letter, contain a number,contain a special characters
            String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!()_{}\\[\\]:;\"'<>,.?/~`|\\\\-]).{8,}$";

            while (!password.matches(passwordPattern)) {
                password = JOptionPane.showInputDialog("Please enter a password. Must be at least be eight characters long, contain a capital letter, contain a number,contain a special characters");
                if (password.matches(passwordPattern)) {
                    JOptionPane.showMessageDialog(null, "Password successfully captured");
                } else {
                    JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the passwordcontains at least eight characters, a capital letter, a number, and a special character");
                }
            }
//Prompting user for a phone number. 

            String number = "";

            //setting parameters for the phone number. Must contain the international country code followed by the number, which is no more than ten characters long
            String numberPattern = "^\\+27\\d{1,3}\\d{1,10}$";

            while (!number.matches(numberPattern)) {
                number = JOptionPane.showInputDialog("Please enter a cellphone number. Must contain the South African country code(+27) followed by the number, which is no more than ten characters long");
                if (number.matches(numberPattern)) {
                    JOptionPane.showMessageDialog(null, "Cellphone number successfully added");
                } else {
                    JOptionPane.showMessageDialog(null, "Cellphone number incorrectly formatted or does not contain international code");
                }

            }

            // Login attempt (with 3 tries allowed)
            int Attempts = 0;
            int maxAttempts = 3;
            boolean loginSuccess = false;

            while (Attempts < maxAttempts) {
                String userName = JOptionPane.showInputDialog("Login", "Please enter your username:");
                String userPassword = JOptionPane.showInputDialog("Password", "Please enter your password:");

                if (userName == null || userPassword == null) {
                    JOptionPane.showMessageDialog(null, "Login cancelled.");
                    System.exit(0);
                }

                if (userName.equals(username) && userPassword.equals(password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    String firstName = JOptionPane.showInputDialog("Enter your first name:");
                    String lastName = JOptionPane.showInputDialog("Enter your last name:");
                    JOptionPane.showMessageDialog(null, "Welcome, " + firstName + " " + lastName);
                    loginSuccess = true;
                    break;
                } else {
                    Attempts++;
                    JOptionPane.showMessageDialog(null, "Username or Password incorrect. Attempts remaining: " + (maxAttempts - Attempts));
                }
            }

            if (!loginSuccess) {
                JOptionPane.showMessageDialog(null, "Maximum login attempts exceeded. Access denied.");
                System.exit(0);
            }

            boolean QuickChat = true;
            int numMessages = 0;        // Number of messages user wants to send
            int messagesSent = 0;        // Counter for messages sent by the user
            JSONArray storedMessages = new JSONArray();      //Store messages for later use
            //Setting up Arrays for the variuos options

            String[] sentMessagesArr = new String[10];
            String[] disregardedMessagesArr = new String[10];
            String[] storedMessagesArr = new String[10];
            String[] messageHashArr = new String[10];
            String[] messageIDArr = new String[10];
            String[] recipientArr = new String[10];
            int messageCount = 0;

            //Display the Quick Chat
            while (QuickChat) {
                String input = JOptionPane.showInputDialog(null, "Welcome to QuickChat:\n" + "1) Send Messages\n2) Show stored messages (coming soon)\n3) Quit", "Please select one of the above options");     //Promp user for their choice

                if (input == null) {
                    QuickChat = false;
                    continue;
                } else if (input.equals("3")) {
                    JOptionPane.showMessageDialog(null, "GoodBye!");      //Exit program if user chooses option 3
                    break;
                } else if (input.equals("2")) {
                    JOptionPane.showMessageDialog(null, "Coming soon");  //Store messages is not yet implemented
                    continue;
                } else if (input.equals("1")) {
                    numMessages = Integer.parseInt(JOptionPane.showInputDialog(null, "How many messages would you like to send?")); //Prompt user for the amount of messages they would like to send
                }

                //for loop for option 1 which is dependent on the amount of messages the user wants to send
                for (int i = 0; i < numMessages && messagesSent < numMessages; i++) {

                    String recipient = JOptionPane.showInputDialog(null, "Enter recipient cellphone number (+27 followed by max 10 digits):");   // Prompt user to enter the recipients cellphone number and check if number is valid
                    if (recipient == null || !recipient.matches(numberPattern)) {
                        JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain international code.");
                        continue;
                    }

                    String message = JOptionPane.showInputDialog(null, "Enter message (max 250 characters):");   // Prompt user to enter the message
                    if (message == null) {
                        continue;
                    }
                    if (message.length() > 250) {                                                                                                               //Make sure the message fits the criteria
                        JOptionPane.showMessageDialog(null, "Message exceeds 250 characters! Please try again. ");
                        break;
                    }

                    //Setting up the message data
                    String messageID = String.valueOf((int) (Math.random() * 1000000000)); // Random 10-digit number
                    messagesSent++; // Count the number of sent messages
                    String str = message.trim();
                    int pos1 = str.indexOf(" ");
                    int pos2 = str.lastIndexOf(" ");
                    String firstWord = str.substring(0, pos1);
                    String lastWord = str.substring(pos2 + 1);

                    // Build the message hash using StringBuilder
                    StringBuilder messageHashBuilder = new StringBuilder();
                    messageHashBuilder.append(messageID.substring(0, 2)).append(":").append(messagesSent).append(":").append(firstWord).append(lastWord);
                    String messageHash = messageHashBuilder.toString().toUpperCase();

                    //
                    // Display the send options to the user
                    String option = JOptionPane.showInputDialog(null, "Message completed. Choose an option:\n1) Send Message\n2) Disregard Message\n3) Store Message for later");
                    if (option != null) {
                        switch (option) {
                            case "1":
                                JOptionPane.showMessageDialog(null, "Message successfully sent."); // Send message
                                messageIDArr[messageCount] = messageID;
                                messageHashArr[messageCount] = messageHash;
                                recipientArr[messageCount] = recipient;
                                sentMessagesArr[messageCount] = message;
                                messageCount++;

                                break;
                            case "2":
                                JOptionPane.showMessageDialog(null, "Deleted succesfully.");  // Disregard message
                                messagesSent--; // Removes message from memory if disregarded
                                break;
                            case "3":
                                JSONObject messageObj = new JSONObject();  // Store message as JSON object
                                messageObj.put("messageID", messageID);
                                messageObj.put("messageHash", messageHash);
                                messageObj.put("recipient", recipient);
                                messageObj.put("message", message);
                                storedMessages.put(messageObj);
                                JOptionPane.showMessageDialog(null, "Message successfully stored.");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Invalid option.");  //Default option if invalid option is chosen
                        }
                    }

                    // Display Message Summary
                    JOptionPane.showMessageDialog(null, "Message ID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + message);

                }
                JOptionPane.showMessageDialog(null, "Total messages sent is: " + messagesSent);
            //Setting up a flag variable for the options list
            boolean arrayMenu = true;
// Loop to show the user the array-based message options
            while (arrayMenu) {
                String arrayOption = JOptionPane.showInputDialog("Please select an option below:\n" // Show the menu of available options
                        + "1) Display all sent messages\n"
                        + "2) Display the longest sent message\n"
                        + "3) Search by message ID\n"
                        + "4) Search by recipient number\n"
                        + "5) Delete by message hash\n"
                        + "6) Display report\n"
                        + "7) Exit");

// Exit the loop if user selects "7" or presses cancel
                if (arrayOption == null || arrayOption.equals("7")) {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    break;
                }

                switch (arrayOption) {
                    
                    // Display all sent messages
                    case "1":
                        StringBuilder allSentMessages = new StringBuilder("Sent Messages:\n");
                        for (int i = 0; i < messageCount; i++) {
                            allSentMessages.append(" To: ").append(recipientArr[i])
                                    .append("\nMessage: ").append(sentMessagesArr[i])
                                    .append("\n\n");
                        }
                        JOptionPane.showMessageDialog(null, allSentMessages.toString());
                        break;
                        
                        // Find and display the longest message
                    case "2":
                        int longestIndex = 0;
                        for (int i = 1; i < messageCount; i++) {
                            if (sentMessagesArr[i].length() > sentMessagesArr[longestIndex].length()) {
                                longestIndex = i;
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Longest Message" + "\nTo: " + recipientArr[longestIndex] + "\nMessage: " + sentMessagesArr[longestIndex]);
                        break;

                    // Search for a message by its ID
                    case "3":
                        String searchID = JOptionPane.showInputDialog("Enter Message ID to search:");
                        boolean found = false;
                        for (int i = 0; i < messageCount; i++) {
                            if (messageIDArr[i].equals(searchID)) {
                                JOptionPane.showMessageDialog(null, "Message Found:\nTo: " + recipientArr[i]
                                        + "\nMessage: " + sentMessagesArr[i]);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Message ID not found.");
                        }
                        break;

                    // Search for all messages sent to a specific recipient number
                    case "4":
                        String searchRecipient = JOptionPane.showInputDialog("Enter recipient number to search:");
                        StringBuilder matches = new StringBuilder("Messages to " + searchRecipient + ":\n");
                        boolean recipientMatches = false;
                        for (int i = 0; i < messageCount; i++) {
                            if (recipientArr[i].equals(searchRecipient)) {
                                matches.append("ID: ").append(messageIDArr[i])
                                        .append("\nMessage: ").append(sentMessagesArr[i]).append("\n\n");
                                recipientMatches = true;
                            }
                        }
                        if (recipientMatches) {
                            JOptionPane.showMessageDialog(null, matches.toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "No messages found for that recipient.");
                        }
                        break;

                    // Delete a message by its hash
                    case "5":
                        String deleteHash = JOptionPane.showInputDialog("Enter message hash to delete:");
                        boolean deleted = false;
                        for (int i = 0; i < messageCount; i++) {
                            if (messageHashArr[i].equals(deleteHash)) {

                                for (int j = i; j < messageCount - 1; j++) {
                                    messageIDArr[j] = messageIDArr[j + 1];
                                    messageHashArr[j] = messageHashArr[j + 1];
                                    recipientArr[j] = recipientArr[j + 1];
                                    sentMessagesArr[j] = sentMessagesArr[j + 1];
                                    sentMessagesArr[j] = sentMessagesArr[j + 1];
                                }
                                messageCount--;
                                JOptionPane.showMessageDialog(null, "Message deleted successfully.");
                                deleted = true;
                                break;
                            }
                        }
                        if (!deleted) {
                            JOptionPane.showMessageDialog(null, "Message hash not found.");
                        }
                        break;
                    //Display report of all the messages sent
                    case "6":
                        StringBuilder report = new StringBuilder("Full Message Report:\n\n");
                        for (int i = 0; i < messageCount; i++) {
                            report.append("ID: ").append(messageIDArr[i]).append("\nHash: ").append(messageHashArr[i]).append("\nTo: ").append(recipientArr[i]).append("\nMessage: ").append(sentMessagesArr[i]).append("\n---------------------\n");
                        }
                        JOptionPane.showMessageDialog(null, report.toString());
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                }
            }

        }
            }



    }
}
/*
The regex patterns for password complexity, username pattern and cellphone number formatting were adapted with the assistance of ChatGPT (OpenAI, 2025) -

Reference: OpenAI, 2025. ChatGPT (GPT-4, April 2025 version) - [online]
 Available at:  https://chat.openai.com

Accessed 20 April 2025
 */

 /*Reference:
The method used for selecting the first and last words of the message was implemented with guidance from the following tutorial:
 Input a string and print its first and last word | string in java
Author: Genuine Coder
 URL: https://youtu.be/BDlxw2ojPBI?si=hoShxgd3Z75pC5k9
 Accessed: [23 May 2025]
 */
 /*
The JSON method used for storing the messages were adapted with the assistance of ChatGPT (OpenAI, 2025) -

Reference: OpenAI, 2025. ChatGPT (GPT-4, May 2025 version) - [online]
 Available at:  https://chat.openai.com

Accessed [23 May 2025]
 */

 /*
The JSON storage input was given with the assistance of ChatGPT (OpenAI, 2025) -

Reference: OpenAI, 2025. ChatGPT (GPT-4, April 2025 version) - [online]
 Available at:  https://chat.openai.com

Accessed 23 June 2025
 */
