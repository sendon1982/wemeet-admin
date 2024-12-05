package org.wemeet.portal.domain;

import java.util.UUID;

public class GameTestSamples {

    public static Game getGameSample1() {
        return new Game().id("id1").name("name1").description("description1");
    }

    public static Game getGameSample2() {
        return new Game().id("id2").name("name2").description("description2");
    }

    public static Game getGameRandomSampleGenerator() {
        return new Game().id(UUID.randomUUID().toString()).name(UUID.randomUUID().toString()).description(UUID.randomUUID().toString());
    }
}
