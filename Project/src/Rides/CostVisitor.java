package Rides;

public interface CostVisitor {
	public void visit(UberX uberX);
	public void visit(UberBlack UberBlack);
	public void visit(UberVan uberVan);
	public void visit(UberPool uberPool);
}
