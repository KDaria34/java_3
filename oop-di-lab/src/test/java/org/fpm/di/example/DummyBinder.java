package org.fpm.di.example;

import org.fpm.di.Binder;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class DummyBinder implements Binder {
    HashMap<Class<?>, Class<?>> impl = new HashMap<>();
    HashMap<Class<?>, Object> inst = new HashMap<>();
    HashMap<Class<?>, Constructor<?>> cons = new HashMap<>();
    @Override
    public <T> void bind(Class<T> clazz) {
        for(Constructor<?> constructor : clazz.getConstructors()) {
            if(constructor.isAnnotationPresent(Inject.class)) {
                cons.put(clazz, constructor);
            }
        }
    }
    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        impl.put(clazz, implementation);
    }
    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        inst.put(clazz, instance);
    }
}
