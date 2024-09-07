package com.example.devopapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateNotificationDto(@JsonProperty("included_segments")
                                    String[] includedSegments,
                                    @JsonProperty("contents")
                                    ContentDto contents) {
}
