package baseNoStates.areas;

// Visitor interface for traversing areas and performing specific operations.
public interface AreaVisitor {
    void visitPartition(Partition partition);

    void visitSpace(Space space);
}
