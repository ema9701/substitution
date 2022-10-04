package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public class Substitution {

    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String CIPHER = "NJFQAPSRXGDOTEUHMVBCWKZILY";

    public Substitution() {}

    public Map<Character, Character> createCipherKey() {
        Map<Character, Character> key = new HashMap<>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            char letter = ALPHABET.charAt(i);
            char cipher = CIPHER.charAt(i);
            if (!key.containsKey(letter)) {
                key.put(letter, cipher);
            }
        }
        return key;
    }

    public Map<Character, Character> rotateKey(Map<Character, Character> mapKey) {
        Map<Character, Character> newKey = new HashMap<>();
        for (Map.Entry<Character, Character> chars : mapKey.entrySet()) {
            newKey.put(chars.getValue(), chars.getKey());
        }
        return newKey;
    }

    public String encrypt(String plainMessage) {
        Map<Character, Character> mapKey = createCipherKey();
        String encoded = "";
        for (int i = 0; i < plainMessage.length(); i++) {
            char mChar = plainMessage.charAt(i);
            encoded += mapKey.getOrDefault(mChar, mChar);
        }
        return encoded;
    }

    public String decrypt(String encodedMessage) {
        Map<Character, Character> rotated = rotateKey(createCipherKey());
        String decoded = "";
        for (int i = 0; i < encodedMessage.length(); i++) {
            char mChar = encodedMessage.charAt(i);
            decoded += rotated.getOrDefault(mChar, mChar);
        }
        return decoded;

    }
}

