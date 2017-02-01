### Парсер котировок Oil & Gas с сайта http://www.msenergyservices.com  @author Artem Faenko

**Применил:** 
- Back-end - Spring MVC
- Front-end - JS, jQuery(ajax), JSP, CSS, сборщик Gradle
- Library - Selenium(HtmlUnitDriver, PhantomJS), Jsoup, Jackson(json)

Т.к. котировки подгружаются скриптами, то пришлось использовать консольный виртуальный браузер.
Сначала для парсинга был применен - *PhantomJS*. Время загрузки данных составляло 3-5 секунд.

Но из-за того, что к нему еще нужно было подключать библиотеку для определённой версии ОС, которая весит 18 Мб, то был применен более легковесный аналог - *HtmlUnitDriver*.
Но скорость загрузки увеличилась до 12-15 секунд.
 
**Скриншоты:** 

![CC0](https://github.com/webserverby/parser-price/blob/master/screenshots/glav_1.png)

![CC0](https://github.com/webserverby/parser-price/blob/master/screenshots/glav_2.png)

![CC0](https://github.com/webserverby/parser-price/blob/master/screenshots/glav_3.png)

С сервера получаем json ответ *{"US Rig Count:":"51.90","WTI Crude Oil":"51.90 +1.93%","Brent Crude Oil":"55.21 +2.16%","Natural Gas"
:"3.41 -0.56%","P:":"936.442.2500","F:":"936.442.2599"}* 

**Java код парсера:** 
_________________________________________________________________________________________________________________________________


public String parsing() {
       
        HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_45, true);
        driver.get("http://www.msenergyservices.com");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        String htmlContent = driver.getPageSource();
        driver.close();

        Document document = Jsoup.parse(htmlContent);
        String elementRig =  document.select("#newsticker-block > div > table > tbody > tr > td:nth-child(1)").text();
        String[] arrRig = elementRig.trim().split("\\s+");

        String elementOil =  document.select( "tr[valign=bottom]" ).text();
        String[] arrOil = elementOil.trim().split("\\s+");

        String elementPF = document.select(".custom.topcontact").text();
        String[] arrPF = elementPF.trim().split("\\s+");

        LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
        data.put( arrRig[0] + " " + arrRig[1] + " " + arrRig[2], arrOil[3] );
        data.put( arrOil[0] + " " + arrOil[1] + " " + arrOil[2], arrOil[3] + " " + arrOil[4] );
        data.put( arrOil[5] + " " + arrOil[6] + " " + arrOil[7], arrOil[8] + " " + arrOil[9] );
        data.put( arrOil[10] + " " + arrOil[11], arrOil[12] + " " + arrOil[13] );
        data.put( arrPF[0], arrPF[1] );
        data.put( arrPF[3], arrPF[4] );

        ObjectMapper mapper = new ObjectMapper();
        String   jsonFromMap = null;
        try {
            jsonFromMap = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }     

        return jsonFromMap;
    }
_________________________________________________________________________________________________________________________________

**JS cкрипт:** 
_________________________________________________________________________________________________________________________________


	function replaceRed(str,index){
		return str.substr(0,index)+str.substr(index).replace(/\-\w+\..+%/,'<strong class="red-cot">$&</strong>');
	}

	function replaceGreen(str,index){
		return str.substr(0,index)+str.substr(index).replace(/\+\w+\..+%/,'<strong class="green-cot">$&</strong>');
	}

	function get_parse() {
		$.getJSON('/parser', function(data) {
			$.each(data, function(key, val) {
				if( val.indexOf('+') > 0 ){
					$('.row').append(
						'<div class="col-xs pull-left block">' +
						'<div class="col-xs pull-left white-cot">' + key + '</div>' +
						'<div class="col-xs pull-left">&nbsp;' + replaceGreen(val, 5) + '</div>' +
						'</div>'
					);
				} else if( val.indexOf('-') > 0){
					$('.row').append(
						'<div class="col-xs pull-left block">' +
						'<div class="col-xs pull-left white-cot">' + key + '</div>' +
						'<div class="col-xs pull-left">&nbsp;' + replaceRed(val, 5) + '</div>' +
						'</div>'
					);

				} else {
					$('.row').append(
						'<div class="col-xs pull-left block">' +
						'<div class="col-xs pull-left white-cot">' + key + '</div>' +
						'<div class="col-xs pull-left">&nbsp;' + val + '</div>' +
						'</div>'
					);
				}

			});
			$(".row").show();
			$("body").faLoading(false);
		});

		$("body").faLoading({
			"type" : "add",
			"icon" : "fa-refresh",
			"status" : 'loading',
			"text" : false,
			"title" : "Пожалуйста подождите..."
		});
	}
_________________________________________________________________________________________________________________________________
