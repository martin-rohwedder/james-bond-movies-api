package dk.martinrohwedder.james_bond_movies_api.graphql.scalars;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class LocalDateTimeScalar {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private LocalDateTimeScalar() {
    }

    public static GraphQLScalarType create() {
        return GraphQLScalarType.newScalar()
                .name("DateTime")
                .description("Local date and time without timezone information")
                .coercing(new Coercing<LocalDateTime, String>() {

                    @Override
                    public String serialize(Object input) throws CoercingSerializeException {
                        if (input instanceof LocalDateTime dateTime) {
                            return FORMATTER.format(dateTime);
                        }

                        throw new CoercingSerializeException("Expected LocalDateTime but was " + input.getClass().getSimpleName());
                    }

                    @Override
                    public LocalDateTime parseValue(Object input) throws CoercingParseValueException {
                        if (input instanceof String value) {
                            try {
                                return LocalDateTime.parse(value, FORMATTER);
                            } catch (Exception e) {
                                throw new CoercingParseValueException("Invalid DateTime: " + value);
                            }
                        }

                        throw new CoercingParseValueException("Expected a String");
                    }

                    @Override
                    public LocalDateTime parseLiteral(Object input) throws CoercingParseLiteralException {
                        if (input instanceof StringValue stringValue) {
                            try {
                                return LocalDateTime.parse(stringValue.getValue(), FORMATTER);
                            } catch (Exception e) {
                                throw new CoercingParseLiteralException("Invalid DateTime: " + stringValue.getValue());
                            }
                        }

                        throw new CoercingParseLiteralException("Expected a String literal");
                    }
                })
                .build();
    }
}
