package com.gadjibaev.gamestats.models;

import java.util.Optional;

public record GameStatisticsPostBody(Optional<Integer> profileId, Optional<Integer> gameId, Optional<Integer> hours) { }