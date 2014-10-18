package domein.observers;


/**
 *
 * @author RootSoft
 */
public interface PlanningDialog {
    public void addObserver(PlanningObserver observer);
    public void removeObserver(PlanningObserver observer);
    public void notifyObservers();
    
}
