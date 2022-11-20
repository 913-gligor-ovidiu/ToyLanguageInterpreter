package repo;

import exceptions.ADTException;
import model.programState.PrgState;

import java.io.IOException;
import java.util.List;


public interface IRepository {
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> prgList);
    void addPrg(PrgState prg);

    void logPrgStateExec() throws IOException, ADTException;

    PrgState getCurrentPrg();
}
