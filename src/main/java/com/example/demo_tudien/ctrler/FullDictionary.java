package com.example.demo_tudien.ctrler;

import com.example.demo_tudien.Dictionary.Dictionary;
import com.example.demo_tudien.Dictionary.EnglishVietnamese;
import com.example.demo_tudien.Dictionary.HandledDicionary;
import com.example.demo_tudien.Dictionary.VietnameseEnglish;

public class FullDictionary {
    public static VietnameseEnglish VEdictionary = new VietnameseEnglish();
    public static EnglishVietnamese EVdictionary = new EnglishVietnamese();

    public static HandledDicionary savedWords = new HandledDicionary();
    public static HandledDicionary historyWords= new HandledDicionary();
}
