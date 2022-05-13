package pages.oasis;

import org.openqa.selenium.WebDriver;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OasisPageData {
    private static final Random random = new Random();
    private static final List<Pair<String, String>> data = new ArrayList<Pair<String, String>>() {{
        add(new Pair("Hellish Reversal Candle", "https://itc.ua/news/adskaya-razvorotnaya-svecha-bitkoin-otygral-30-na-fone-padeniya-terra/"));
        add(new Pair("Crypto CRASH Before The Great Ethereum Hash rate Migration!", "https://www.youtube.com/watch?v=C3Rm6EsJAG0"));
        add(new Pair("dogecoin forecast", "https://www.business2community.com/cryptocurrency/dogecoin-price-prediction"));
        add(new Pair("LTC forecast", "https://capital.com/ru/prognoz-tseny-litecoin-ltc"));
        add(new Pair("computer predict BTC", "https://news.mit.edu/2014/mit-computer-scientists-can-predict-price-bitcoin"));
    }};

    public static Pair<String, String> getTestData() {
        int randomIndex = random.nextInt(data.size());
        return data.get(randomIndex);
    }
}
