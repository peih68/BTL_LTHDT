package com.example.demo_tudien.PixabayAPI;

import javafx.fxml.Initializable;
import ru.blizzed.pixabaylib.Pixabay;
import ru.blizzed.pixabaylib.PixabayCallException;
import ru.blizzed.pixabaylib.PixabayErrorException;
import ru.blizzed.pixabaylib.model.PixabayImage;
import ru.blizzed.pixabaylib.params.PixabayParams;

import javafx.scene.image.Image;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PixabayAPI implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pixabay.initialize("40694300-4e998735bc707c093f172a751");
    }

    public static Image getRandomImage (String target) {
        try {
            List<String> imageURLs = Pixabay.search().image(PixabayParams.QUERY.of(target))
                    .execute()
                    .getHits()
                    .stream()
                    .map(PixabayImage::getLargeImageURL)
                    .collect(Collectors.toList());
            Collections.shuffle(imageURLs);
            String randomURL = "";
            if (!imageURLs.isEmpty()) {
                randomURL = imageURLs.get(0);
            }
            System.out.println(randomURL);
            return new Image(randomURL,true);
        } catch (PixabayCallException | PixabayErrorException e) {
            throw new RuntimeException(e);
        }
    }
}
