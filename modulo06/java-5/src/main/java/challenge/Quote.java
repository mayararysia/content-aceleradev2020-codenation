package challenge;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "scripts")
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotEmpty
	@Column
	private String actor;

	@NotEmpty
	@Column(name = "detail")
	private String quote;

	public Quote() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}
}
