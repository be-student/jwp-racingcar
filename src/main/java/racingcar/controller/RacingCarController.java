package racingcar.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.CarPositionDto;
import racingcar.dto.CarResponse;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.service.RacingCarsService;

@RestController
public class RacingCarController {

    private static final String CAR_NAME_DELIMITER = ",";
    private final RacingCarsService racingCarsService;

    public RacingCarController(final RacingCarsService racingCarsService) {
        this.racingCarsService = racingCarsService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponse> plays(@RequestBody final GameRequest gameRequest) {
        final List<String> carNames = Arrays.asList(gameRequest.getNames().split(CAR_NAME_DELIMITER));

        final int gameId = racingCarsService.race(carNames, gameRequest.getCount());
        final List<CarPositionDto> carsWithPosition = racingCarsService.findCarsWithPosition(gameId);
        final List<CarPositionDto> winners = racingCarsService.findWinners(gameId);

        final GameResponse gameResponse = toGameResponse(carsWithPosition, winners);
        return new ResponseEntity<>(gameResponse, HttpStatus.OK);
    }

    private GameResponse toGameResponse(final List<CarPositionDto> carsWithPosition,
            final List<CarPositionDto> winners) {
        final List<CarResponse> carResponses = carsWithPosition
                .stream()
                .map(carPositionDto -> new CarResponse(carPositionDto.getCarName(), carPositionDto.getStatus()))
                .collect(Collectors.toList());
        final String winner = winners.stream()
                .map(CarPositionDto::getCarName)
                .collect(Collectors.joining(CAR_NAME_DELIMITER));

        return new GameResponse(winner, carResponses);
    }
}