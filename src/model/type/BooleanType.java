package model.type;

import model.value.Value;
import model.value.BooleanValue;
public class BooleanType implements Type {
    @Override
    public boolean equals(Type another) {
        return another instanceof BooleanType;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public Value defaultValue() {
        return new BooleanValue(false);
    }

}

