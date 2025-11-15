package ui;

import javax.swing.*;
import java.awt.*;
import api.DictionaryApi;
import util.JsonUtils;
import model.WordMeaning;
import exceptions.ApiException;

public class DictionaryUI {
    private JFrame frame;
    private JTextField input;
    private JTextArea output;

    public void show() {
        frame = new JFrame("QuickDictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        input = new JTextField(20);
        JButton searchBtn = new JButton("Search Meaning");
        output = new JTextArea(8, 40);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setEditable(false);

        JPanel top = new JPanel();
        top.add(new JLabel("Word: "));
        top.add(input);
        top.add(searchBtn);

        frame.getContentPane().add(top, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(output), BorderLayout.CENTER);

        searchBtn.addActionListener(e -> onSearch());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void onSearch() {
        String word = input.getText().trim();
        if (word.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a word.", "Input required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        output.setText("Searching...");
        // run network on a background thread to keep UI responsive
        new Thread(() -> {
            try {
                String json = DictionaryApi.fetchRawJson(word);
                String def = JsonUtils.extractFirstDefinition(json);
                if (def == null) def = "Definition not found in response.";
                WordMeaning wm = new WordMeaning(word, def);

                SwingUtilities.invokeLater(() -> {
                    output.setText("Word: " + wm.getWord() + "\n\nMeaning:\n" + wm.getMeaning());
                });
            } catch (ApiException ae) {
                SwingUtilities.invokeLater(() -> {
                    output.setText("");
                    JOptionPane.showMessageDialog(frame, "Error: " + ae.getMessage(), "API Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }
}
