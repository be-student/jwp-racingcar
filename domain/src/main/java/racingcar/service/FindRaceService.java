package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.RacingGame;
import racingcar.repository.RacingGameRepository;

@Service
public class FindRaceService {

    private final RacingGameRepository racingGameRepository;

    public FindRaceService(final RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    @Transactional(readOnly = true)
    public List<RacingGame> findAllRace() {
        return racingGameRepository.findAll();
    }
}