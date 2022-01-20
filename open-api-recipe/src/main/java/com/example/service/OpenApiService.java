package com.example.service;

import com.example.service.db_access.DatabaseAccessible;
import com.example.service.kafka_access.Producer;

public interface OpenApiService extends DatabaseAccessible, Producer {
}
