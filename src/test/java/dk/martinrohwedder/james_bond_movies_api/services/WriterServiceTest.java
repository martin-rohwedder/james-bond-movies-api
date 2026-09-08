package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.WriterWithMovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Writer;
import dk.martinrohwedder.james_bond_movies_api.mappers.WriterMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.WriterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WriterServiceTest {
    @Mock
    private WriterRepository writerRepository;

    @Mock
    private WriterMapper writerMapper;

    private WriterService writerService;

    private Writer createWriterEntity(String name) {
        return Writer.builder()
                .name(name)
                .build();
    }

    private WriterWithMovieResponseDto createWriterDto(String name) {
        return new WriterWithMovieResponseDto(
                UUID.fromString("11111111-1111-1111-1111-111111111111"),
                name,
                LocalDate.of(1908, 5, 28),
                LocalDate.of(1964, 8, 12),
                List.of()
        );
    }

    @BeforeEach
    void setUp() {
        writerService = new WriterService(writerRepository, writerMapper);
    }

    @Test
    void should_return_writer_by_id() {
        // Arrange
        UUID id = UUID.randomUUID();
        Writer writer = createWriterEntity("Ian Fleming");
        WriterWithMovieResponseDto dto = createWriterDto("Ian Fleming");

        when(writerRepository.findById(id)).thenReturn(Optional.of(writer));
        when(writerMapper.writerToWriterWithMovieResponseDto(writer)).thenReturn(dto);

        // Act
        Optional<WriterWithMovieResponseDto> result = writerService.getWriterById(id);

        // Assert
        assertThat(result).contains(dto);

        verify(writerRepository).findById(id);
        verify(writerMapper).writerToWriterWithMovieResponseDto(writer);
        verifyNoMoreInteractions(writerRepository, writerMapper);
    }

    @Test
    void should_return_empty_optional_when_writer_is_not_found() {
        // Arrange
        UUID id = UUID.randomUUID();

        when(writerRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<WriterWithMovieResponseDto> result = writerService.getWriterById(id);

        // Assert
        assertThat(result).isEmpty();

        verify(writerRepository).findById(id);
        verifyNoInteractions(writerMapper);
    }

    @Test
    void should_return_all_writers_with_movies() {
        // Arrange
        Writer writer1 = createWriterEntity("Ian Fleming");
        Writer writer2 = createWriterEntity("Richard Maibaum");

        WriterWithMovieResponseDto dto1 = createWriterDto("Ian Fleming");
        WriterWithMovieResponseDto dto2 = createWriterDto("Richard Maibaum");

        when(writerRepository.findAllByOrderByNameAsc()).thenReturn(List.of(writer1, writer2));

        when(writerMapper.writerToWriterWithMovieResponseDto(writer1, true)).thenReturn(dto1);
        when(writerMapper.writerToWriterWithMovieResponseDto(writer2, true)).thenReturn(dto2);

        // Act
        List<WriterWithMovieResponseDto> result = writerService.getAllWriters(null, true);

        // Assert
        assertThat(result).containsExactly(dto1, dto2);

        verify(writerRepository).findAllByOrderByNameAsc();
        verify(writerMapper).writerToWriterWithMovieResponseDto(writer1, true);
        verify(writerMapper).writerToWriterWithMovieResponseDto(writer2, true);
        verifyNoMoreInteractions(writerRepository, writerMapper);
    }

    @Test
    void should_return_all_writers_without_movies() {
        // Arrange
        Writer writer = createWriterEntity("Ian Fleming");
        WriterWithMovieResponseDto dto = createWriterDto("Ian Fleming");

        when(writerRepository.findAllByOrderByNameAsc()).thenReturn(List.of(writer));

        when(writerMapper.writerToWriterWithMovieResponseDto(writer, false)).thenReturn(dto);

        // Act
        List<WriterWithMovieResponseDto> result = writerService.getAllWriters(null, false);

        // Assert
        assertThat(result).containsExactly(dto);

        verify(writerRepository).findAllByOrderByNameAsc();
        verify(writerMapper).writerToWriterWithMovieResponseDto(writer, false);
        verifyNoMoreInteractions(writerRepository, writerMapper);
    }

    @Test
    void should_return_writers_by_name_with_movies() {
        // Arrange
        Writer writer = createWriterEntity("Ian Fleming");
        WriterWithMovieResponseDto dto = createWriterDto("Ian Fleming");

        when(writerRepository.findAllByNameIgnoreCaseOrderByNameAsc("Ian Fleming")).thenReturn(List.of(writer));

        when(writerMapper.writerToWriterWithMovieResponseDto(writer, true)).thenReturn(dto);

        // Act
        List<WriterWithMovieResponseDto> result = writerService.getAllWriters("Ian Fleming", true);

        // Assert
        assertThat(result).containsExactly(dto);

        verify(writerRepository).findAllByNameIgnoreCaseOrderByNameAsc("Ian Fleming");
        verify(writerMapper).writerToWriterWithMovieResponseDto(writer, true);
        verifyNoMoreInteractions(writerRepository, writerMapper);
    }

    @Test
    void should_return_writers_by_name_without_movies() {
        // Arrange
        Writer writer = createWriterEntity("Ian Fleming");
        WriterWithMovieResponseDto dto = createWriterDto("Ian Fleming");

        when(writerRepository.findAllByNameIgnoreCaseOrderByNameAsc("Ian Fleming")).thenReturn(List.of(writer));

        when(writerMapper.writerToWriterWithMovieResponseDto(writer, false)).thenReturn(dto);

        // Act
        List<WriterWithMovieResponseDto> result = writerService.getAllWriters("Ian Fleming", false);

        // Assert
        assertThat(result).containsExactly(dto);

        verify(writerRepository).findAllByNameIgnoreCaseOrderByNameAsc("Ian Fleming");
        verify(writerMapper).writerToWriterWithMovieResponseDto(writer, false);
        verifyNoMoreInteractions(writerRepository, writerMapper);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void should_return_all_writers_when_name_is_blank_or_null(String name) {
        // Arrange
        Writer writer = createWriterEntity("Ian Fleming");
        WriterWithMovieResponseDto dto = createWriterDto("Ian Fleming");

        when(writerRepository.findAllByOrderByNameAsc()).thenReturn(List.of(writer));

        when(writerMapper.writerToWriterWithMovieResponseDto(writer, true)).thenReturn(dto);

        // Act
        List<WriterWithMovieResponseDto> result = writerService.getAllWriters(name, true);

        // Assert
        assertThat(result).containsExactly(dto);

        verify(writerRepository).findAllByOrderByNameAsc();
        verify(writerMapper).writerToWriterWithMovieResponseDto(writer, true);
        verifyNoMoreInteractions(writerRepository, writerMapper);
    }

    @Test
    void should_return_empty_list_when_no_matching_writer_exists() {
        // Arrange
        when(writerRepository.findAllByNameIgnoreCaseOrderByNameAsc("Unknown Writer")).thenReturn(List.of());

        // Act
        List<WriterWithMovieResponseDto> result = writerService.getAllWriters("Unknown Writer", true);

        // Assert
        assertThat(result).isEmpty();

        verify(writerRepository).findAllByNameIgnoreCaseOrderByNameAsc("Unknown Writer");
        verifyNoInteractions(writerMapper);
    }

    @Test
    void should_return_empty_list_when_no_matching_writers_exists() {
        // Arrange
        when(writerRepository.findAllByOrderByNameAsc()).thenReturn(List.of());

        // Act
        List<WriterWithMovieResponseDto> result = writerService.getAllWriters(null, true);

        // Assert
        assertThat(result).isEmpty();

        verify(writerRepository).findAllByOrderByNameAsc();
        verifyNoInteractions(writerMapper);
    }

    @Test
    void should_forward_include_movies_flag_to_mapper() {
        // Arrange
        Writer writer = createWriterEntity("Ian Fleming");
        WriterWithMovieResponseDto dto = createWriterDto("Ian Fleming");

        when(writerRepository.findAllByOrderByNameAsc()).thenReturn(List.of(writer));

        when(writerMapper.writerToWriterWithMovieResponseDto(writer, false)).thenReturn(dto);

        // Act
        writerService.getAllWriters(null, false);

        // Assert
        verify(writerMapper).writerToWriterWithMovieResponseDto(writer, false);
    }
}
