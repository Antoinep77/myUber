package Rides;

public interface CostVisitor {
	/**
	 * @param uberX an Uber X object
	 * @return return the fare of the uberX ride
	 */
	public double visit(UberX uberX);
	/**
	 * @param UberBlack An uber Black object
	 * @return return the fare of the uberXBlack ride
	 */
	public double visit(UberBlack UberBlack);
	/**
	 * @param uberVanAn uber Van object
	 * @return return the fare of the uberVan ride
	 */
	public double visit(UberVan uberVan);
	/**
	 * @param uberPool An uber Pool object
	 * @return return the fare of the uberPool ride
	 */
	public double visit(UberPool uberPool);
}
