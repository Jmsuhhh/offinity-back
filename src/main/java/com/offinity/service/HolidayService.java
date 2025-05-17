package com.offinity.service;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.offinity.dto.HolidayDto;

@Service
public class HolidayService {
	@Value("${holiday.api.url}")
	private String apiUrl;

	@Value("${holiday.api.key}")
	private String apiKey;

	public List<HolidayDto> getHolidays(int year, int month) throws Exception {
		String targetUrl = apiUrl + "?ServiceKey=" + apiKey + "&solYear=" + year + "&solMonth="
				+ String.format("%02d", month);

		URI uri = new URI(targetUrl); // 문자열을 URI로 파싱
		URL url = uri.toURL();        // URI를 URL로 변환
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(conn.getInputStream());

		NodeList items = doc.getElementsByTagName("item");
		List<HolidayDto> list = new ArrayList<>();

		for (int i = 0; i < items.getLength(); i++) {
			Element item = (Element) items.item(i);
			HolidayDto dto = new HolidayDto();
			
			dto.setName(getTagValue(item, "dateName")); 
		    dto.setDateString(getTagValue(item, "locdate"));
		    dto.setWeekdayKo(getTagValue(item, "week")); 
		    
		    list.add(dto);
		}

		return list;
	}

	private String getTagValue(Element e, String tagName) {
		NodeList list = e.getElementsByTagName(tagName);
		if (list.getLength() == 0)
			return null;
		return list.item(0).getTextContent();
	}
}
