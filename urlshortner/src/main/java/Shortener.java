import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import urlshortner.TTLShortUrl;

public class Shortener {

	private Map<String, String> urlList = new HashMap<>();
	private static int counter;
	private final int timeToLive = 1; // 86400; one day

	// 1 - Shorten URL "SEO"
	public String createShortener(String url, String seoKeyword) {
		return IConstants.URL_SHORT + seoKeyword;
	}

	// 2 - Random URL shortening
	public String createRandomShortUrl(String url) {
		String randomString = UUID.randomUUID().toString();
		String shortString = randomString.replaceAll("-", "");
		String shortUrl = IConstants.URL_SHORT + shortString.substring(0, 4);
		urlList.put(shortUrl, url);
		return shortUrl;
	}

	
	// 3 - Get original URL
	public String retrieveOriginalUrl(String shortUrl) {
		return urlList.get(shortUrl);
	}
	

	// 4 - Different automatic string ?????
	public String generateIntIncrementalRandom(String url) {
		urlList.clear();
		String shortUrl = IConstants.URL_SHORT + counter;
		counter = counter +1;
		urlList.put(shortUrl, url);
		
		return shortUrl;
	}


	
	//5 - Invalidate Short URL
	public String notRetrieveOriginalUrl(String shortUrl) {
		if (urlList.get(shortUrl) != null)
			urlList.remove(shortUrl);
		return urlList.get(shortUrl);
	}
	

	// 6 - Time To Live (TTL) URL
	public Map invalidTTLShortUrl(String shortUrl) {
		TTLShortUrl shortUrlWithTTL = null;
		Date date = new Date();

		String originalUrl = retrieveOriginalUrl(shortUrl);
		shortUrlWithTTL = new TTLShortUrl(originalUrl, shortUrl, timeToLive, date);
		if (!shortUrlWithTTL.isValid()) {
			urlList.remove(shortUrl);
		}
		return urlList;
	}
	

	// 7 - URL Statistics
	public int countShortUrl(String shortUrl) {
		int count = 0;
		 for (String value : urlList.values()) {
       	  if (value == shortUrl)
			count += 1;
       	  }
		return count;
	}

}
