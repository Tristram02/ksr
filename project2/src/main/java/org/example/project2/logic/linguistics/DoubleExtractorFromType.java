package org.example.project2.logic.linguistics;

import java.io.Serializable;
import java.util.function.Function;

public interface DoubleExtractorFromType<T> extends Function<T, Double>, Serializable {
}