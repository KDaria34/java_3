package org.fpm.di.example;

import javax.inject.Inject;

public class Human {
    private final Heart heart;
    private final Brain brain;

    @Inject
    public Human(Heart h, Brain b) {
        this.heart = h;
        this.brain = b;
    }
    public Heart getHeart() {
        return heart;
    }
    public Brain getBrain() {
        return brain;
    }
}
