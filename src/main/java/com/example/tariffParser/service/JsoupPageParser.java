package com.example.tariffParser.service;

import com.example.tariffParser.model.Tariff;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class JsoupPageParser implements PageParser {
    private final String url;

    private final Pattern patternForGb = Pattern.compile("(\\d+) ГБ");
    private final Pattern patternForMin = Pattern.compile("(\\d+) минут");


    public JsoupPageParser(@Value("${parser.url:}") String url) {
        this.url = url;
    }

    @Override
    public List<Tariff> getTariffs() throws IOException {
        try {
            log.debug("Запрашивание тарифов с сайта");
            Document document = Jsoup.connect(url).get();
            Elements tariffsBlocks = document.select(".tariffs-carousel-v4__card-wrapper");
            List<Tariff> tariffs = new ArrayList<>();

            for (var tariffBlock : tariffsBlocks) {
                tariffs.add(fetchTariffsFields(tariffBlock));
            }

            return tariffs;

        } catch (IOException e) {
            log.warn("Ошибка при парсинге тарифов");
            throw new IOException("Не удалось получить тарифы");
        }
    }

    private Tariff fetchTariffsFields(Element tariffBlock) {
        String name = tariffBlock.select("a.tariffs-card-header-v4__title-link").text();
        String price = tariffBlock.select("div.tariffs-card-buy-v4__price").text();
        String description = tariffBlock.select(".tariffs-card-v4__body>div:not(.tariffs-card-v4__buy)").text();
        String tariffUrl = url + tariffBlock.select("a.tariffs-card-header-v4__title-link").attr("href");
        int gbCount = getGbFromDescription(description);
        int minutesCount = getMinFromDescription(description);

        return new Tariff(name, description, price, minutesCount, gbCount, tariffUrl);
    }

    private int getGbFromDescription(String description) {
        Matcher matcherForGb = patternForGb.matcher(description);

        if (matcherForGb.find()) {
            return Integer.parseInt(matcherForGb.group(1));
        }

        return 0;
    }

    private int getMinFromDescription(String description) {
        Matcher matcherForMin = patternForMin.matcher(description);

        if (matcherForMin.find()) {
            return Integer.parseInt(matcherForMin.group(1));
        }

        return 0;
    }
}
