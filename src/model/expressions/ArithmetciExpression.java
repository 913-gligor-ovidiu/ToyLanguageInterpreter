package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionEvalException;
import model.utils.MyIDict;
import model.utils.MyIHeap;
import model.value.Value;
import model.value.IntValue;
import model.type.IntType;

public class ArithmetciExpression implements IExpression{
    private IExpression e1;
    private IExpression e2;
    private char op;

    public ArithmetciExpression(char op, IExpression e1, IExpression e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDict<String, Value> symTable, MyIHeap heap) throws ExpressionEvalException, ADTException {
        Value v1, v2;
        v1 = e1.eval(symTable, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(symTable, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op == '+')
                    return new IntValue(n1 + n2);
                if (op == '-')
                    return new IntValue(n1 - n2);
                if (op == '*')
                    return new IntValue(n1 * n2);
                if (op == '/')
                    if (n2 == 0)
                        throw new ExpressionEvalException("Division by zero");
                    else
                        return new IntValue(n1 / n2);
            } else
                throw new ExpressionEvalException("Second operand is not an integer");
        } else
            throw new ExpressionEvalException("First operand is not an integer");
        return null;
    }

    @Override
    public String toString() {
        return e1.toString() + op + e2.toString();
    }
}
