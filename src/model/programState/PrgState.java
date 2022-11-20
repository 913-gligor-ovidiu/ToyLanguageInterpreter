package model.programState;

import model.statements.IStmt;
import model.utils.MyIList;
import model.utils.MyIStack;
import model.utils.MyIDict;
import model.value.Value;
import model.utils.MyIHeap;

import java.io.BufferedReader;

public class PrgState {

    private MyIStack<IStmt> exeStack;
    private MyIDict<String, Value> symTable;
    private MyIList<Value> out;

    private MyIHeap heap;

    private MyIDict<String, BufferedReader> fileTable;
    private IStmt originalProgram;

    public PrgState(MyIStack<IStmt> exeStack, MyIDict<String, Value> symTable, MyIList<Value> out, IStmt originalProgram, MyIDict<String, BufferedReader> fileTable, MyIHeap heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap=heap;
        this.originalProgram = originalProgram;
        this.exeStack.push(originalProgram);

    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIDict<String, Value> getSymTable() {
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public MyIHeap getHeap(){return heap;}

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(MyIDict<String, Value> symTable) {
        this.symTable = symTable;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public void setHeap(MyIHeap heap) {this.heap = heap;}

    public void setFileTable(MyIDict<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public MyIDict<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    @Override
    public String toString() {
        return
                "\nExeStack\n" + exeStack + "\n" +
                "\nSymTable\n" + symTable + "\n" +
                "\nOut\n" + out + "\n" +
                "\nFileTable\n" + fileTable + "\n"+
                "\nHeap\n" + heap + "\n" +
                "\nOriginalProgram\n" + originalProgram + "\n------------------------------------------------------------------"
                ;
    }
}