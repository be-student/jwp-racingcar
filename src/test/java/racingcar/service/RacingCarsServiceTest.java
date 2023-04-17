package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import racingcar.dto.RaceDto;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class RacingCarsServiceTest {

    private final List<String> carsName = List.of("브리", "토미", "브라운");
    private final int count = 10;
    private RacingCarsService racingCarsService;

    @BeforeEach
    void setUp() {
        racingCarsService = new RacingCarsService(new StubNumberPicker(10),
                new StubRacingGameRepository());
    }

    @Test
    void 레이스_진행() {
        final RaceDto result = racingCarsService.race(carsName, count);
        
        assertAll(
                () -> assertThat(result.getWinners()).hasSize(3),
                () -> assertThat(result.getCarPositionDtos().get(0).getCarName()).isEqualTo("브리"),
                () -> assertThat(result.getCarPositionDtos().get(0).getStatus()).isEqualTo(10),
                () -> assertThat(result.getCarPositionDtos().get(1).getCarName()).isEqualTo("토미"),
                () -> assertThat(result.getCarPositionDtos().get(1).getStatus()).isEqualTo(10),
                () -> assertThat(result.getCarPositionDtos().get(2).getCarName()).isEqualTo("브라운"),
                () -> assertThat(result.getCarPositionDtos().get(2).getStatus()).isEqualTo(10)
        );
    }
}
