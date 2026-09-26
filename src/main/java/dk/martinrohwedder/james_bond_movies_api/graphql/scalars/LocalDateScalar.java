package dk.martinrohwedder.james_bond_movies_api.graphql.scalars;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class LocalDateScalar {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private LocalDateScalar() {
    }

    public static GraphQLScalarType create() {
        return GraphQLScalarType.newScalar()
                .name("Date")
                .description("A date without time or timezone information")
                .coercing(new Coercing<LocalDate, String>() {

                    @Override
                    public String serialize(Object input) throws CoercingSerializeException {
                        if (input instanceof LocalDate date) {
                            return FORMATTER.format(date);
                        }

                        throw new CoercingSerializeException("Expected LocalDate but was " + input.getClass().getSimpleName());
                    }

                    @Override
                    public LocalDate parseValue(Object input) throws CoercingParseValueException {
                        if (input instanceof String value) {
                            try {
                                return LocalDate.parse(value, FORMATTER);
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseValueException("Invalid Date value: " + value);
                            }
                        }

                        throw new CoercingParseValueException("Expected a String value");
                    }

                    @Override
                    public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
                        if (input instanceof StringValue stringValue) {
                            try {
                                return LocalDate.parse(stringValue.getValue(), FORMATTER);
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException("Invalid Date value: " + stringValue.getValue());
                            }
                        }

                        throw new CoercingParseLiteralException("Expected a String literal");
                    }
                })
                .build();
    }
}