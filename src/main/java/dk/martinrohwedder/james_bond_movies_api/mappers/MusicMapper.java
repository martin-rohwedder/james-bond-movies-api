package dk.martinrohwedder.james_bond_movies_api.mappers;

import dk.martinrohwedder.james_bond_movies_api.dtos.MusicResponseDto;
import dk.martinrohwedder.james_bond_movies_api.entities.Music;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicMapper {
    MusicResponseDto musicToMusicResponseDto(Music music);
}
