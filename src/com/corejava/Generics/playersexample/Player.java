package com.corejava.Generics.playersexample;

import java.util.Objects;

public abstract class Player {
    private String name;

    public Player(final String name) {
        if (name == null) {
            throw new NullPointerException("Player name cannot be null");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
