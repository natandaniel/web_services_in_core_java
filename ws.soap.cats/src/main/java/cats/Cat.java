package cats;

public class Cat {

	private String size;
	private String colour;
	private String coatLength;
	private int avgAgeExpectancy;
	private int avgWeight;
	private String grooming;
	private String lifestyle;

	public Cat() {
	}

	public Cat(String size, String colour, String coatLength, int avgAgeExpectancy, int avgWeight, String grooming,
			String lifestyle) {
		super();
		this.size = size;
		this.colour = colour;
		this.coatLength = coatLength;
		this.avgAgeExpectancy = avgAgeExpectancy;
		this.avgWeight = avgWeight;
		this.grooming = grooming;
		this.lifestyle = lifestyle;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getCoatLength() {
		return coatLength;
	}

	public void setCoatLength(String coatLength) {
		this.coatLength = coatLength;
	}

	public int getAvgAgeExpectancy() {
		return avgAgeExpectancy;
	}

	public void setAvgAgeExpectancy(int avgAgeExpectancy) {
		this.avgAgeExpectancy = avgAgeExpectancy;
	}

	public int getAvgWeight() {
		return avgWeight;
	}

	public void setAvgWeight(int avgWeight) {
		this.avgWeight = avgWeight;
	}

	public String getGrooming() {
		return grooming;
	}

	public void setGrooming(String grooming) {
		this.grooming = grooming;
	}

	public String getLifestyle() {
		return lifestyle;
	}

	public void setLifestyle(String lifestyle) {
		this.lifestyle = lifestyle;
	}
}
