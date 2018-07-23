package de.testapp.model;

import java.util.List;

public interface AsyncResult {
    void asyncFinished(List<Product> results);
}
