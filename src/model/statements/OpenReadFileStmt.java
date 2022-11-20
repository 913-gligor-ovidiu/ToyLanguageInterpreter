package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionEvalException;
import exceptions.StatementExecException;
import model.expressions.IExpression;
import model.programState.PrgState;
import model.type.StringType;
import model.utils.MyIDict;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStmt implements IStmt {

    private final IExpression exp;

    public OpenReadFileStmt(IExpression exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws ADTException, ExpressionEvalException, StatementExecException {
        Value value = exp.eval(state.getSymTable(), state.getHeap());
        if (value.getType().equals(new StringType())) {
            StringValue filename = (StringValue) value;
            MyIDict<String, BufferedReader> fileTable = state.getFileTable();
            if (!fileTable.isDefined(filename.getValue())) {
                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader(filename.getValue()));
                } catch (FileNotFoundException e) {
                    throw new StatementExecException("File not found");
                }
                fileTable.put(filename.getValue(), br);
                state.setFileTable(fileTable);
            } else
                throw new StatementExecException("File already open");
        } else
            throw new StatementExecException("Expression is not a string");
        return state;
    }

    @Override
    public String toString() {
        return "openReadFile(" + exp.toString() + ")";
    }
}


