package com.example.devopapi.api.file;

import lombok.Builder;

@Builder
public record FileDto(String name,
                      String url,
                      String extension,
                      long size) {
}
