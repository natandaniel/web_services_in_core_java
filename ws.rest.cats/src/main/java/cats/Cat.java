package cats;

public class Cat {

	private String name;
	private String size;
	private String colour;
	private String coatLength;
	private int avgAgeExpectancy;
	private int avgWeight;
	private String grooming;
	private String lifestyle;

	public Cat() {}

	public Cat(String name, String size, String colour, String coatLength, int avgAgeExpectancy, int avgWeight,
			String grooming, String lifestyle) {
		super();
		this.name = name;
		this.size = size;
		this.colour = colour;
		this.coatLength = coatLength;
		this.avgAgeExpectancy = avgAgeExpectancy;
		this.avgWeight = avgWeight;
		this.grooming = grooming;
		this.lifestyle = lifestyle;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "[name=" + name + ", size=" + size + ", colour=" + colour + ", coatLength=" + coatLength
				+ ", avgAgeExpectancy=" + avgAgeExpectancy + ", avgWeight=" + avgWeight + ", grooming=" + grooming
				+ ", lifestyle=" + lifestyle + "]";
	}
}
