package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionEvalException;
import exceptions.StatementExecException;
import model.expressions.IExpression;
import model.programState.PrgState;
import model.utils.MyIStack;
import model.value.BooleanValue;
import model.value.Value;

public class WhileStmt implements IStmt{

    private final IExpression exp;
    private final IStmt stmt;

    public WhileStmt(IExpression exp, IStmt stmt){

        this.exp = exp;
        this.stmt =  stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementExecException, ExpressionEvalException, ADTException{

        Value value = exp.eval(state.getSymTable(), state.getHeap());

        if(!(value instanceof BooleanValue))
            throw new StatementExecException("While statement: expression is not a boolean");

        MyIStack<IStmt> exeStack = state.getExeStack();

        BooleanValue boolValue = (BooleanValue) value;

        if(boolValue.getValue()) {
            exeStack.push(this);
            exeStack.push(stmt);
        }
        state.setExeStack(exeStack);
        return state;
    }

    @Override
    public String toString(){
        return "while(" + exp.toString() + ") " + stmt.toString();
    }

}
