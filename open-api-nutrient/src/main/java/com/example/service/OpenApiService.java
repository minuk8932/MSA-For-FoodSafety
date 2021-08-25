package com.example.service;

import com.example.service.db__access.DatabaseAccessible;
import com.example.service.openapi.OpenApiConnectable;

public interface OpenApiService extends OpenApiConnectable, DatabaseAccessible {
}
