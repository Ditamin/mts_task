package com.example.tariffParser.service;

import com.example.tariffParser.model.Tariff;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class PageParserImplTest {
    public static MockWebServer serviceMock;

    @BeforeAll
     static void setUp() throws IOException {
        serviceMock = new MockWebServer();
        serviceMock.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        serviceMock.shutdown();
    }

    @Test
    void getTariffsTest() throws IOException {
        String baseUrl = String.format("http://localhost:%s", serviceMock.getPort());
        var parser = new JsoupPageParser(baseUrl);
        String tariffHtml = "<div class=\"tariffs-carousel-v4__card-wrapper\"><div data-gtm-tariff-id=\"2359\" data-gtm-tariff-title=\"Максимум\" data-gtm-tariff-calls-min=\"\" data-gtm-tariff-calls-bl=\"\" data-gtm-tariff-traffic=\"40\" data-gtm-tariff-payment=\"870\" data-gtm-tariff-benefits=\"\" class=\"tariffs-card-v4 gtm-tariff-card-component\"><div class=\"tariffs-card-header-v4\"><div class=\"tariffs-card-header-v4__text\"><p class=\"mfui-v6-caption mfui-v6-caption_color_default mfui-v6-caption_space_tight mfui-v6-caption_variant_normal tariffs-card-header-v4__product\">МегаФон 3.0</p><div class=\"tariffs-card-header-v4__title\"><a class=\"tariffs-card-header-v4__title-link gtm-tariff-title-link\" href=\"/tariffs/all/mf_maksimum.html\">Максимум</a></div></div><div class=\"action-tile action-tile_font-color_white action-tile_code_action tariffs-card-header-v4__badge\"><div>Хит!</div></div></div><div class=\"tariffs-card-v4__body\"><div class=\"tariffs-card-additional-params-v4\"><div class=\"tariffs-card-additional-params-v4__param\"><div class=\"tariffs-card-additional-params-v4__value\">40 ГБ</div><div class=\"tariffs-card-additional-params-v4__trigger gtm-tariffs-card-caption-link\" data-gtm-title=\"&amp;#43; 10&nbsp;ГБ<br />с&nbsp;МегаСилой\"><p class=\"mfui-v6-caption mfui-v6-caption_color_default mfui-v6-caption_space_tight mfui-v6-caption_variant_medium tariffs-card-additional-params-v4__description\">+ 10&nbsp;ГБ<br>с&nbsp;МегаСилой</p><svg xmlns=\"http://www.w3.org/2000/svg\" xml:space=\"preserve\" viewBox=\"0 0 20 20\" fill=\"\" class=\"ui-lib-icon ui-lib-icon_fill_content ui-lib-icon_size_small tariffs-card-additional-params-v4__trigger-icon\" aria-hidden=\"true\" focusable=\"false\"><path d=\"M10 2c4.4 0 8 3.6 8 8s-3.6 8-8 8-8-3.6-8-8 3.6-8 8-8zm0 5c.6 0 1-.4 1-1s-.4-1-1-1-1 .4-1 1 .4 1 1 1zm1 8V9H9v6h2z\"></path></svg></div></div><div class=\"tariffs-card-additional-params-v4__param\"><div class=\"tariffs-card-additional-params-v4__value\">900 минут</div></div><div class=\"tariffs-card-additional-params-v4__param tariffs-card-additional-params-v4__param_clicked gtm-tariffs-card-caption-link\" data-gtm-title=\"5 МегаСил<br />на выбор\"><div class=\"tariffs-card-additional-params-v4__value\">5<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 20 20\" fill=\"\" class=\"ui-lib-icon ui-lib-icon_fill_content ui-lib-icon_size_medium tariffs-card-additional-params-v4__megapower-title-icon\" aria-hidden=\"true\" focusable=\"false\"><path d=\"M11.39 9H16v1l-7.5 8H7l1.61-7H4v-1l7.5-8H13Z\" style=\"fill-rule: evenodd;\"></path></svg></div><p class=\"mfui-v6-caption mfui-v6-caption_color_default mfui-v6-caption_space_tight mfui-v6-caption_variant_medium tariffs-card-additional-params-v4__megapower-caption\">МегаСил<br>на выбор</p><div class=\"tariffs-service-icons tariffs-card-additional-params-v4__megapowers\"><div class=\"tariffs-service-icons__icon-area\" style=\"z-index:4\"><img class=\"ui-sprite-icon ui-sprite-icon_size_large tariffs-service-icons__icon\" src=\"/public/ceph/icon-full_set-pre-5g_cirlce_32_direct_url.svg\" alt=\"\"></div><div class=\"tariffs-service-icons__icon-area\" style=\"z-index:3\"><svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 32 32\" fill=\"\" class=\"ui-lib-icon ui-lib-icon_fill_content ui-lib-icon_size_large tariffs-service-icons__icon\" aria-hidden=\"true\" focusable=\"false\"><g fill=\"none\" fill-rule=\"evenodd\"><circle cx=\"16\" cy=\"16\" r=\"12\" fill=\"#2CA5E0\"></circle><g fill-rule=\"nonzero\"><path fill=\"#2CA5E0\" d=\"M13.325 21.823c-.44 0-.365-.167-.516-.584l-1.295-4.259 9.956-5.906\"></path><path fill=\"#FFF\" d=\"M13.325 21.823c.34 0 .49-.155.68-.34l1.81-1.759-2.26-1.362\"></path><path fill=\"#FFF\" d=\"m13.556 18.362 5.47 4.042c.625.344 1.076.166 1.231-.58l2.228-10.494c.229-.915-.347-1.329-.944-1.059L8.46 15.316c-.892.358-.887.856-.162 1.078l3.357 1.047 7.769-4.9c.366-.222.703-.103.427.142\"></path></g></g></svg></div><div class=\"tariffs-service-icons__icon-area\" style=\"z-index:2\"><svg xmlns=\"http://www.w3.org/2000/svg\" fill=\"\" viewBox=\"0 0 32 32\" class=\"ui-lib-icon ui-lib-icon_fill_content ui-lib-icon_size_large tariffs-service-icons__icon\" aria-hidden=\"true\" focusable=\"false\"><path fill=\"#07F\" d=\"M16 28c6.627 0 12-5.373 12-12S22.627 4 16 4 4 9.373 4 16s5.373 12 12 12Z\"></path><path fill=\"#fff\" d=\"M16.625 20.5c-4.783 0-7.511-3.003-7.625-8h2.396c.079 3.668 1.845 5.221 3.244 5.541V12.5h2.256v3.163c1.382-.136 2.834-1.577 3.323-3.163h2.256a5.85 5.85 0 0 1-1.08 2.287 6.47 6.47 0 0 1-1.989 1.701 6.842 6.842 0 0 1 2.267 1.653A6.155 6.155 0 0 1 23 20.5h-2.483a3.933 3.933 0 0 0-1.34-1.93 4.518 4.518 0 0 0-2.28-.929V20.5h-.272Z\"></path></svg></div><div class=\"tariffs-service-icons__icon-invisible-area tariffs-card-additional-params-v4__megapowers-arrow\"><svg xmlns=\"http://www.w3.org/2000/svg\" xml:space=\"preserve\" viewBox=\"0 0 20 20\" fill=\"\" class=\"ui-lib-icon ui-lib-icon_fill_content ui-lib-icon_size_medium\" aria-hidden=\"true\" focusable=\"false\"><path d=\"m8 13 3-3-3-3 1-1 4 4-4 4z\"></path></svg></div></div></div></div><div class=\"tariffs-card-v4__buy\"><div class=\"tariffs-card-buy-v4\"><div class=\"tariffs-card-buy-v4__price-wrapper\"><div class=\"tariffs-card-buy-v4__price\">870<!-- --> <!-- -->₽</div><p class=\"mfui-v6-caption mfui-v6-caption_color_default mfui-v6-caption_space_tight mfui-v6-caption_variant_normal mfui-v6-caption_has-margin tariffs-card-buy-v4__price-period\">за&nbsp;30&nbsp;дней</p></div><div class=\"tariffs-card-buy-v4__price-text\"><p class=\"mfui-v6-caption mfui-v6-caption_color_default mfui-v6-caption_space_wide mfui-v6-caption_variant_normal\">Выгоднее с&nbsp;абонементом</p></div><div class=\"tariffs-card-buy-v4__buttons-wrapper\"><button class=\"mfui-v6-button mfui-v6-button_type_primary mfui-v6-button_theme_green mfui-v6-button_size-all_small mfui-v6-button_no-touch mfui-v6-button_content-type_text tariffs-card-buy-v4__button gtm-tariff-choose-button\" type=\"button\"><div class=\"mfui-v6-button__inner\"><div class=\"mfui-v6-button__content\"><span class=\"mfui-v6-button__text\">Выбрать</span></div></div></button><a class=\"tariffs-card-buy-v4__more-link gtm-tariff-more-link\" href=\"/tariffs/all/mf_maksimum.html\" target=\"_self\">Подробнее</a></div></div></div></div><div class=\"tariffs-card-popup gtm-tariff-param-popup\" data-gtm-is-opened=\"false\"></div></div></div>";
        serviceMock.enqueue(new MockResponse()
                .addHeader("Content-Type", "text/html; charset=utf-8")
                .setBody(tariffHtml));
        Tariff tariff = new Tariff(
                "Максимум",
                "40 ГБ + 10 ГБ с МегаСилой 900 минут 5 МегаСил на выбор",
                "870 ₽",
                900,
                40,
                baseUrl + "/tariffs/all/mf_maksimum.html");

        List<Tariff> res = parser.getTariffs();
        Tariff resTariff = res.getFirst();

        Assertions.assertThat(resTariff.toString()).isEqualTo(tariff.toString());
    }

    @Test
    void getTariffsEmptyResponseTest() throws IOException {
        String baseUrl = String.format("http://localhost:%s", serviceMock.getPort());
        var parser = new JsoupPageParser(baseUrl);
        String tariffHtml = "<html></html>";
        serviceMock.enqueue(new MockResponse()
                .addHeader("Content-Type", "text/html; charset=utf-8")
                .setBody(tariffHtml));

        List<Tariff> res = parser.getTariffs();
        Assertions.assertThat(res).isEmpty();
    }

    @Test
    void getTariffsFailedToFetchDataTest() throws IOException {
        String baseUrl = String.format("http://localhost:%s", serviceMock.getPort());
        var parser = new JsoupPageParser(baseUrl);

        serviceMock.enqueue(new MockResponse().setStatus("500"));

        Assertions.assertThatThrownBy(parser::getTariffs).isInstanceOf(IOException.class);
    }
}