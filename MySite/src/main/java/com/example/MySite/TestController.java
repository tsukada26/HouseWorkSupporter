package com.example.MySite;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;



@Controller
@RequestMapping()
public class TestController {

    @Autowired

    @GetMapping("/index")
    public String index() {
        return "Index";
    }
    
    @GetMapping("/calc")
    public String calc() {
        return "calc";
    }
    @GetMapping("/test")
    public String test(){
    	RestClient defaultClient = RestClient.create();
    	String result = defaultClient.get()
    			  .uri("https://example.com")
    			  .retrieve()
    			  .body(String.class);

    			System.out.println(result);
    	return "Index";
    }

        private static final Logger logger = LoggerFactory.getLogger(TestController.class);

        /**
         * Simply selects the home view to render by returning its name.
         */
        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String home(Locale locale, Model model) {
            logger.info("Welcome home! The client locale is {}.", locale);

            Date date = new Date();
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

            String formattedDate = dateFormat.format(date);

            model.addAttribute("serverTime", formattedDate );

            // ここから
            Tess4jUtils tess = new Tess4jUtils();
            tess.getWord();
            // ここまで

            return "index";
        }
        // "http://localhost:8080/api/weather/tokyo" でアクセス。
        /**
        * @return 東京の天気情報
        * 
        * @see <a href="http://weather.livedoor.com/weather_hacks/webservice">お天気Webサービス - livedoor</a>
        */
        @RequestMapping( value="weather/tokyo"
                , produces=MediaType.APPLICATION_JSON_VALUE
                , method=RequestMethod.GET)
        private String call() {

            RestTemplate rest = new RestTemplate();

            final String cityCode = "130010"; // 東京のCityCode
            final String endpoint = "http://weather.livedoor.com/forecast/webservice/json/v1";

            final String url = endpoint + "?city=" + cityCode;

            // 直接Beanクラスにマップ出来るけど今回はめんどくさいのでStringで。
            ResponseEntity<String> response = rest.getForEntity(url, String.class);

            String json = response.getBody();
            System.out.print("JSON"+json);
            return "tokyo";
        }
}