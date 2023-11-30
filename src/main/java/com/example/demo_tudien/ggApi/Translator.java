package com.example.demo_tudien.ggApi;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import javazoom.jl.player.Player;
public class Translator {

    public static String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycby3AOWmhe32TgV9nW-Q0TyGOEyCHQeFiIn7hRgy5m8jHPaXDl2GdToyW_3Ys5MTbK6wjg/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine.replace("&#39;","'"));
        }
        in.close();
        return response.toString();
    }


    public static void textToSpeech(String text, String lang) {
        try {
            String urlStr =
                    "https://translate.google.com/translate_tts?ie=UTF-8&tl="
                            + lang
                            + "&client=tw-ob&q="
                            + URLEncoder.encode(text, "UTF-8") ;
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream sound = con.getInputStream();
            Player player = new Player(sound);
            player.play();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final Map<String,String> languages = new HashMap<String,String>();
    static {
        languages.put("Afrikaans", "af");
        languages.put("Albanian", "sq");
        languages.put("Amharic", "am");
        languages.put("Arabic", "ar");
        languages.put("Armenian", "hy");
        languages.put("Assamese", "as");
        languages.put("Aymara", "ay");
        languages.put("Azerbaijani", "az");
        languages.put("Bambara", "bm");
        languages.put("Basque", "eu");
        languages.put("Belarusian", "be");
        languages.put("Bengali", "bn");
        languages.put("Bhojpuri", "bho");
        languages.put("Bosnian", "bs");
        languages.put("Bulgarian", "bg");
        languages.put("Catalan", "ca");
        languages.put("Cebuano", "ceb");
        languages.put("ChineseSimplified", "zh-CN");
        languages.put("ChineseTraditional", "zh-TW");
        languages.put("Corsican", "co");
        languages.put("Croatian", "hr");
        languages.put("Czech", "cs");
        languages.put("Danish", "da");
        languages.put("Dhivehi", "dv");
        languages.put("Dogri", "doi");
        languages.put("Dutch", "nl");
        languages.put("English", "en");
        languages.put("Esperanto", "eo");
        languages.put("Estonian", "et");
        languages.put("Ewe", "ee");
        languages.put("Filipino", "fil");
        languages.put("Finnish", "fi");
        languages.put("French", "fr");
        languages.put("Frisian", "fy");
        languages.put("Galician", "gl");
        languages.put("Georgian", "ka");
        languages.put("German", "de");
        languages.put("Greek", "el");
        languages.put("Guarani", "gn");
        languages.put("Gujarati", "gu");
        languages.put("HaitianCreole", "ht");
        languages.put("Hausa", "ha");
        languages.put("Hawaiian", "haw");
        languages.put("Hebrew", "he");
        languages.put("Hindi", "hi");
        languages.put("Hmong", "hmn");
        languages.put("Hungarian", "hu");
        languages.put("Icelandic", "is");
        languages.put("Igbo", "ig");
        languages.put("Ilocano", "ilo");
        languages.put("Indonesian", "id");
        languages.put("Irish", "ga");
        languages.put("Italian", "it");
        languages.put("Japanese", "ja");
        languages.put("Javanese", "jv");
        languages.put("Kannada", "kn");
        languages.put("Kazakh", "kk");
        languages.put("Khmer", "km");
        languages.put("Kinyarwanda", "rw");
        languages.put("Konkani", "gom");
        languages.put("Korean", "ko");
        languages.put("Krio", "kri");
        languages.put("Kurdish", "ku");
        languages.put("KurdishSorani", "ckb");
        languages.put("Kyrgyz", "ky");
        languages.put("Lao", "lo");
        languages.put("Latin", "la");
        languages.put("Latvian", "lv");
        languages.put("Lingala", "ln");
        languages.put("Lithuanian", "lt");
        languages.put("Luganda", "lg");
        languages.put("Luxembourgish", "lb");
        languages.put("Macedonian", "mk");
        languages.put("Maithili", "mai");
        languages.put("Malagasy", "mg");
        languages.put("Malay", "ms");
        languages.put("Malayalam", "ml");
        languages.put("Maltese", "mt");
        languages.put("Maori", "mi");
        languages.put("Marathi", "mr");
        languages.put("Meiteilon", "mni-Mtei");
        languages.put("Mizo", "lus");
        languages.put("Mongolian", "mn");
        languages.put("MyanmarBurmese", "my");
        languages.put("Nepali", "ne");
        languages.put("Norwegian", "no");
        languages.put("Nyanja", "ny");
        languages.put("Odia", "or");
        languages.put("Oromo", "om");
        languages.put("Pashto", "ps");
        languages.put("Persian", "fa");
        languages.put("Polish", "pl");
        languages.put("Portuguese", "pt");
        languages.put("Punjabi", "pa");
        languages.put("Quechua", "qu");
        languages.put("Romanian", "ro");
        languages.put("Russian", "ru");
        languages.put("Samoan", "sm");
        languages.put("Sanskrit", "sa");
        languages.put("ScotsGaelic", "gd");
        languages.put("Sepedi", "nso");
        languages.put("Serbian", "sr");
        languages.put("Sesotho", "st");
        languages.put("Shona", "sn");
        languages.put("Sindhi", "sd");
        languages.put("Sinhala", "si");
        languages.put("Slovak", "sk");
        languages.put("Slovenian", "sl");
        languages.put("Somali", "so");
        languages.put("Spanish", "es");
        languages.put("Sundanese", "su");
        languages.put("Swahili", "sw");
        languages.put("Swedish", "sv");
        languages.put("Tagalog", "tl");
        languages.put("Tajik", "tg");
        languages.put("Tamil", "ta");
        languages.put("Tatar", "tt");
        languages.put("Telugu", "te");
        languages.put("Thai", "th");
        languages.put("Tigrinya", "ti");
        languages.put("Tsonga", "ts");
        languages.put("Turkish", "tr");
        languages.put("Turkmen", "tk");
        languages.put("Twi", "ak");
        languages.put("Ukrainian", "uk");
        languages.put("Urdu", "ur");
        languages.put("Uyghur", "ug");
        languages.put("Uzbek","uz");
        languages.put("Vietnamese","vi");
        languages.put("Welsh","cy");
        languages.put("Xhosa","xh");
        languages.put("Yiddish","yi");
        languages.put("Yoruba","yo");
        languages.put("Zulu","zu");
    }
    public static final TreeSet<String> languageList = new TreeSet<String>(languages.keySet());

}