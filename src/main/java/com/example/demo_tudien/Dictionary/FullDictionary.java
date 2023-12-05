package com.example.demo_tudien.Dictionary;

public interface FullDictionary {
    String EVdictionaryPath = "src/main/resources/com/example/demo_tudien/DictionarySrc/Anh-Viet.txt";
    String VEdictionaryPath = "src/main/resources/com/example/demo_tudien/DictionarySrc/Viet-Anh.txt";
    String savedWordsPath = "src/main/resources/com/example/demo_tudien/DictionarySrc/TuDuocLuuLai.txt";
    String historyWordsPath = "src/main/resources/com/example/demo_tudien/DictionarySrc/TraGanDay.txt";

    VietnameseEnglish VEdictionary = new VietnameseEnglish();
    EnglishVietnamese EVdictionary = new EnglishVietnamese();
    VietnameseEnglish savedWords = new VietnameseEnglish();
    VietnameseEnglish historyWords= new VietnameseEnglish();
}
