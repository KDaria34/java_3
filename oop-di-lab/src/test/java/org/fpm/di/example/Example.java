package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class Example {

    private Container container;

    @Before
    public void setUp() {
        Environment env = new DummyEnvironment();
        container = env.configure(new MyConfiguration());
    }

    @Test
    public void shouldInjectSingleton() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        assertNotSame(container.getComponent(MyPrototype.class), container.getComponent(MyPrototype.class));
    }

    @Test
    public void shouldBuildInjectionGraph() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        /*
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
        */
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        final UseA hasADependency = container.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }

    @Test
    public void Dasha_is_Girl() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        assertSame(container.getComponent(Girl.class), container.getComponent(Dasha.class));
    }

    @Test
    public void Different_Humans_has_different_Brains() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Human human1 = container.getComponent(Human.class);
        Human human2 = container.getComponent(Human.class);
        assertNotSame(human1.getBrain(), human2.getBrain());
    }

    @Test
    public void Different_Humans_has_different_Hearts() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Human human1 = container.getComponent(Human.class);
        Human human2 = container.getComponent(Human.class);
        assertNotSame(human1.getHeart(), human2.getHeart());
    }


}
