import java.util.Map;

import org.apache.commons.collections4.MultiMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShortenerTest {

    private Shortener shortener;
    private MultiMap<String, String> mapShortUrl;
    private final String shortUrl = "http://short.com/3434";

    @Before
    public void init(){
        this.shortener = new Shortener();
    	for (int i = 0; i < 3; i++) {
    		shortener.createRandomShortUrl(shortUrl);
		}
    }

    @Test
    public void testCreateShortener()
    {
       String shortUrl = "http://short.com/MY-NEW-WS";
       Assert.assertEquals(shortUrl,shortener.createShortener(null,"MY-NEW-WS"));

    }

    @Test
    public void testCreateRandomShortUrl() {
        String firstPartUrlToCompare = "http://short.com/";
        String fullUrlToCompare = "http://short.com/As34";
        String inputParamUrl = "http://some.domain.com/somepath/webService";
        
        String result = shortener.createRandomShortUrl(inputParamUrl);
        Assert.assertEquals(true, result.startsWith(firstPartUrlToCompare));
        Assert.assertEquals(fullUrlToCompare.length(), result.length());

    }

    @Test
    public void testRetrieveOriginalUrl(){
        String inputParamUrl = "http://some.domain.com/somepath/webService2";
        String result = shortener.createRandomShortUrl(inputParamUrl);
        Assert.assertEquals(inputParamUrl, shortener.retrieveOriginalUrl(result));
    }
    
    @Test
	public void testCreateIncrementalRandomShortUrl() {
    	int i;
		String fullUrlToCompare = "http://some.domain.com/somepath/webService";
		for (i= 0; i < 3; i++) {
			String shortUrl = shortener.generateIntIncrementalRandom(fullUrlToCompare);
			Assert.assertEquals(true, shortUrl.endsWith(""+i));
		}
	}
    
    
    @Test
    public void testRetrieveOriginalUrl_NotValidate(){
        String inputParamUrl = "http://some.domain.com/somepath/webService4";
        String shortUrl = shortener.createRandomShortUrl(inputParamUrl);
        String shortUrlDeleted = shortener.notRetrieveOriginalUrl(shortUrl);
        Assert.assertNull("URL NOT VALID", shortener.retrieveOriginalUrl(shortUrlDeleted));
    }
    
    
    @Test
    public void testRetrieveOriginalUrl_invalidated(){
    	String inputParamUrl = "http://some.domain.com/somepath/webService2";
        String shortUrl = shortener.createRandomShortUrl(inputParamUrl);
        Map<String,String> invalidTTLShortUrl = shortener.invalidTTLShortUrl(shortUrl);
        Assert.assertNull("INPUT INVALIDATED", invalidTTLShortUrl.get(shortUrl));
    }
    
    
    @Test
    public void testCountShortUrl() {
    	String shortUrl = "http://short.com/3434";
    	int count = 0;
        count = shortener.countShortUrl(shortUrl);
        Assert.assertEquals(3, count);
    }

}
