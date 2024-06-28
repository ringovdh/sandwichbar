package be.faros.sandwichbar.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class BasicRepository {

    protected final NamedParameterJdbcTemplate template;

    public BasicRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }
}
