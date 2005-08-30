package gov.nih.nci.camod.service;

public interface CuratableActionFactory {
    public CurateableAction getAction(String inActionName);
    public void registerAction(String inActionName, CurateableAction inAction);
}
