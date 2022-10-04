package com.techelevator;

import java.io.*;
import java.util.*;

public class CipherApp {


    private static final String MAIN_MENU_OPTION_RAW = "Print an encrypted/decrypted message";
    private static final String MAIN_MENU_OPTION_FILE = "Encrypt/Decrypt to a new text file";
    private static final String EXIT_APP = "Exit app";
    private static final String ENCRYPT = "Encrypt";
    private static final String DECRYPT = "Decrypt";
    private static final String PROMPT_FOR_FILE_PATH = "Please enter a valid file path>>>";
    private static final String PROMPT_FOR_TEXT = "Enter some text>>>";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_RAW, MAIN_MENU_OPTION_FILE, EXIT_APP};
    private static final String[] ENCRYPTION_OPTIONS = {ENCRYPT, DECRYPT};

    private Menu menu;
    private Substitution sub;
    private Scanner userInput;
    private PrintWriter writer;

    public CipherApp(Menu menu, Substitution sub, InputStream in, OutputStream out) {
        this.menu = menu;
        this.sub = sub;
        this.userInput = new Scanner(in);
        this.writer = new PrintWriter(out);
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        Substitution sub = new Substitution();
        CipherApp app = new CipherApp(menu, sub, System.in, System.out);
        app.run();
    }

    public void run() {
        while (true) {
            String select = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (select.equals(MAIN_MENU_OPTION_RAW)) {
                System.out.println(PROMPT_FOR_TEXT);
                String userText = userInput.nextLine();
                String selectType = (String) menu.getChoiceFromOptions(ENCRYPTION_OPTIONS);
                if (selectType.equals(ENCRYPT)) {
                    printMessageToConsole(userText, true);
                } else if (selectType.equals(DECRYPT)) {
                    printMessageToConsole(userText, false);
                }
            } else if (select.equals(MAIN_MENU_OPTION_FILE)) {
                System.out.println(PROMPT_FOR_FILE_PATH);
                String filePath = userInput.nextLine();
                String SelectType = (String) menu.getChoiceFromOptions(ENCRYPTION_OPTIONS);
                if (SelectType.equals(ENCRYPT)) {
                    handleFileConversion(filePath, true);
                } else if (SelectType.equals(DECRYPT)) {
                    handleFileConversion(filePath, false);
                }
            } else if (select.equals(EXIT_APP)) {
                break;
            }
        }
    }

    private void printMessageToConsole(String messageInput, boolean isEncoded) {
        try (Scanner msgReader = new Scanner(messageInput)) {
            while (msgReader.hasNextLine()) {
                String userText = msgReader.nextLine().toUpperCase();
                if (isEncoded) {
                    System.out.println(sub.encrypt(userText));
                } else {
                    System.out.println(sub.decrypt(userText));
                }
            }
        } catch (IllegalArgumentException e) {
            e.getLocalizedMessage();
        }
    }

    private void handleFileConversion(String filePath, boolean isEncoded) {
        File userFile = new File(filePath);
        File convertedFile = getFile(userFile);
        int lineCount = 0;
        try (Scanner fileInput = new Scanner(userFile);
             PrintWriter writer = new PrintWriter(convertedFile)) {
            while (fileInput.hasNextLine()) {
                String text = fileInput.nextLine().toUpperCase();
                lineCount++;
                if (isEncoded) {
                    writer.println(sub.encrypt(text));
                } else {
                    writer.println(sub.decrypt(text));
                }
            }
        } catch (FileNotFoundException e) {
            e.getLocalizedMessage();
        } finally {
            String message = "Encrypted/Decrypted " + lineCount +
                    " lines of file " + convertedFile.getName() +
                    " on " + new Date();
            System.out.println(message);
        }
    }

    static private File getFile(File txtFile) {
        String txtPath = txtFile.getAbsolutePath();
        int dotIndex = txtPath.lastIndexOf('.');
        String filePath;
        if (dotIndex >= 0) {
            filePath = txtPath.substring(0, dotIndex) + ".converted." + txtPath.substring(dotIndex + 1);
        } else {
            filePath = txtPath + ".converted";
        }
        return new File(filePath);
    }
}
