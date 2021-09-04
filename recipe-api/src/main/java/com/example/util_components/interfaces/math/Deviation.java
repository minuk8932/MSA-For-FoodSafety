package com.example.util_components.interfaces.math;

import java.util.List;

public interface Deviation extends Average{

    double getDeviation(List<Double> value, double avg);

}