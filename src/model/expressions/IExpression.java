package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionEvalException;
import model.utils.MyIDict;
import model.utils.MyIHeap;
import model.value.Value;

public interface IExpression {
    Value eval(MyIDict<String, Value> symTable, MyIHeap heap) throws ExpressionEvalException, ADTException;
    IExpression deepCopy();
}
