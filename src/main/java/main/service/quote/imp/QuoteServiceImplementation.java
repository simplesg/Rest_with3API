package main.service.quote.imp;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import main.dto.quote.Quote;
import main.service.quote.QuoteService;

import static main.util.PageUri.QUOTE_URL;

@Service
@RequiredArgsConstructor
public class QuoteServiceImplementation implements QuoteService {

    private final RestTemplate restTemplate;

    @Override
    public Quote getRandomQuote() {
        return restTemplate.getForObject(
            QUOTE_URL, Quote.class);
    }
}
