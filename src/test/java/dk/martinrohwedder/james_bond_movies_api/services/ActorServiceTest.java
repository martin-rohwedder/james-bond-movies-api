package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.ActorWithMovieResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Actor;
import dk.martinrohwedder.james_bond_movies_api.mappers.ActorMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private ActorMapper actorMapper;

    private ActorService actorService;

    private Actor createActorEntity() {
        return Actor.builder()
                .name("Sean Connery")
                .characterRole("James Bond")
                .build();
    }

    private ActorWithMovieResponseDto createActorDto() {
        return new ActorWithMovieResponseDto(
                UUID.fromString("11111111-1111-1111-1111-111111111111"),
                "Sean Connery",
                "James Bond",
                null,
                "British",
                LocalDate.of(1930, 8, 25),
                null,
                List.of()
        );
    }

    @BeforeEach
    void setUp() {
        actorService = new ActorService(actorRepository, actorMapper);
    }

    @Test
    void should_return_actor_by_id() {
        // Arrange
        UUID id = UUID.randomUUID();
        Actor actor = createActorEntity();
        ActorWithMovieResponseDto dto = createActorDto();

        when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
        when(actorMapper.actorToActorWithMovieResponseDto(actor)).thenReturn(dto);

        // Act
        Optional<ActorWithMovieResponseDto> result = actorService.getActorById(id);

        // Assert
        assertThat(result).contains(dto);

        verify(actorRepository).findById(id);
        verify(actorMapper).actorToActorWithMovieResponseDto(actor);
        verifyNoMoreInteractions(actorRepository, actorMapper);
    }

    @Test
    void should_return_empty_optional_when_actor_is_not_found() {
        // Arrange
        UUID id = UUID.randomUUID();

        when(actorRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<ActorWithMovieResponseDto> result = actorService.getActorById(id);

        // Assert
        assertThat(result).isEmpty();

        verify(actorRepository).findById(id);
        verifyNoInteractions(actorMapper);
    }

    @Test
    void should_return_all_actors_with_movies() {
        // Arrange
        Actor actor1 = createActorEntity();
        Actor actor2 = createActorEntity();

        ActorWithMovieResponseDto dto1 = createActorDto();
        ActorWithMovieResponseDto dto2 = createActorDto();

        when(actorRepository.findAllByOrderByNameAsc())
                .thenReturn(List.of(actor1, actor2));

        when(actorMapper.actorToActorWithMovieResponseDto(actor1, true)).thenReturn(dto1);
        when(actorMapper.actorToActorWithMovieResponseDto(actor2, true)).thenReturn(dto2);

        // Act
        List<ActorWithMovieResponseDto> result =
                actorService.getAllActors(null, true);

        // Assert
        assertThat(result).containsExactly(dto1, dto2);

        verify(actorRepository).findAllByOrderByNameAsc();
        verify(actorMapper).actorToActorWithMovieResponseDto(actor1, true);
        verify(actorMapper).actorToActorWithMovieResponseDto(actor2, true);
        verifyNoMoreInteractions(actorRepository, actorMapper);
    }

    @Test
    void should_return_all_actors_without_movies() {
        // Arrange
        Actor actor = createActorEntity();
        ActorWithMovieResponseDto dto = createActorDto();

        when(actorRepository.findAllByOrderByNameAsc())
                .thenReturn(List.of(actor));

        when(actorMapper.actorToActorWithMovieResponseDto(actor, false))
                .thenReturn(dto);

        // Act
        List<ActorWithMovieResponseDto> result =
                actorService.getAllActors(null, false);

        // Assert
        assertThat(result).containsExactly(dto);

        verify(actorRepository).findAllByOrderByNameAsc();
        verify(actorMapper).actorToActorWithMovieResponseDto(actor, false);
        verifyNoMoreInteractions(actorRepository, actorMapper);
    }

    @Test
    void should_return_actors_by_name_with_movies() {
        // Arrange
        Actor actor = createActorEntity();
        ActorWithMovieResponseDto dto = createActorDto();

        when(actorRepository.findAllByNameIgnoreCaseOrderByNameAsc("Sean Connery"))
                .thenReturn(List.of(actor));

        when(actorMapper.actorToActorWithMovieResponseDto(actor, true))
                .thenReturn(dto);

        // Act
        List<ActorWithMovieResponseDto> result =
                actorService.getAllActors("Sean Connery", true);

        // Assert
        assertThat(result).containsExactly(dto);

        verify(actorRepository).findAllByNameIgnoreCaseOrderByNameAsc("Sean Connery");
        verify(actorMapper).actorToActorWithMovieResponseDto(actor, true);
        verifyNoMoreInteractions(actorRepository, actorMapper);
    }

    @Test
    void should_return_actors_by_name_without_movies() {
        // Arrange
        Actor actor = createActorEntity();
        ActorWithMovieResponseDto dto = createActorDto();

        when(actorRepository.findAllByNameIgnoreCaseOrderByNameAsc("Sean Connery"))
                .thenReturn(List.of(actor));

        when(actorMapper.actorToActorWithMovieResponseDto(actor, false))
                .thenReturn(dto);

        // Act
        List<ActorWithMovieResponseDto> result =
                actorService.getAllActors("Sean Connery", false);

        // Assert
        assertThat(result).containsExactly(dto);

        verify(actorRepository).findAllByNameIgnoreCaseOrderByNameAsc("Sean Connery");
        verify(actorMapper).actorToActorWithMovieResponseDto(actor, false);
        verifyNoMoreInteractions(actorRepository, actorMapper);
    }

    @Test
    void should_return_all_actors_when_name_is_blank() {
        // Arrange
        Actor actor = createActorEntity();
        ActorWithMovieResponseDto dto = createActorDto();

        when(actorRepository.findAllByOrderByNameAsc())
                .thenReturn(List.of(actor));

        when(actorMapper.actorToActorWithMovieResponseDto(actor, true))
                .thenReturn(dto);

        // Act
        List<ActorWithMovieResponseDto> result =
                actorService.getAllActors("   ", true);

        // Assert
        assertThat(result).containsExactly(dto);

        verify(actorRepository).findAllByOrderByNameAsc();
        verify(actorMapper).actorToActorWithMovieResponseDto(actor, true);
        verifyNoMoreInteractions(actorRepository, actorMapper);
    }

    @Test
    void should_return_all_actors_when_name_is_empty() {
        Actor actor = createActorEntity();
        ActorWithMovieResponseDto dto = createActorDto();

        when(actorRepository.findAllByOrderByNameAsc())
                .thenReturn(List.of(actor));

        when(actorMapper.actorToActorWithMovieResponseDto(actor, true))
                .thenReturn(dto);

        List<ActorWithMovieResponseDto> result =
                actorService.getAllActors("", true);

        assertThat(result).containsExactly(dto);

        verify(actorRepository).findAllByOrderByNameAsc();
        verify(actorMapper).actorToActorWithMovieResponseDto(actor, true);
        verifyNoMoreInteractions(actorRepository, actorMapper);
    }

    @Test
    void should_return_empty_list_when_no_matching_actor_exists() {
        // Arrange
        when(actorRepository.findAllByNameIgnoreCaseOrderByNameAsc("Unknown Actor"))
                .thenReturn(Collections.emptyList());

        // Act
        List<ActorWithMovieResponseDto> result =
                actorService.getAllActors("Unknown Actor", true);

        // Assert
        assertThat(result).isEmpty();

        verify(actorRepository).findAllByNameIgnoreCaseOrderByNameAsc("Unknown Actor");
        verifyNoInteractions(actorMapper);
    }

    @Test
    void should_return_empty_list_when_no_actors_exist() {
        // Arrange
        when(actorRepository.findAllByOrderByNameAsc())
                .thenReturn(Collections.emptyList());

        // Act
        List<ActorWithMovieResponseDto> result =
                actorService.getAllActors(null, true);

        // Assert
        assertThat(result).isEmpty();

        verify(actorRepository).findAllByOrderByNameAsc();
        verifyNoInteractions(actorMapper);
    }

    @Test
    void should_forward_include_movies_flag_to_mapper() {
        Actor actor = createActorEntity();
        ActorWithMovieResponseDto dto = createActorDto();

        when(actorRepository.findAllByOrderByNameAsc())
                .thenReturn(List.of(actor));

        when(actorMapper.actorToActorWithMovieResponseDto(actor, false))
                .thenReturn(dto);

        actorService.getAllActors(null, false);

        verify(actorMapper).actorToActorWithMovieResponseDto(actor, false);
    }
}
