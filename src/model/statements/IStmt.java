package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionEvalException;
import exceptions.StatementExecException;
import model.programState.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws StatementExecException, ExpressionEvalException, ADTException;
    IStmt deepCopy();
}
