package com.example.demo_tudien.PixabayAPI;

import javafx.scene.image.Image;
import ru.blizzed.pixabaylib.Pixabay;
import ru.blizzed.pixabaylib.PixabayCallException;
import ru.blizzed.pixabaylib.PixabayErrorException;
import ru.blizzed.pixabaylib.model.PixabayImage;
import ru.blizzed.pixabaylib.params.LangParam;
import ru.blizzed.pixabaylib.params.PixabayParams;

import java.util.List;

public class PixabayAPI {
    public static Image getRandomImage (String target, LangParam.Lang lang) {
        try {
            List<String> imageURLs = Pixabay.search().image(PixabayParams.QUERY.of(target), PixabayParams.LANG.of(lang))
                    .execute()
                    .getHits()
                    .stream()
                    .map(PixabayImage::getLargeImageURL)
                    .toList();
            String randomURL = "";
            if (!imageURLs.isEmpty()) {
                randomURL = imageURLs.get(0);
            } else {
                return null;
            }
            System.out.println(randomURL);
            return new Image(randomURL,true);
        } catch (PixabayCallException | PixabayErrorException e) {
            throw new RuntimeException(e);
        }
    }
}
