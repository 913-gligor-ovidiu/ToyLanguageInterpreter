package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionEvalException;
import model.utils.MyIDict;
import model.utils.MyIHeap;
import model.value.Value;

public class ValueExpression implements IExpression {
    private final Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value eval(MyIDict<String, Value> symTable, MyIHeap heap) throws ExpressionEvalException, ADTException {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

