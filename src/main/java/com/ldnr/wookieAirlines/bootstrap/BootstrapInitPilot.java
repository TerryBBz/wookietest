package com.ldnr.wookieAirlines.bootstrap;

import com.ldnr.wookieAirlines.models.Pilot;
import com.ldnr.wookieAirlines.models.RaceType;
import com.ldnr.wookieAirlines.models.enums.PilotStatusEnum;
import com.ldnr.wookieAirlines.repositories.PilotRepository;
import com.ldnr.wookieAirlines.repositories.RaceTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(4)
public class BootstrapInitPilot implements CommandLineRunner {

    private final PilotRepository pilotRepository;
    private final RaceTypeRepository raceTypeRepository;

    public BootstrapInitPilot(PilotRepository pilotRepository, RaceTypeRepository raceTypeRepository) {
        this.pilotRepository = pilotRepository;
        this.raceTypeRepository = raceTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstrap Init Pilot...");

        // Récupérer quelques races pour les pilotes
        RaceType chalacteens = raceTypeRepository.findById(1L).orElse(null);
        RaceType chiss = raceTypeRepository.findById(2L).orElse(null);
        RaceType humains = raceTypeRepository.findById(3L).orElse(null);
        RaceType ithoriens = raceTypeRepository.findById(4L).orElse(null);
        RaceType mirialans = raceTypeRepository.findById(5L).orElse(null);
        RaceType ongrees = raceTypeRepository.findById(13L).orElse(null);
        RaceType togrutas = raceTypeRepository.findById(19L).orElse(null);
        RaceType wookiee = raceTypeRepository.findById(20L).orElse(null);
        RaceType zabraks = raceTypeRepository.findById(22L).orElse(null);
        RaceType miralukas = raceTypeRepository.findById(8L).orElse(null);

        // Pilote 1
        Pilot jax = new Pilot();
        jax.setFirstName("Jax");
        jax.setLastName("Skywalker");
        jax.setSubscriptionAge(25);
        jax.setSubscriptionDate(LocalDate.of(2023, 1, 15));
        jax.setTotalFlightTime(1250);
        jax.setTotalMissions(5);
        jax.setTotalCombatMissions(2);
        jax.setPilotStatus(PilotStatusEnum.AVAILABLE);
        jax.setRace(chalacteens);
        pilotRepository.save(jax);

        // Pilote 2
        Pilot rey = new Pilot();
        rey.setFirstName("Rey");
        rey.setLastName("Kenobi");
        rey.setSubscriptionAge(28);
        rey.setSubscriptionDate(LocalDate.of(2022, 11, 8));
        rey.setTotalFlightTime(2100);
        rey.setTotalMissions(8);
        rey.setTotalCombatMissions(4);
        rey.setPilotStatus(PilotStatusEnum.AVAILABLE);
        rey.setRace(humains);
        pilotRepository.save(rey);

        // Pilote 3
        Pilot finn = new Pilot();
        finn.setFirstName("Finn");
        finn.setLastName("Dameron");
        finn.setSubscriptionAge(30);
        finn.setSubscriptionDate(LocalDate.of(2021, 5, 22));
        finn.setTotalFlightTime(3200);
        finn.setTotalMissions(12);
        finn.setTotalCombatMissions(7);
        finn.setPilotStatus(PilotStatusEnum.AVAILABLE);
        finn.setRace(chiss);
        pilotRepository.save(finn);

        // Pilote 4
        Pilot poe = new Pilot();
        poe.setFirstName("Poe");
        poe.setLastName("Solo");
        poe.setSubscriptionAge(32);
        poe.setSubscriptionDate(LocalDate.of(2020, 9, 14));
        poe.setTotalFlightTime(4500);
        poe.setTotalMissions(18);
        poe.setTotalCombatMissions(11);
        poe.setPilotStatus(PilotStatusEnum.AVAILABLE);
        poe.setRace(zabraks);
        pilotRepository.save(poe);

        // Pilote 5
        Pilot ahsoka = new Pilot();
        ahsoka.setFirstName("Ahsoka");
        ahsoka.setLastName("Tano");
        ahsoka.setSubscriptionAge(27);
        ahsoka.setSubscriptionDate(LocalDate.of(2023, 3, 10));
        ahsoka.setTotalFlightTime(1800);
        ahsoka.setTotalMissions(6);
        ahsoka.setTotalCombatMissions(3);
        ahsoka.setPilotStatus(PilotStatusEnum.AVAILABLE);
        ahsoka.setRace(togrutas);
        pilotRepository.save(ahsoka);

        // Pilote 6
        Pilot kanan = new Pilot();
        kanan.setFirstName("Kanan");
        kanan.setLastName("Jarrus");
        kanan.setSubscriptionAge(35);
        kanan.setSubscriptionDate(LocalDate.of(2019, 12, 3));
        kanan.setTotalFlightTime(5200);
        kanan.setTotalMissions(23);
        kanan.setTotalCombatMissions(15);
        kanan.setPilotStatus(PilotStatusEnum.AVAILABLE);
        kanan.setRace(ithoriens);
        pilotRepository.save(kanan);

        // Pilote 7
        Pilot ezra = new Pilot();
        ezra.setFirstName("Ezra");
        ezra.setLastName("Bridger");
        ezra.setSubscriptionAge(22);
        ezra.setSubscriptionDate(LocalDate.of(2024, 1, 20));
        ezra.setTotalFlightTime(900);
        ezra.setTotalMissions(3);
        ezra.setTotalCombatMissions(1);
        ezra.setPilotStatus(PilotStatusEnum.AVAILABLE);
        ezra.setRace(humains);
        pilotRepository.save(ezra);

        // Pilote 8
        Pilot sabine = new Pilot();
        sabine.setFirstName("Sabine");
        sabine.setLastName("Wren");
        sabine.setSubscriptionAge(26);
        sabine.setSubscriptionDate(LocalDate.of(2022, 7, 18));
        sabine.setTotalFlightTime(2800);
        sabine.setTotalMissions(10);
        sabine.setTotalCombatMissions(6);
        sabine.setPilotStatus(PilotStatusEnum.AVAILABLE);
        sabine.setRace(mirialans);
        pilotRepository.save(sabine);

        // Pilote 9
        Pilot hera = new Pilot();
        hera.setFirstName("Hera");
        hera.setLastName("Syndulla");
        hera.setSubscriptionAge(38);
        hera.setSubscriptionDate(LocalDate.of(2018, 4, 25));
        hera.setTotalFlightTime(6200);
        hera.setTotalMissions(28);
        hera.setTotalCombatMissions(18);
        hera.setPilotStatus(PilotStatusEnum.AVAILABLE);
        hera.setRace(ongrees);
        pilotRepository.save(hera);

        // Pilote 10
        Pilot zeb = new Pilot();
        zeb.setFirstName("Zeb");
        zeb.setLastName("Orrelios");
        zeb.setSubscriptionAge(45);
        zeb.setSubscriptionDate(LocalDate.of(2017, 8, 12));
        zeb.setTotalFlightTime(7100);
        zeb.setTotalMissions(35);
        zeb.setTotalCombatMissions(22);
        zeb.setPilotStatus(PilotStatusEnum.AVAILABLE);
        zeb.setRace(miralukas);
        pilotRepository.save(zeb);

        // Pilote 11
        Pilot cal = new Pilot();
        cal.setFirstName("Cal");
        cal.setLastName("Kestis");
        cal.setSubscriptionAge(24);
        cal.setSubscriptionDate(LocalDate.of(2023, 6, 5));
        cal.setTotalFlightTime(1450);
        cal.setTotalMissions(4);
        cal.setTotalCombatMissions(2);
        cal.setPilotStatus(PilotStatusEnum.AVAILABLE);
        cal.setRace(humains);
        pilotRepository.save(cal);

        // Pilote 12
        Pilot jyn = new Pilot();
        jyn.setFirstName("Jyn");
        jyn.setLastName("Erso");
        jyn.setSubscriptionAge(29);
        jyn.setSubscriptionDate(LocalDate.of(2021, 10, 30));
        jyn.setTotalFlightTime(3800);
        jyn.setTotalMissions(15);
        jyn.setTotalCombatMissions(9);
        jyn.setPilotStatus(PilotStatusEnum.AVAILABLE);
        jyn.setRace(humains);
        pilotRepository.save(jyn);

        // Pilote 13
        Pilot cassian = new Pilot();
        cassian.setFirstName("Cassian");
        cassian.setLastName("Andor");
        cassian.setSubscriptionAge(34);
        cassian.setSubscriptionDate(LocalDate.of(2020, 2, 14));
        cassian.setTotalFlightTime(4900);
        cassian.setTotalMissions(21);
        cassian.setTotalCombatMissions(13);
        cassian.setPilotStatus(PilotStatusEnum.AVAILABLE);
        cassian.setRace(humains);
        pilotRepository.save(cassian);

        // Pilote 14
        Pilot bodhi = new Pilot();
        bodhi.setFirstName("Bodhi");
        bodhi.setLastName("Rook");
        bodhi.setSubscriptionAge(31);
        bodhi.setSubscriptionDate(LocalDate.of(2021, 9, 8));
        bodhi.setTotalFlightTime(3300);
        bodhi.setTotalMissions(11);
        bodhi.setTotalCombatMissions(5);
        bodhi.setPilotStatus(PilotStatusEnum.AVAILABLE);
        bodhi.setRace(humains);
        pilotRepository.save(bodhi);

        // Pilote 15
        Pilot chirrut = new Pilot();
        chirrut.setFirstName("Chirrut");
        chirrut.setLastName("Imwe");
        chirrut.setSubscriptionAge(55);
        chirrut.setSubscriptionDate(LocalDate.of(2015, 3, 22));
        chirrut.setTotalFlightTime(8500);
        chirrut.setTotalMissions(42);
        chirrut.setTotalCombatMissions(28);
        chirrut.setPilotStatus(PilotStatusEnum.AVAILABLE);
        chirrut.setRace(humains);
        pilotRepository.save(chirrut);

        // Pilote 16
        Pilot baze = new Pilot();
        baze.setFirstName("Baze");
        baze.setLastName("Malbus");
        baze.setSubscriptionAge(52);
        baze.setSubscriptionDate(LocalDate.of(2016, 11, 17));
        baze.setTotalFlightTime(7800);
        baze.setTotalMissions(38);
        baze.setTotalCombatMissions(25);
        baze.setPilotStatus(PilotStatusEnum.AVAILABLE);
        baze.setRace(humains);
        pilotRepository.save(baze);

        // Pilote 17
        Pilot din = new Pilot();
        din.setFirstName("Din");
        din.setLastName("Djarin");
        din.setSubscriptionAge(40);
        din.setSubscriptionDate(LocalDate.of(2019, 1, 28));
        din.setTotalFlightTime(5800);
        din.setTotalMissions(26);
        din.setTotalCombatMissions(16);
        din.setPilotStatus(PilotStatusEnum.AVAILABLE);
        din.setRace(humains);
        pilotRepository.save(din);

        // Pilote 18
        Pilot bo = new Pilot();
        bo.setFirstName("Bo");
        bo.setLastName("Katan");
        bo.setSubscriptionAge(48);
        bo.setSubscriptionDate(LocalDate.of(2017, 5, 19));
        bo.setTotalFlightTime(6900);
        bo.setTotalMissions(32);
        bo.setTotalCombatMissions(21);
        bo.setPilotStatus(PilotStatusEnum.AVAILABLE);
        bo.setRace(humains);
        pilotRepository.save(bo);

        // Pilote 19
        Pilot cara = new Pilot();
        cara.setFirstName("Cara");
        cara.setLastName("Dune");
        cara.setSubscriptionAge(36);
        cara.setSubscriptionDate(LocalDate.of(2020, 8, 7));
        cara.setTotalFlightTime(5100);
        cara.setTotalMissions(24);
        cara.setTotalCombatMissions(14);
        cara.setPilotStatus(PilotStatusEnum.AVAILABLE);
        cara.setRace(humains);
        pilotRepository.save(cara);

        // Pilote 20
        Pilot migs = new Pilot();
        migs.setFirstName("Migs");
        migs.setLastName("Mayfeld");
        migs.setSubscriptionAge(41);
        migs.setSubscriptionDate(LocalDate.of(2018, 12, 11));
        migs.setTotalFlightTime(6300);
        migs.setTotalMissions(29);
        migs.setTotalCombatMissions(17);
        migs.setPilotStatus(PilotStatusEnum.AVAILABLE);
        migs.setRace(wookiee);
        pilotRepository.save(migs);

        System.out.println("Bootstrap Init Pilot terminé ! 20 pilotes créés.");
    }
}