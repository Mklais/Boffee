package com.klaisapp.bookclub.service.messageservice;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class LiteraryQuotesGenerator {

    private static final List<String> quotes = Arrays.asList(
            "It is a far, far better thing that I do, than I have ever done; it is a far, far better rest I go to than I have ever known.",
            "It matters not what someone is born, but what they grow to be.",
            "It does not do to dwell on dreams and forget to live."
    );

    public String getRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.size());
        return quotes.get(index);
    }
}
