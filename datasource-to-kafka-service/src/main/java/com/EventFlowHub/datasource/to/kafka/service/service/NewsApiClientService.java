package com.EventFlowHub.datasource.to.kafka.service.service;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NewsApiClientService {

	private static final Logger LOG = LoggerFactory.getLogger(NewsApiClientService.class);

	private final String apiKey;
	private final String keywords;
	private final String lang;
	private final int pageSize;
	private final NewsApiClient newsApiClient;

	public NewsApiClientService(@Value("${news.api.apiKey}") String apiKey,
			@Value("${news.api.keywords}") String keywords, @Value("${news.api.lang}") String lang, @Value("${news.api.pageSize}") int pageSize) {
		this.apiKey = apiKey;
		this.keywords = keywords;
		this.lang = lang;
		this.pageSize = pageSize;
		this.newsApiClient = new NewsApiClient(this.apiKey);
	}

	//fetch news every 20 minutes
	@Scheduled(fixedRate = 1200000)
	public void fetchDataFromStream() {
		LOG.info("Fetching data from the News API...");
		// Make a GET request to the News API
		newsApiClient.getEverything(
				new EverythingRequest.Builder()
						.q(keywords)
						.language(lang)
						.pageSize(pageSize)
						.from(DateTimeFormatter.ISO_DATE_TIME.format(ZonedDateTime.now().minusMinutes(20)))
						.to(DateTimeFormatter.ISO_DATE_TIME.format(ZonedDateTime.now())).build()
				, new NewsApiClient.ArticlesResponseCallback() {
					@Override
					public void onSuccess(ArticleResponse response) {
						LOG.info("fetched articles from news API successfully!");
						response.getArticles().forEach(article -> LOG.info(article.getTitle()));
						//call kafka producer here
					}

					@Override
					public void onFailure(Throwable throwable) {
						LOG.error("Error while calling news API : {}", throwable.getMessage());
					}
				});
	}
}

