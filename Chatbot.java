import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Chatbot extends JFrame implements ActionListener {
    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;
    HashMap<String, String> responses;

    Chatbot() {
        // Predefined responses
        responses = new HashMap<>();
        responses.put("hi", "Hello! How can I help you?");
        responses.put("hello", "Hi there!");
        responses.put("how are you", "I'm just a bot, but I'm doing great!");
        responses.put("what is your name", "I'm a Java ChatBot.");
        responses.put("bye", "Goodbye! Have a great day.");
        responses.put("thanks", "You're welcome!");


        setTitle("AI ChatBot");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        sendButton = new JButton("Send");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);


        sendButton.addActionListener(this);
        inputField.addActionListener(this);

        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        String userText = inputField.getText().toLowerCase().trim();
        if (userText.isEmpty()) return;

        chatArea.append("You: " + userText + "\n");
        inputField.setText("");

        String response = getResponse(userText);
        chatArea.append("Bot: " + response + "\n");
    }


    private String getResponse(String input) {
        for (String key : responses.keySet()) {
            if (input.contains(key)) {
                return responses.get(key);
            }
        }
        return "I'm not sure how to respond to that.";
    }

    public static void main(String[] args) {
        new Chatbot();
    }
}
