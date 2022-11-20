package view;

import controller.Controller;
import model.expressions.ArithmetciExpression;
import model.expressions.ValueExpression;
import model.expressions.VarExpression;
import model.programState.PrgState;
import model.statements.*;
import model.type.BooleanType;
import model.type.IntType;
import model.type.StringType;
import model.utils.MyHeap;
import model.value.StringValue;
import model.utils.MyDict;
import model.utils.MyList;
import model.utils.MyStack;
import model.value.BooleanValue;
import model.value.IntValue;
import repo.IRepository;
import repo.Repository;

public class Interpreter {
    public static void main(String[] args) {
        IStmt ex1= new CompoundStmt(new VarDeclStmt("v",new IntType()),
                new CompoundStmt(new AssignStmt("v",new ValueExpression(new IntValue(2))), new PrintStmt(new
                        VarExpression("v"))));
        PrgState prg1 = new PrgState(new MyStack<>(), new MyDict<>(), new MyList<>(), ex1, new MyDict<>(), new MyHeap());
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller controller1 = new Controller(repo1);

        IStmt ex2 = new CompoundStmt( new VarDeclStmt("a",new IntType()),
                new CompoundStmt(new VarDeclStmt("b",new IntType()),
                        new CompoundStmt(new AssignStmt("a", new ArithmetciExpression('+',new ValueExpression(new IntValue(2)),new
                                ArithmetciExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStmt(new AssignStmt("b",new ArithmetciExpression('+',new VarExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStmt(new VarExpression("b"))))));
        PrgState prg2 = new PrgState(new MyStack<>(), new MyDict<>(), new MyList<>(), ex2, new MyDict<>(), new MyHeap());
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller controller2 = new Controller(repo2);

        IStmt ex3 = new CompoundStmt(new VarDeclStmt("a",new BooleanType()),
                new CompoundStmt(new VarDeclStmt("v", new IntType()),
                        new CompoundStmt(new AssignStmt("a", new ValueExpression(new BooleanValue(true))),
                                new CompoundStmt(new IfStmt(new VarExpression("a"),new AssignStmt("v",new ValueExpression(new
                                        IntValue(2))), new AssignStmt("v", new ValueExpression(new IntValue(3)))), new PrintStmt(new
                                        VarExpression("v"))))));

        PrgState prg3 = new PrgState(new MyStack<>(), new MyDict<>(), new MyList<>(), ex3, new MyDict<>(), new MyHeap());
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller controller3 = new Controller(repo3);

        IStmt ex4 = new CompoundStmt(new VarDeclStmt("varf", new StringType()),
                new CompoundStmt(new AssignStmt("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStmt(new OpenReadFileStmt(new VarExpression("varf")),
                                new CompoundStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompoundStmt(new ReadFileStmt(new VarExpression("varf"), "varc"),
                                                new CompoundStmt(new PrintStmt(new VarExpression("varc")),
                                                        new CompoundStmt(new ReadFileStmt(new VarExpression("varf"), "varc"),
                                                                new CompoundStmt(new PrintStmt(new VarExpression("varc")),
                                                                        new CloseReadFileStmt(new VarExpression("varf"))))))))));

        PrgState prg4 = new PrgState(new MyStack<>(), new MyDict<>(), new MyList<>(), ex4, new MyDict<>(), new MyHeap());
        IRepository repo4 = new Repository(prg4, "log4.txt");
        Controller controller4 = new Controller(repo4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", ex1.toString(), controller1));
        menu.addCommand(new RunExampleCommand("2", ex2.toString(), controller2));
        menu.addCommand(new RunExampleCommand("3", ex3.toString(), controller3));
        menu.addCommand(new RunExampleCommand("4", ex4.toString(), controller4));
        menu.show();
    }
}
