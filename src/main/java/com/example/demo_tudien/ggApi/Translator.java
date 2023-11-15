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


    public static final String Afrikaans = "af";
    public static final String Albanian = "sq";
    public static final String Amharic = "am";

    public static final String Arabic = "ar";
    public static final String Armenian = "hy";
    public static final String Assamese = "as";
    public static final String Aymara = "ay";
    public static final String Azerbaijani = "az";
    public static final String Bambara = "bm";
    public static final String Basque = "eu";
    public static final String Belarusian = "be";
    public static final String Bengali = "bn";
    public static final String Bhojpuri = "bho";
    public static final String Bosnian = "bs";
    public static final String Bulgarian = "bg";
    public static final String Catalan = "ca";
    public static final String Cebuano = "ceb";
    public static final String ChineseSimplified = "zh-CN";
    public static final String ChineseTraditional = "zh-TW";
    public static final String Corsican = "co";
    public static final String Croatian = "hr";
    public static final String Czech = "cs";
    public static final String Danish = "da";
    public static final String Dhivehi = "dv";
    public static final String Dogri = "doi";
    public static final String Dutch = "nl";
    public static final String English = "en";
    public static final String Esperanto = "eo";
    public static final String Estonian = "et";
    public static final String Ewe = "ee";
    public static final String Filipino = "fil";
    public static final String Finnish = "fi";

    public static final String French = "fr";
    public static final String Frisian = "fy";
    public static final String Galician = "gl";
    public static final String Georgian = "ka";

    public static final String German = "de";
    public static final String Greek = "el";
    public static final String Guarani = "gn";
    public static final String Gujarati = "gu";
    public static final String HaitianCreole = "ht";
    public static final String Hausa = "ha";
    public static final String Hawaiian = "haw";
    public static final String Hebrew = "he";
    public static final String Hindi = "hi";
    public static final String Hmong = "hmn";
    public static final String Hungarian = "hu";
    public static final String Icelandic = "is";
    public static final String Igbo = "ig";
    public static final String Ilocano = "ilo";
    public static final String Indonesian = "id";
    public static final String Irish = "ga";

    public static final String Italian = "it";

    public static final String Japanese = "ja";
    public static final String Javanese = "jv";
    public static final String Kannada = "kn";
    public static final String Kazakh = "kk";

    public static final String Khmer = "km";
    public static final String Kinyarwanda = "rw";
    public static final String Konkani = "gom";
    public static final String Korean = "ko";
    public static final String Krio = "kri";
    public static final String Kurdish = "ku";
    public static final String KurdishSorani = "ckb";
    public static final String Kyrgyz = "ky";

    public static final String Lao = "lo";

    public static final String Latin = "la";
    public static final String Latvian = "lv";
    public static final String Lingala = "ln";
    public static final String Lithuanian = "lt";
    public static final String Luganda = "lg";
    public static final String Luxembourgish = "lb";
    public static final String Macedonian = "mk";
    public static final String Maithili = "mai";
    public static final String Malagasy = "mg";
    public static final String Malay = "ms";
    public static final String Malayalam = "ml";
    public static final String Maltese = "mt";
    public static final String Maori = "mi";
    public static final String Marathi = "mr";
    public static final String Meiteilon = "mni-Mtei";
    public static final String Mizo = "lus";
    public static final String Mongolian = "mn";
    public static final String MyanmarBurmese = "my";
    public static final String Nepali = "ne";
    public static final String Norwegian = "no";
    public static final String Nyanja = "ny";
    public static final String Odia = "or";
    public static final String Oromo = "om";
    public static final String Pashto = "ps";

    public static final String Persian = "fa";

    public static final String Polish = "pl";

    public static final String Portuguese = "pt";
    public static final String Punjabi = "pa";
    public static final String Quechua = "qu";
    public static final String Romanian = "ro";

    public static final String Russian = "ru";
    public static final String Samoan = "sm";

    public static final String Sanskrit = "sa";
    public static final String ScotsGaelic = "gd";
    public static final String Sepedi = "nso";
    public static final String Serbian = "sr";
    public static final String Sesotho = "st";
    public static final String Shona = "sn";
    public static final String Sindhi = "sd";
    public static final String Sinhala = "si";
    public static final String Slovak = "sk";
    public static final String Slovenian = "sl";
    public static final String Somali = "so";
    public static final String Spanish = "es";
    public static final String Sundanese = "su";
    public static final String Swahili = "sw";
    public static final String Swedish = "sv";
    public static final String Tagalog = "tl";
    public static final String Tajik = "tg";
    public static final String Tamil = "ta";
    public static final String Tatar = "tt";
    public static final String Telugu = "te";
    public static final String Thai = "th";
    public static final String Tigrinya = "ti";
    public static final String Tsonga = "ts";
    public static final String Turkish = "tr";
    public static final String Turkmen = "tk";
    public static final String Twi = "ak";
    public static final String Ukrainian = "uk";
    public static final String Urdu = "ur";
    public static final String Uyghur = "ug";
    public static final String Uzbek = "uz";

    public static final String Vietnamese = "vi";
    public static final String Welsh = "cy";
    public static final String Xhosa = "xh";
    public static final String Yiddish = "yi";
    public static final String Yoruba = "yo";
    public static final String Zulu = "zu";
}