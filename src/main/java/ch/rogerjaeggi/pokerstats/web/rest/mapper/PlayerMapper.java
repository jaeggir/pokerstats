package ch.rogerjaeggi.pokerstats.web.rest.mapper;

import ch.rogerjaeggi.pokerstats.domain.Player;
import ch.rogerjaeggi.pokerstats.web.rest.dto.PlayerDto;

import java.util.LinkedList;
import java.util.List;

public class PlayerMapper {

    public static List<PlayerDto> toDto(List<Player> players) {
        List<PlayerDto> dtos = new LinkedList<>();
        for (Player player : players) {
            dtos.add(toDto(player));
        }
        return dtos;
    }

    public static PlayerDto toDto(Player player) {
        PlayerDto dto = new PlayerDto();
        dto.setUuid(player.getUuid());
        dto.setGuest(player.getGuest());
        dto.setBirthday(player.getBirthday().toDateTimeAtStartOfDay().getMillis());
        dto.setJoined(player.getJoined().toDateTimeAtStartOfDay().getMillis());
        dto.setNickname(player.getNickname());
        dto.setUpdated(player.getUpdated().toDateTime().getMillis());
        return dto;
    }
}
