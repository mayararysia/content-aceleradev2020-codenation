package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private QuoteRepository repository;

	@Override
	public Quote getQuote() {
		return getQuoteOfTheList(this.repository.findAll());
	}


	@Override
	public Quote getQuoteByActor(String actor) {
		return Optional.ofNullable(actor).isPresent() && !actor.isEmpty()
				? getQuoteOfTheList(this.repository.findByActor(actor)) : new Quote();
	}

	private Quote getQuoteOfTheList(List<Quote> list) {
		return Optional.ofNullable(list).isPresent() && list.size() > 0
				? list.get(new Random().nextInt(list.size()))
				: new Quote();
	}

}
