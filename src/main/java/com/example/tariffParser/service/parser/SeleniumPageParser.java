package com.example.tariffParser.service.parser;

import com.example.tariffParser.model.Tariff;
import org.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SeleniumPageParser implements PageParser {
    private final String url;
    private final Pattern patternForGb = Pattern.compile("(\\d+) ГБ");
    private final Pattern patternForMin = Pattern.compile("(\\d+) минут");

    public SeleniumPageParser(@Value("${parser.url:}") String url) {
        this.url = url;
    }

    @Override
    public List<Tariff> getTariffs() throws IOException {
        WebDriver driver = getChromeDriver();
        driver.get(url);

        Set<Tariff> tariffs = new TreeSet<>(Comparator.comparing(Tariff::getDescription));

        List<WebElement> firstFilters = driver.findElements(By.cssSelector(".tariffs-showcase-first-filters__title"));

        for (var firstFilter : firstFilters) {
            firstFilter.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
            List<WebElement> secondFilters = driver.findElements(By.cssSelector(".mfui-v6-chip__inner"));

            for (var secondFilter : secondFilters) {
                secondFilter.click();
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
                tariffs.addAll(fetchCurrentTariffs(driver.getPageSource()));
            }
        }

        return tariffs.stream().toList();
    }

    private ChromeDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }

    List<Tariff> fetchCurrentTariffs(String page) {
        Document document = Jsoup.parse(page);
        Elements tariffsBlocks = document.select(".tariffs-carousel-v4__card-wrapper");
        List<Tariff> tariffs = new ArrayList<>();

        for (var tariffBlock : tariffsBlocks) {
            tariffs.add(fetchTariffsFields(tariffBlock));
        }

        return tariffs;
    }

    private Tariff fetchTariffsFields(Element tariffBlock) {
        String name = tariffBlock.select("a.tariffs-card-header-v4__title-link").text();
        String price = tariffBlock.select("div.tariffs-card-buy-v4__price").text();
        String description = tariffBlock.select(".tariffs-card-v4__body>div:not(.tariffs-card-v4__buy)").text();
        String tariffUrl = url + getEndPoint(tariffBlock.select("a.tariffs-card-header-v4__title-link").attr("href"));
        int gbCount = getGbFromDescription(description);
        int minutesCount = getMinFromDescription(description);

        return new Tariff(name, description, price, minutesCount, gbCount, tariffUrl);
    }

    private String getEndPoint(String tariffUrl) {
        return tariffUrl.substring(tariffUrl.lastIndexOf('/'));
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
