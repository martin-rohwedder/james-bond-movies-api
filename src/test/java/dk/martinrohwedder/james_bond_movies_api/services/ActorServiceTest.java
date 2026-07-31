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
}
