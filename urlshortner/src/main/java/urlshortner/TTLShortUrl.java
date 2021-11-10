package urlshortner;

import java.util.Date;

public class TTLShortUrl {
	private String originalUrl;
	private String shortenedUrl;
	private int timeToLive;
	private Date date;

	public TTLShortUrl(String originalUrl, String shortenedUrl, int timeToLive, Date date) {
		this.setShortenedUrl(shortenedUrl);
		this.setTimeToLive(timeToLive);
		this.setOriginalUrl(originalUrl);
		this.setDate(date);
	}

	public boolean isValid() {
		Date timeNow = new Date();
		Date expireDate = new Date(this.getDate().getTime() + getTimeToLive());
		return timeNow.before(expireDate);
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortenedUrl() {
		return shortenedUrl;
	}

	public void setShortenedUrl(String shortenedUrl) {
		this.shortenedUrl = shortenedUrl;
	}

	public int getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
