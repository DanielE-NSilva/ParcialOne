package com.example.parcialone.helpers;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ErrorMessage {
    private List<Map<String, String>> error;
}
