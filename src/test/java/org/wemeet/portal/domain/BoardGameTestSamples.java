package org.wemeet.portal.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BoardGameTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static BoardGame getBoardGameSample1() {
        return new BoardGame()
            .id("id1")
            .englishName("englishName1")
            .chineseName("chineseName1")
            .yearPublished(1)
            .minPlayers(1)
            .maxPlayers(1)
            .playTimeMin(1)
            .playTimeMax(1)
            .minAge(1)
            .suggestedAge(1)
            .mechanics("mechanics1")
            .categories("categories1")
            .publishers("publishers1")
            .ratingVotes(1L)
            .complexityVotes(1L)
            .officialUrl("officialUrl1")
            .bggUrl("bggUrl1")
            .gstoneUrl("gstoneUrl1")
            .thumbnailUrl("thumbnailUrl1")
            .largeImageUrl("largeImageUrl1");
    }

    public static BoardGame getBoardGameSample2() {
        return new BoardGame()
            .id("id2")
            .englishName("englishName2")
            .chineseName("chineseName2")
            .yearPublished(2)
            .minPlayers(2)
            .maxPlayers(2)
            .playTimeMin(2)
            .playTimeMax(2)
            .minAge(2)
            .suggestedAge(2)
            .mechanics("mechanics2")
            .categories("categories2")
            .publishers("publishers2")
            .ratingVotes(2L)
            .complexityVotes(2L)
            .officialUrl("officialUrl2")
            .bggUrl("bggUrl2")
            .gstoneUrl("gstoneUrl2")
            .thumbnailUrl("thumbnailUrl2")
            .largeImageUrl("largeImageUrl2");
    }

    public static BoardGame getBoardGameRandomSampleGenerator() {
        return new BoardGame()
            .id(UUID.randomUUID().toString())
            .englishName(UUID.randomUUID().toString())
            .chineseName(UUID.randomUUID().toString())
            .yearPublished(intCount.incrementAndGet())
            .minPlayers(intCount.incrementAndGet())
            .maxPlayers(intCount.incrementAndGet())
            .playTimeMin(intCount.incrementAndGet())
            .playTimeMax(intCount.incrementAndGet())
            .minAge(intCount.incrementAndGet())
            .suggestedAge(intCount.incrementAndGet())
            .mechanics(UUID.randomUUID().toString())
            .categories(UUID.randomUUID().toString())
            .publishers(UUID.randomUUID().toString())
            .ratingVotes(longCount.incrementAndGet())
            .complexityVotes(longCount.incrementAndGet())
            .officialUrl(UUID.randomUUID().toString())
            .bggUrl(UUID.randomUUID().toString())
            .gstoneUrl(UUID.randomUUID().toString())
            .thumbnailUrl(UUID.randomUUID().toString())
            .largeImageUrl(UUID.randomUUID().toString());
    }
}
