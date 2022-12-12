package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionEvalException;
import model.utils.MyIDict;
import model.utils.MyIHeap;
import model.value.RefValue;
import model.value.Value;

public class ReadHeapExpression implements IExpression{

    private final IExpression exp;

    public ReadHeapExpression(IExpression exp) {
        this.exp = exp;
    }

    @Override
    public Value eval(MyIDict<String, Value> symTable, MyIHeap heap) throws ExpressionEvalException, ADTException {
        Value value = exp.eval(symTable, heap);

        if(!(value instanceof RefValue))
            throw new ExpressionEvalException("The value is not a reference value");

        int address = ((RefValue) value).getAddress();

        if(!heap.isDefined(address))
            throw new ExpressionEvalException("The address is not a key in the heap");

        return heap.lookup(address);
    }

    @Override
    public IExpression deepCopy() {
        return new ReadHeapExpression(exp.deepCopy());
    }

    @Override
    public String toString() {
        return "rH(" + exp + ")";
    }
}
