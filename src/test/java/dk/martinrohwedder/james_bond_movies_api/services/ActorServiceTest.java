package dk.martinrohwedder.james_bond_movies_api.services;

import dk.martinrohwedder.james_bond_movies_api.dtos.ActorResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Actor;
import dk.martinrohwedder.james_bond_movies_api.mappers.ActorMapper;
import dk.martinrohwedder.james_bond_movies_api.repositories.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActorServiceTest {
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

    @BeforeEach
    void setUp() {
        actorService = new ActorService(actorRepository, actorMapper);
    }

    @Test
    void should_return_actor_by_id() {
        // Arrange
        UUID id = UUID.randomUUID();
        Actor actor = createActorEntity();
        ActorResponseDto dto = mock(ActorResponseDto.class);

        when(actorRepository.findById(id))
                .thenReturn(Optional.of(actor));

        when(actorMapper.actorToActorResponseDto(actor))
                .thenReturn(dto);

        // Act
        Optional<ActorResponseDto> result = actorService.getActorById(id);

        // Assert
        assertThat(result).contains(dto);

        verify(actorRepository).findById(id);
        verify(actorMapper).actorToActorResponseDto(actor);
        verifyNoMoreInteractions(actorRepository, actorMapper);
    }

    @Test
    void should_return_empty_optional_when_actor_is_not_found() {
        // Arrange
        UUID id = UUID.randomUUID();

        when(actorRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act
        Optional<ActorResponseDto> result = actorService.getActorById(id);

        // Assert
        assertThat(result).isEmpty();

        verify(actorRepository).findById(id);
        verifyNoInteractions(actorMapper);
    }

    @Test
    void should_return_all_actors() {
        // Arrange
        Actor actor1 = createActorEntity();
        Actor actor2 = createActorEntity();
        ActorResponseDto dto1 = mock(ActorResponseDto.class);
        ActorResponseDto dto2 = mock(ActorResponseDto.class);

        when(actorRepository.findAll())
                .thenReturn(List.of(actor1, actor2));

        when(actorMapper.actorToActorResponseDto(actor1)).thenReturn(dto1);
        when(actorMapper.actorToActorResponseDto(actor2)).thenReturn(dto2);

        // Act
        List<ActorResponseDto> result = actorService.getAllActors();

        // Assert
        assertThat(result).containsExactly(dto1, dto2);

        verify(actorRepository).findAll();
        verify(actorMapper).actorToActorResponseDto(actor1);
        verify(actorMapper).actorToActorResponseDto(actor2);
        verifyNoMoreInteractions(actorRepository, actorMapper);
    }

    @Test
    void should_return_multiple_actors_with_same_name() {
        Actor actor1 = createActorEntity();
        Actor actor2 = createActorEntity();

        ActorResponseDto dto1 = mock(ActorResponseDto.class);
        ActorResponseDto dto2 = mock(ActorResponseDto.class);

        when(actorRepository.findAllByNameIgnoreCase("Sean Connery"))
                .thenReturn(List.of(actor1, actor2));

        when(actorMapper.actorToActorResponseDto(actor1)).thenReturn(dto1);
        when(actorMapper.actorToActorResponseDto(actor2)).thenReturn(dto2);

        List<ActorResponseDto> result =
                actorService.getActorByName("Sean Connery");

        assertThat(result).containsExactly(dto1, dto2);

        verify(actorRepository).findAllByNameIgnoreCase("Sean Connery");
        verify(actorMapper).actorToActorResponseDto(actor1);
        verify(actorMapper).actorToActorResponseDto(actor2);
        verifyNoMoreInteractions(actorRepository, actorMapper);
    }

    @Test
    void should_return_actors_by_name() {
        // Arrange
        Actor actor = createActorEntity();
        String actorName = "Sean Connery";
        ActorResponseDto dto = mock(ActorResponseDto.class);

        when(actorRepository.findAllByNameIgnoreCase(actorName))
                .thenReturn(List.of(actor));

        when(actorMapper.actorToActorResponseDto(actor))
                .thenReturn(dto);

        // Act
        List<ActorResponseDto> result = actorService.getActorByName(actorName);

        // Assert
        assertThat(result).containsExactly(dto);

        verify(actorRepository).findAllByNameIgnoreCase(actorName);
        verify(actorMapper).actorToActorResponseDto(actor);
        verifyNoMoreInteractions(actorRepository, actorMapper);
    }

    @Test
    void should_return_empty_list_when_providing_wrong_actor_name() {
        // Arrange
        String actorName = "Wrong actor name";

        when(actorRepository.findAllByNameIgnoreCase(actorName))
                .thenReturn(Collections.emptyList());

        // Act
        List<ActorResponseDto> result = actorService.getActorByName(actorName);

        // Assert
        assertThat(result).isEmpty();

        verify(actorRepository).findAllByNameIgnoreCase(actorName);
        verifyNoInteractions(actorMapper);
    }

    @Test
    void should_return_empty_list_when_no_actors_exist() {
        when(actorRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<ActorResponseDto> result = actorService.getAllActors();

        assertThat(result).isEmpty();

        verify(actorRepository).findAll();
        verifyNoInteractions(actorMapper);
    }
}
