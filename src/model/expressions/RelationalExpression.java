package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionEvalException;
import model.type.IntType;
import model.utils.MyIDict;
import model.utils.MyIHeap;
import model.value.BooleanValue;
import model.value.IntValue;
import model.value.Value;

import java.util.Objects;

public class RelationalExpression implements IExpression {
    IExpression expression1;
    IExpression expression2;
    String operator;

    public RelationalExpression(String operator, IExpression expression1, IExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public Value eval(MyIDict<String, Value> symTable, MyIHeap heap) throws ExpressionEvalException, ADTException {
        Value value1, value2;
        value1 = this.expression1.eval(symTable, heap);
        if (value1.getType().equals(new IntType())) {
            value2 = this.expression2.eval(symTable, heap);
            if (value2.getType().equals(new IntType())) {
                IntValue val1 = (IntValue) value1;
                IntValue val2 = (IntValue) value2;
                int v1, v2;
                v1 = val1.getValue();
                v2 = val2.getValue();
                if (Objects.equals(this.operator, "<"))
                    return new BooleanValue(v1 < v2);
                else if (Objects.equals(this.operator, "<="))
                    return new BooleanValue(v1 <= v2);
                else if (Objects.equals(this.operator, "=="))
                    return new BooleanValue(v1 == v2);
                else if (Objects.equals(this.operator, "!="))
                    return new BooleanValue(v1 != v2);
                else if (Objects.equals(this.operator, ">"))
                    return new BooleanValue(v1 > v2);
                else if (Objects.equals(this.operator, ">="))
                    return new BooleanValue(v1 >= v2);
            } else
                throw new ExpressionEvalException("Second operand is not an integer.");
        } else
            throw new ExpressionEvalException("First operand in not an integer.");
        return null;
    }


    @Override
    public String toString() {
        return this.expression1.toString() + " " + this.operator + " " + this.expression2.toString();
    }
}
