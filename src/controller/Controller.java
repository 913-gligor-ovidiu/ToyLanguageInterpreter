package controller;

import model.programState.PrgState;
import model.statements.IStmt;
import model.value.RefValue;
import model.value.Value;
import repo.IRepository;
import exceptions.ADTException;
import exceptions.ExpressionEvalException;
import exceptions.StatementExecException;
import model.utils.MyIStack;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    IRepository repo;
    boolean displayFlag=false;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public List<Integer> getAddrFromHeap(Collection<Value> heapValues) {
        return heapValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> ( symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public PrgState oneStep(PrgState state) throws StatementExecException, ExpressionEvalException, ADTException {
        MyIStack<IStmt> exeStack = state.getExeStack();
        if (exeStack.isEmpty()) {
            throw new ADTException("Execution stack is empty!");
        }
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(state);
    }

    public void allSteps() throws StatementExecException, ExpressionEvalException, ADTException, IOException {
        PrgState prg = repo.getCurrentPrg();
        repo.logPrgStateExec();
        display();

        if(displayFlag){
            Scanner debugReader = new Scanner(System.in);
            System.out.println("Do you want to run in debug mode?: (1/0) ");
            int debugOpt = debugReader.nextInt();
            boolean debugFlag = false;

            if(debugOpt ==  1){
                debugFlag = true;
                while (!prg.getExeStack().isEmpty()) {
                    oneStep(prg);
                    repo.logPrgStateExec();
                    display();
                    if(debugFlag){
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Do you want to continue in debug mode?: (1/0)");
                        int opt = scanner.nextInt();
                        if(opt == 1)
                            continue;
                        else if(opt == 0)
                            debugFlag = false;
                    }
                }
            }
            else if(debugOpt ==  0) {
                while (!prg.getExeStack().isEmpty()) {
                    oneStep(prg);
                    repo.logPrgStateExec();
                    display();
                }
            }
        }
        else{
            while (!prg.getExeStack().isEmpty()) {
                oneStep(prg);
                repo.logPrgStateExec();
                prg.getHeap().setContent(
                        (HashMap<Integer, Value>) safeGarbageCollector(
                                getAddrFromSymTable(prg.getSymTable().getContent().values()),getAddrFromHeap(prg.getHeap().getContent().values()),prg.getHeap().getContent()
                        ));
                //repo.logPrgStateExec();
                display();
            }
        }
    }
    private void display(){
        if(displayFlag){
            System.out.println(repo.getCurrentPrg().toString());
        }
    }

}
