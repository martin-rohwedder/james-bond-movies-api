package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.ProducerWithMoviesResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Producer;
import dk.martinrohwedder.james_bond_movies_api.mappers.ProducerMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.ProducerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProducerServiceTest {

    @Mock
    private ProducerRepository producerRepository;

    @Mock
    private ProducerMapper producerMapper;

    private ProducerService producerService;

    private Producer createProducerEntity(String name) {
        return Producer.builder()
                .name(name)
                .nationality("British")
                .build();
    }

    private ProducerWithMoviesResponseDto createProducerDto(String name) {
        return new ProducerWithMoviesResponseDto(
                UUID.randomUUID(),
                name,
                null,
                "British",
                LocalDate.of(1920, 1, 1),
                null,
                List.of()
        );
    }

    @BeforeEach
    void setUp() {
        producerService = new ProducerService(producerRepository, producerMapper);
    }

    @Test
    void should_return_producer_by_id() {
        UUID id = UUID.randomUUID();
        Producer producer = createProducerEntity("Albert R. Broccoli");
        ProducerWithMoviesResponseDto dto = createProducerDto("Albert R. Broccoli");

        when(producerRepository.findById(id)).thenReturn(Optional.of(producer));
        when(producerMapper.producerToProducerWithMoviesResponseDto(producer))
                .thenReturn(dto);

        Optional<ProducerWithMoviesResponseDto> result =
                producerService.getProducerById(id);

        assertThat(result).contains(dto);

        verify(producerRepository).findById(id);
        verify(producerMapper).producerToProducerWithMoviesResponseDto(producer);
        verifyNoMoreInteractions(producerRepository, producerMapper);
    }

    @Test
    void should_return_empty_optional_when_producer_is_not_found() {
        UUID id = UUID.randomUUID();

        when(producerRepository.findById(id)).thenReturn(Optional.empty());

        Optional<ProducerWithMoviesResponseDto> result =
                producerService.getProducerById(id);

        assertThat(result).isEmpty();

        verify(producerRepository).findById(id);
        verifyNoInteractions(producerMapper);
    }

    @Test
    void should_return_all_producers() {
        Producer producer1 = createProducerEntity("Albert R. Broccoli");
        Producer producer2 = createProducerEntity("Harry Saltzman");

        ProducerWithMoviesResponseDto dto1 = createProducerDto("Albert R. Broccoli");
        ProducerWithMoviesResponseDto dto2 = createProducerDto("Harry Saltzman");

        when(producerRepository.findAllByOrderByNameAsc())
                .thenReturn(List.of(producer1, producer2));

        when(producerMapper.producerToProducerWithMoviesResponseDto(producer1))
                .thenReturn(dto1);
        when(producerMapper.producerToProducerWithMoviesResponseDto(producer2))
                .thenReturn(dto2);

        List<ProducerWithMoviesResponseDto> result =
                producerService.getAllProducers(null);

        assertThat(result).containsExactly(dto1, dto2);

        verify(producerRepository).findAllByOrderByNameAsc();
        verify(producerMapper).producerToProducerWithMoviesResponseDto(producer1);
        verify(producerMapper).producerToProducerWithMoviesResponseDto(producer2);
        verifyNoMoreInteractions(producerRepository, producerMapper);
    }

    @Test
    void should_return_producers_by_name() {
        Producer producer = createProducerEntity("Albert R. Broccoli");
        ProducerWithMoviesResponseDto dto = createProducerDto("Albert R. Broccoli");

        when(producerRepository.findAllByNameIgnoreCaseOrderByNameAsc("Albert R. Broccoli"))
                .thenReturn(List.of(producer));

        when(producerMapper.producerToProducerWithMoviesResponseDto(producer))
                .thenReturn(dto);

        List<ProducerWithMoviesResponseDto> result =
                producerService.getAllProducers("Albert R. Broccoli");

        assertThat(result).containsExactly(dto);

        verify(producerRepository)
                .findAllByNameIgnoreCaseOrderByNameAsc("Albert R. Broccoli");
        verify(producerMapper).producerToProducerWithMoviesResponseDto(producer);
        verifyNoMoreInteractions(producerRepository, producerMapper);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void should_return_all_producers_when_name_is_blank_or_null(String name) {
        Producer producer = createProducerEntity("Albert R. Broccoli");
        ProducerWithMoviesResponseDto dto = createProducerDto("Albert R. Broccoli");

        when(producerRepository.findAllByOrderByNameAsc())
                .thenReturn(List.of(producer));

        when(producerMapper.producerToProducerWithMoviesResponseDto(producer))
                .thenReturn(dto);

        List<ProducerWithMoviesResponseDto> result =
                producerService.getAllProducers(name);

        assertThat(result).containsExactly(dto);

        verify(producerRepository).findAllByOrderByNameAsc();
        verify(producerMapper).producerToProducerWithMoviesResponseDto(producer);
        verifyNoMoreInteractions(producerRepository, producerMapper);
    }

    @Test
    void should_return_empty_list_when_no_matching_producer_exists() {
        when(producerRepository.findAllByNameIgnoreCaseOrderByNameAsc("Unknown Producer"))
                .thenReturn(Collections.emptyList());

        List<ProducerWithMoviesResponseDto> result =
                producerService.getAllProducers("Unknown Producer");

        assertThat(result).isEmpty();

        verify(producerRepository)
                .findAllByNameIgnoreCaseOrderByNameAsc("Unknown Producer");
        verifyNoInteractions(producerMapper);
    }

    @Test
    void should_return_empty_list_when_no_producers_exist() {
        when(producerRepository.findAllByOrderByNameAsc())
                .thenReturn(Collections.emptyList());

        List<ProducerWithMoviesResponseDto> result =
                producerService.getAllProducers(null);

        assertThat(result).isEmpty();

        verify(producerRepository).findAllByOrderByNameAsc();
        verifyNoInteractions(producerMapper);
    }
}