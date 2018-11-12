package Rides;

public interface CostVisitor {
	public double visit(UberX uberX);
	public double visit(UberBlack UberBlack);
	public double visit(UberVan uberVan);
	public double visit(UberPool uberPool);
}
