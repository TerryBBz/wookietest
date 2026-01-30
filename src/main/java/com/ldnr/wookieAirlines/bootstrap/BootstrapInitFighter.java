package com.ldnr.wookieAirlines.bootstrap;

import com.ldnr.wookieAirlines.models.Fighter;
import com.ldnr.wookieAirlines.models.FighterType;
import com.ldnr.wookieAirlines.models.enums.FighterStatusEnum;
import com.ldnr.wookieAirlines.repositories.FighterRepository;
import com.ldnr.wookieAirlines.repositories.FighterTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class BootstrapInitFighter implements CommandLineRunner {

    private final FighterRepository fighterRepository;
    private final FighterTypeRepository fighterTypeRepository;

    public BootstrapInitFighter(FighterRepository fighterRepository, FighterTypeRepository fighterTypeRepository) {
        this.fighterRepository = fighterRepository;
        this.fighterTypeRepository = fighterTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstrap Init Fighter...");

        FighterType xWingType = fighterTypeRepository.findById(1L).orElse(null);
        FighterType yWingType = fighterTypeRepository.findById(2L).orElse(null);

        // Fighter 1
        Fighter redLeader = new Fighter();
        redLeader.setName("Red Leader");
        redLeader.setType(xWingType);
        redLeader.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(redLeader);

        // Fighter 2
        Fighter redTwo = new Fighter();
        redTwo.setName("Red Two");
        redTwo.setType(xWingType);
        redTwo.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(redTwo);

        // Fighter 3
        Fighter redThree = new Fighter();
        redThree.setName("Red Three");
        redThree.setType(xWingType);
        redThree.setFighterStatus(FighterStatusEnum.MAINTENANCE);
        fighterRepository.save(redThree);

        // Fighter 4
        Fighter redFour = new Fighter();
        redFour.setName("Red Four");
        redFour.setType(xWingType);
        redFour.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(redFour);

        // Fighter 5
        Fighter redFive = new Fighter();
        redFive.setName("Red Five");
        redFive.setType(xWingType);
        redFive.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(redFive);

        // Fighter 6
        Fighter blueLeader = new Fighter();
        blueLeader.setName("Blue Leader");
        blueLeader.setType(yWingType);
        blueLeader.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(blueLeader);

        // Fighter 7
        Fighter blueTwo = new Fighter();
        blueTwo.setName("Blue Two");
        blueTwo.setType(yWingType);
        blueTwo.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(blueTwo);

        // Fighter 8
        Fighter blueThree = new Fighter();
        blueThree.setName("Blue Three");
        blueThree.setType(yWingType);
        blueThree.setFighterStatus(FighterStatusEnum.MAINTENANCE);
        fighterRepository.save(blueThree);

        // Fighter 9
        Fighter goldLeader = new Fighter();
        goldLeader.setName("Gold Leader");
        goldLeader.setType(xWingType);
        goldLeader.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(goldLeader);

        // Fighter 10
        Fighter goldTwo = new Fighter();
        goldTwo.setName("Gold Two");
        goldTwo.setType(xWingType);
        goldTwo.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(goldTwo);

        // Fighter 11
        Fighter goldThree = new Fighter();
        goldThree.setName("Gold Three");
        goldThree.setType(xWingType);
        goldThree.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(goldThree);

        // Fighter 12
        Fighter rogueLeader = new Fighter();
        rogueLeader.setName("Rogue Leader");
        rogueLeader.setType(xWingType);
        rogueLeader.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(rogueLeader);

        // Fighter 13
        Fighter rogueTwo = new Fighter();
        rogueTwo.setName("Rogue Two");
        rogueTwo.setType(xWingType);
        rogueTwo.setFighterStatus(FighterStatusEnum.MAINTENANCE);
        fighterRepository.save(rogueTwo);

        // Fighter 14
        Fighter phoenixLeader = new Fighter();
        phoenixLeader.setName("Phoenix Leader");
        phoenixLeader.setType(yWingType);
        phoenixLeader.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(phoenixLeader);

        // Fighter 15
        Fighter phoenixTwo = new Fighter();
        phoenixTwo.setName("Phoenix Two");
        phoenixTwo.setType(yWingType);
        phoenixTwo.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(phoenixTwo);

        // Fighter 16
        Fighter wraithOne = new Fighter();
        wraithOne.setName("Wraith One");
        wraithOne.setType(xWingType);
        wraithOne.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(wraithOne);

        // Fighter 17
        Fighter wraithTwo = new Fighter();
        wraithTwo.setName("Wraith Two");
        wraithTwo.setType(xWingType);
        wraithTwo.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(wraithTwo);

        // Fighter 18
        Fighter spectreOne = new Fighter();
        spectreOne.setName("Spectre One");
        spectreOne.setType(xWingType);
        spectreOne.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(spectreOne);

        // Fighter 19
        Fighter spectreTwo = new Fighter();
        spectreTwo.setName("Spectre Two");
        spectreTwo.setType(xWingType);
        spectreTwo.setFighterStatus(FighterStatusEnum.MAINTENANCE);
        fighterRepository.save(spectreTwo);

        // Fighter 20
        Fighter ghostSquadron = new Fighter();
        ghostSquadron.setName("Ghost Squadron");
        ghostSquadron.setType(yWingType);
        ghostSquadron.setFighterStatus(FighterStatusEnum.OPERATIONAL);
        fighterRepository.save(ghostSquadron);

        System.out.println("Bootstrap Init Fighter terminé ! 20 fighters créés.");
    }
}