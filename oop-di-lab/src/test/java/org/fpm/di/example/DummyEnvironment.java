package org.fpm.di.example;

import org.fpm.di.Configuration;
import org.fpm.di.Container;
import org.fpm.di.Environment;

public class DummyEnvironment implements Environment {
    @Override
    public Container configure(Configuration configuration) {
        DummyBinder dm = new DummyBinder();
        configuration.configure(dm);
        DummyContainer dc = new DummyContainer();
        dc.binder = dm;
        return dc;
    }
}
